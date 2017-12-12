package com.samplelogin.geb.spock

import com.samplelogin.geb.spock.targetpages.TargetPages
import com.samplelogin.geb.spock.pages.SampleLoginPage


/*
THIS SPEC IS AN EXAMPLE OF BASE/UTILITY SPEC
 */

class SampleLoginBaseSpec extends MetaGebReportingSpec {

    //=== STEPS ===//
    def oneIsOnTheSampleLoginPage(pageType) {
        go TargetPages.url(pageType)
        oneMustBeOnTheSampleLoginPage()
    }

    def oneMustBeOnTheSampleLoginPage() {
        waitFor(SampleLoginPage.SAMPLE_LOGIN_TIMEOUT) {
            at SampleLoginPage
        }
        // Still here? must return trueish so that this step can be used in then:
        return page
    }

}