let activeDomain = null;
let startTime = null;

// job hunting domains
const JOB_SITES = [
  "linkedin.com",
  "indeed.com",
  "naukri.com"
];

// helper: get domain from url
function getDomain(url) {
  try {
    return new URL(url).hostname.replace("www.", "");
  } catch {
    return null;
  }
}

// check if domain is job site
function isJobSite(domain) {
  return JOB_SITES.some(site => domain.includes(site));
}

// save time spent
function saveTime() {
  if (!activeDomain || !startTime) return;

  const timeSpent = Math.floor((Date.now() - startTime) / 1000);

  chrome.storage.local.get(["timeData"], result => {
    const data = result.timeData || {};
    data[activeDomain] = (data[activeDomain] || 0) + timeSpent;

    chrome.storage.local.set({ timeData: data });
  });

  startTime = null;
}

// when active tab changes
chrome.tabs.onActivated.addListener(async activeInfo => {
  saveTime();

  const tab = await chrome.tabs.get(activeInfo.tabId);
  const domain = getDomain(tab.url);

  if (domain && isJobSite(domain)) {
    activeDomain = domain;
    startTime = Date.now();
  } else {
    activeDomain = null;
  }
});

// when tab URL changes
chrome.tabs.onUpdated.addListener((tabId, changeInfo, tab) => {
  if (changeInfo.status === "complete") {
    saveTime();

    const domain = getDomain(tab.url);
    if (domain && isJobSite(domain)) {
      activeDomain = domain;
      startTime = Date.now();
    } else {
      activeDomain = null;
    }
  }
});

// when user goes idle
chrome.idle.onStateChanged.addListener(state => {
  if (state !== "active") {
    saveTime();
    activeDomain = null;
  }
});

// when browser closes
chrome.runtime.onSuspend.addListener(() => {
  saveTime();
});
