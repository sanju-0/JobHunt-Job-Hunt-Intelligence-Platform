document.addEventListener("DOMContentLoaded", () => {
  chrome.storage.local.get("timeData", result => {
    const statsDiv = document.getElementById("stats");
    const warningDiv = document.getElementById("warning");
    const data = result.timeData || {};

    let totalSeconds = 0;

    if (Object.keys(data).length === 0) {
      statsDiv.innerHTML = "<p>No data yet</p>";
      return;
    }

    for (const domain in data) {
      const seconds = data[domain];
      totalSeconds += seconds;

      const minutes = Math.floor(seconds / 60);
      const percent = Math.min((seconds / 3600) * 100, 100);

      const card = document.createElement("div");
      card.className = "card";

      card.innerHTML = `
        <div class="domain">${domain}</div>
        <div class="time">${minutes} min</div>
        <div class="progress">
          <div class="progress-fill" style="width:${percent}%"></div>
        </div>
      `;

      statsDiv.appendChild(card);
    }

    // 1 hour warning
    if (totalSeconds >= 3600) {
      warningDiv.classList.remove("hidden");
    }
  });
});
