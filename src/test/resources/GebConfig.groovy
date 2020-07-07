import org.openqa.selenium.Dimension
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import org.testcontainers.containers.BrowserWebDriverContainer
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.phantomjs.PhantomJSDriver
import org.openqa.selenium.phantomjs.PhantomJSDriverService
import org.openqa.selenium.safari.SafariDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.logging.Level
import java.util.logging.Logger

//--- DRIVER ---//
// Quiet HTMLUnit warnings
Logger logger = Logger.getLogger("");
logger.setLevel(Level.OFF);

// There is No Default Driver - set as warning
driver = "THERE IS NO DEFAULT DRIVER, IT MUST BE SPECIFIED"

//--- GEB ENVIRONMENT OVERRIDES ---//
environments {

    //- The default but limited htmlunit -//
    htmlunit {
        driver = "htmlunit"
    }

    //- Docker Compose Containers -//
    seleniumchrome {
        driver = {
            def remoteWebDriverServerUrl = new URL("http://seleniumchrome:4444/wd/hub")
            new RemoteWebDriver(remoteWebDriverServerUrl, DesiredCapabilities.chrome())
        }
    }

    //- Selenium Browser Containers -//
    dockerChrome {
        driver = {
            def container = new BrowserWebDriverContainer()
                    .withCapabilities(new ChromeOptions())
            container.start()
            container.getWebDriver()
        }
    }
    dockerFirefox {
        driver = {
            def container = new BrowserWebDriverContainer()
                    .withCapabilities(new FirefoxOptions())
            container.start()
            container.getWebDriver()
        }
    }

    //- Headless Browsers -//
    chromeHeadless {
        WebDriverManager.chromedriver().setup()
        driver = {
            ChromeOptions o = new ChromeOptions()
            o.setHeadless(true)
            new ChromeDriver(o)
        }
    }
    firefoxHeadless {
        WebDriverManager.firefoxdriver().setup()
        driver = {
            FirefoxOptions o = new FirefoxOptions()
            o.setHeadless(true)
            new FirefoxDriver(o)
        }
    }
    phantomjs {
        WebDriverManager.phantomjs().setup()
        driver = {
            // Set up the PhantomJS Command Line Arguments to allow for SSL (https sites)
            // This recipe is modified from http://blog.swwomm.com/2014/10/phantomjs-and-ssl.html
            final phantomjsSslCliArgs = [
                    "--web-security=false",
                    "--ssl-protocol=any",
                    "--ignore-ssl-errors=true"
            ]
            def desiredCapabilities = DesiredCapabilities.phantomjs()
            desiredCapabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, phantomjsSslCliArgs)
            def phantomJsDriver = new PhantomJSDriver(desiredCapabilities)
            // PhantomJS runs much faster if an explicit viewport size is set so...
            phantomJsDriver.manage().window().setSize(new Dimension(1028, 768))
            return phantomJsDriver
        }
    }

    //- Local Browsers -//
    chrome {
        WebDriverManager.chromedriver().setup()
        driver = "chrome"
    }
    firefox {
        WebDriverManager.firefoxdriver().setup()
        driver = "firefox"
    }
    safari {
        driver = {
            new SafariDriver()
        }
    }

}
