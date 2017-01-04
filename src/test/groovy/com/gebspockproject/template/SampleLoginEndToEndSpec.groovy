package com.gebspockproject.template

import com.gebspockproject.template.annotations.EndToEnd
import com.gebspockproject.template.pages.SampleSecureAreaPage
import com.gebspockproject.template.targetpages.TargetPageType
import spock.lang.Unroll


/*
THIS SPEC IS AN EXAMPLE OF SPECIFYING/VERIFYING PAGE BEHAVIOR

IT ALSO CONTAINS AN EXAMPLE OF AN ANNOTATED SPEC (@EndToEnd)
 */

@EndToEnd
@Unroll
class SampleLoginEndToEndSpec extends SampleLoginBaseSpec {

    def "When the user enters valid credentials on the '#pageType.description()', the user must be sent to Secure Area Page"() {
        given:
        oneIsOnTheSampleLoginPage(pageType)

        when:
        loginWithValidUserCredentials()

        then:
        oneMustBeSentToTheSecureAreaPage()

        where:
        pageType << [TargetPageType.SAMPLE_LOGIN_PAGE,]

    }


    //=== STEPS ===//
    private oneMustBeSentToTheSecureAreaPage() {
        waitFor(5) {
            at SampleSecureAreaPage
        }
        // Still here? must return trueish so that this step can be used in then:
        return page
    }

}
