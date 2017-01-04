import org.openqa.selenium.Dimension
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver
import org.openqa.selenium.phantomjs.PhantomJSDriverService
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.safari.SafariDriver

import java.util.logging.Level
import java.util.logging.Logger

//--- DEFAULT DRIVER ---//
// Default Driver is HtmlUnit

// Quiet HTMLUnit warnings
Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.SEVERE)
Logger.getLogger("org.openqa").setLevel(Level.SEVERE)

// Set default driver to HtmlUnit
driver = { new HtmlUnitDriver() }


//--- GEB ENVIRONMENT OVERRIDES ---//

environments {

    htmlunit {
        driver = {
            new HtmlUnitDriver()
        }
    }

    firefox {
        driver = {
            new FirefoxDriver()
        }
    }

    phantomjs {
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

    chrome {
        driver = {
            new ChromeDriver()
        }
    }

    safari {
        driver = {
            new SafariDriver()
        }
    }

}
