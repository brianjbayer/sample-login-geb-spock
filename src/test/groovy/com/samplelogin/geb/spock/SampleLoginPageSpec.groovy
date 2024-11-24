package com.samplelogin.geb.spock

import com.samplelogin.geb.spock.pages.SampleLoginPage

import spock.lang.Unroll

@Unroll
class SampleLoginPageSpec extends MetaGebReportingSpec {

    def "There must be a single displayed FindMeLink"() {
        when:
        to SampleLoginPage

        then:
        findMeLink.mustBePresent()
    }

}
