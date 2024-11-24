package com.samplelogin.geb.spock

import com.samplelogin.geb.spock.annotations.EndToEnd
import com.samplelogin.geb.spock.pages.SampleLoginPage
import com.samplelogin.geb.spock.pages.SampleSecureAreaPage

import spock.lang.Unroll

@Unroll
class SampleLoginSpec extends MetaGebReportingSpec {

    @EndToEnd
    def "When the user enters valid credentials, the user must be sent to Secure Area Page"() {
        given:
        to SampleLoginPage

        when:
        // login("tomsmith", "SuperSecretPassword!")
        loginWithValidUserCredentials()

        then:
        at SampleSecureAreaPage
    }

    def "When the user enters invalid credentials, an error message is displayed"() {
    given:
    to SampleLoginPage

    when:
    loginWithInvalidPassword()

    then:
    at SampleLoginPage

    and:
    login_error
}

}
