## Prerequisites

In order to run this project...

You must have Docker installed and running on your host machine to run the project using the docker compose framework

### Prerequisites for Running the Dependency Security Scan

> This project uses the Open Worldwide Application Security Project ([OWASP](https://owasp.org/))
> [`dependency-check-maven`](https://dependency-check.github.io/DependencyCheck/)
> package to perform the dependency security scanning

In order to run the project's dependency security scanning,
you will most likely need to set your own
National Vulnerability Database (NVD) API Key to pull
the vulnerability database used to scan against the
project's dependencies.  Without this key being set, the
scan will either fail with a `403` or similar unauthorized
return code or the download will be throttled with a message
like this...

```text
[WARNING] An NVD API Key was not provided - it is highly recommended to use an NVD API key as the update can take a VERY long time without an API Key
```

To set the `NVD_API_KEY` environment variable and value...

```sh
NVD_API_KEY=<your-key-here>
```

#### Getting an NVD API Key

The National Vulnerability Database is maintained by the
[National Institute of Standards and Technology](https://www.nist.gov/) (NIST)

To get an NVD API key...

1. Request one online at NVD Developers
   [Request an API Key](https://nvd.nist.gov/developers/request-an-api-key)

   :email: You will need at least you email address

2. You will receive an email at that address with an expiring link
   that redirect you to *activate your API Key*

3. Be sure to click on that link before it expires and store
   you key in a secure place like a password manager

4. Verify your key, substituting your activated NVD API Key below...

   ```sh
   export NVD_API_KEY=<your-nvd-api-key> && \
     curl -s -o /dev/null -w "%{http_code}\n" \
     -H "Accept: application/json" \
     -H "apiKey: $NVD_API_KEY" \
     "https://services.nvd.nist.gov/rest/json/cves/2.0?cpeName=cpe:2.3:o:microsoft:windows_10:1607:*:*:*:*:*:*:*"
   ```

   :green_circle: You should get a returned `200` response code

   > The `cpeName=cpe:2.3:o:microsoft:windows_10:1607:*:*:*:*:*:*:*`
   > is just a known vulnerability to find using your key.

      ```sh
   export NVD_API_KEY=xxxxxx-yyyy-zz && \
     curl -s -o /dev/null -w "%{http_code}\n" \
     -H "Accept: application/json" \
     -H "apiKey: $NVD_API_KEY" \
     "https://services.nvd.nist.gov/rest/json/cves/2.0?cpeName=cpe:2.3:o:microsoft:windows_10:1607:*:*:*:*:*:*:*"

   200
   ```

### Prerequisites for Running the Tests

To run the tests, you must set the following required
environment variables...

1. Set the required `BROWSER` environment variable and value...

   ```sh
   BROWSER=chrome
   ```

2. Set the required `BASE_URL` environment variable and value...

   ```sh
   BASE_URL='https://the-internet.herokuapp.com'
   ```

3. Set the required `LOGIN_USERNAME` secret environment variable and value...

   ```sh
   LOGIN_USERNAME=tomsmith
   ```

4. Set the required `LOGIN_PASSWORD` secret environment variable and value...

   ```sh
   LOGIN_PASSWORD=SuperSecretPassword!
   ```

> These are publicly available values but demonstrate
> basic secret management

### Optional: Creating a `.env` File

You can create a file named `.env` in the project root directory
that contains the required environment variables that will
be used by default by docker compose instead of setting them on
the command line...

> :no_entry_sign: However your `.env` file will not be used when running natively

```
BASE_URL='https://the-internet.herokuapp.com'
LOGIN_USERNAME=tomsmith
LOGIN_PASSWORD=SuperSecretPassword!
```

You can also add you `NVD_API_KEY` as well.
