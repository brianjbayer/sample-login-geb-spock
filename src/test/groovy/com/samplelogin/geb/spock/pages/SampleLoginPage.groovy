package com.samplelogin.geb.spock.pages

import com.samplelogin.geb.spock.modules.SampleFindMeLinkModule

class SampleLoginPage extends BasePage {

    static url = '/login'

    // Page specific waits
    public static final TIMEOUT = 5
    static timeout() { SampleLoginPage.TIMEOUT }

    static at = { header.text() == 'Login Page' }

    static content = {
        findMeLink(wait: SampleLoginPage.timeout()) { module SampleFindMeLinkModule }

        header(wait: SampleLoginPage.timeout()) { $('h2') }

        loginForm(wait: SampleLoginPage.timeout()) { $('form[name=login]') }
        loginFormUsernameInput(wait: SampleLoginPage.timeout()) { loginForm.$('input[name=username]') }
        loginFormPasswordInput(wait: SampleLoginPage.timeout()) { loginForm.$('input[name=password]') }
        loginFormSubmitButton(wait: SampleLoginPage.timeout()) { loginForm.$('button[type=submit]') }

        login_error(wait: SampleLoginPage.timeout()) { $('#flash') }
    }

    def loginWithValidUserCredentials() {
        login(validUsername(), validPassword())
    }

    def loginWithInvalidPassword() {
        login(validUsername(), 'NotAValidPassword')
    }

    def login(username, password) {
        loginFormUsernameInput.value(username)
        loginFormPasswordInput.value(password)
        loginFormSubmitButton.click()
    }

    def getRequiredProperty(property) {
        final propertyValue = System.getProperty(property)
        if (!propertyValue) {
            throw new IllegalStateException("Required system property '$property' is not set!")
        }
        propertyValue
    }

    def validUsername() {
        // final validUsername = System.getProperty("geb.test.validUsername")
        getRequiredProperty('geb.test.validUsername')
    }

    def validPassword() {
        // System.getProperty("geb.test.validPassword")
        getRequiredProperty('geb.test.validPassword')
    }

}
