package com.gebspockproject.template.pages

/*
THIS IS AN EXAMPLE OF A GEB PAGE WHICH INHERITS CONTENT

IT ALSO CONTAINS A CONTENT METHOD
*/

class SampleLoginPage extends SamplePageWithFindMeLinkPage {

    public static final SAMPLE_LOGIN_TIMEOUT = 5

    static at = {  header.text() == "Login Page" }

    static content= {

        header { $("h2") }
        
        loginForm { $("form[name=login]") }
        loginFormUsernameInput { loginForm.$("input[name=username]") }
        loginFormPasswordInput { loginForm.$("input[name=password]") }
        loginFormSubmitButton  { loginForm.$("button[type=submit]") }

    }


    def loginWithValidUserCredentials() {
        loginFormUsernameInput.value("tomsmith")
        loginFormPasswordInput.value("SuperSecretPassword!")
        loginFormSubmitButton.click()
    }
}