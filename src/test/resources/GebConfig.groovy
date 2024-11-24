import org.openqa.selenium.remote.RemoteWebDriver
import java.util.logging.Level
import java.util.logging.Logger

//--- DRIVER ---//
// Quiet some warnings
Logger logger = Logger.getLogger('')
logger.setLevel(Level.OFF)

browser = getBrowser()
browserOptions = browserOptions(browser)

remoteURL = getRemoteURL()

if (remoteURL) {
    driver = { remoteBrowser(browserOptions, remoteURL) }
} else {
    driver = { localBrowser(browserOptions) }
}

//--- METHODS ---//
def remoteBrowser(browserOptions, url) {
    final remoteWebDriverServerUrl = new URL(url)
    new RemoteWebDriver(remoteWebDriverServerUrl, browserOptions)
}

def localBrowser(browserOptions) {
    final browserDriverClassName = "org.openqa.selenium.${browser}.${browser.capitalize()}Driver"
    browserDriver = this.class.classLoader.loadClass( browserDriverClassName, true, false )?.newInstance(browserOptions)
}

def browserOptions(browser) {
    final browserOptionsClassName = "org.openqa.selenium.${browser}.${browser.capitalize()}Options"
    browserOptions = this.class.classLoader.loadClass( browserOptionsClassName, true, false )?.newInstance()
    if (isHeadless()) {
        browserOptions.addArguments('--headless')
    }
    browserOptions
}

def getBrowser() {
    System.getProperty('geb.browser', '').toLowerCase()
}

def getRemoteURL() {
    System.getProperty('geb.remoteUrl')
}

def isHeadless() {
    System.getProperty('geb.headless') && true
}
