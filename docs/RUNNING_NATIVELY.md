## Running the Automated Tests Natively and Environment Variables
Assuming that you have a Maven/Java/Groovy development environment,
the tests can be run natively by `maven`.

### Prerequisites
* Java 16
* Maven 3.8
* To run the tests using a specific browser requires that browser
be installed
(e.g. to run the tests in the Chrome Browser requires
Chrome be installed).

### Environment Variables
#### Required Environment Variables and Secrets
For the required secrets and other environment variables,
see the [PREREQUISITES.md](PREREQUISITES.md)

#### Specify Browser
`BROWSER=`...

**Example:**
`BROWSER=chrome`

Mostly, this uses a _pass-through_ approach and should support any
valid `Selenium::WebDriver` browser.

The following browsers were working on Mac at the time of this commit...
* `chrome` - Google Chrome (requires Chrome)
* `edge` - Microsoft Edge (requires Edge)
* `firefox` - Mozilla Firefox (requires Firefox)
* `safari` - Apple Safari (local only, requires Safari)

> This project uses the
> [Selenium Manager](https://www.selenium.dev/blog/2022/introducing-selenium-manager/)
> facility built into the `selenium-webdriver` gem to automatically
> download and maintain chromedriver, edgedriver,
> and geckodriver (Firefox).

#### Specify Headless
`HEADLESS=`...

> **The `HEADLESS` environment variable is ignored if the `BROWSER`
> environment variable is not provided (i.e. set)**

**Example:**
`HEADLESS=true`

> The headless specification is implemented as _truthy_
> and ignores case.  Setting `HEADLESS` to any value
> is interpreted as `true`

#### Specify Remote (Container) URL
`REMOTE_URL=`...

Specifying a Remote URL creates a remote browser of type
specified by `BROWSER` at the specified remote URL

 **Example:**
`REMOTE_URL='http://localhost:4444/wd/hub'`

#### Using the Selenium Standalone Containers
Like the docker-compose framework, these tests can be run natively
using the Selenium Standalone containers and the VNC Server
if you want.

For specifics, see the Selenium Standalone Image
[documentation](https://github.com/SeleniumHQ/docker-selenium).

1. Run the Selenium Standalone image with standard port and volume mapping...
   ```
   docker run -d -p 4444:4444 -p 5900:5900 -p 7900:7900 -v /dev/shm:/dev/shm selenium/standalone-chromium
   ```
2. If you want, launch the VNC client in app or browser
3. Run the tests specifying the remote Selenium container...
   ```
   REMOTE_URL='http://localhost:4444/wd/hub' BROWSER=chrome mvn clean test
   ```
