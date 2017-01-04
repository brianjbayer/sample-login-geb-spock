package com.gebspockproject.template


import com.gebspockproject.template.pages.SampleLoginPage
import com.gebspockproject.template.targetpages.TargetPageType
import spock.lang.Unroll


/*
THIS SPEC IS AN EXAMPLE OF SPECIFYING/VERIFYING THE CONTENT A PAGE

IT ALSO IS AN EXAMPLE OF SPEC WHICH INHERITS FROM A BASE/UTILITY SPEC
 */

@Unroll
class SampleLoginPageSpec extends SampleLoginBaseSpec {


    def "There must be a single displayed FindMeLink on the '#pageType.description()'"() {
        when:
        oneIsOnTheSampleLoginPage(pageType)

        then:
        final findMeLink = mustBePresentEventually(findMeLink, SampleLoginPage.SAMPLE_LOGIN_TIMEOUT)

        and:
        findMeLink.isOnlyOne()

        and:
        mustBeDisplayedEventually(findMeLink, SampleLoginPage.SAMPLE_LOGIN_TIMEOUT)

        where:
        pageType << [TargetPageType.SAMPLE_LOGIN_PAGE,]

    }

    def "There must be a single displayed Login Form with single displayed username and password inputs and a single displayed Login Button on the '#pageType.description()'"() {
        when:
        oneIsOnTheSampleLoginPage(pageType)

        then:
        final theloginForm = mustBePresentEventually(loginForm, SampleLoginPage.SAMPLE_LOGIN_TIMEOUT)
        theloginForm.isOnlyOne()
        mustBeDisplayedEventually(theloginForm, SampleLoginPage.SAMPLE_LOGIN_TIMEOUT)

        and:
        final theUsernameInput = mustBePresentEventually(loginFormUsernameInput, SampleLoginPage.SAMPLE_LOGIN_TIMEOUT)
        theUsernameInput.isOnlyOne()
        mustBeDisplayedEventually(theUsernameInput, SampleLoginPage.SAMPLE_LOGIN_TIMEOUT)

        and:
        final thePasswordInput = mustBePresentEventually(loginFormPasswordInput, SampleLoginPage.SAMPLE_LOGIN_TIMEOUT)
        thePasswordInput.isOnlyOne()
        mustBeDisplayedEventually(thePasswordInput, SampleLoginPage.SAMPLE_LOGIN_TIMEOUT)

        and:
        final theLoginButton = mustBePresentEventually(loginFormSubmitButton, SampleLoginPage.SAMPLE_LOGIN_TIMEOUT)
        theLoginButton.isOnlyOne()
        mustBeDisplayedEventually(theLoginButton, SampleLoginPage.SAMPLE_LOGIN_TIMEOUT)

        where:
        pageType << [TargetPageType.SAMPLE_LOGIN_PAGE,]

    }

}
