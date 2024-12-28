package com.samplelogin.geb.spock.modules

import geb.Module

class SampleFindMeLinkModule extends Module {

    public static final TIMEOUT = 5
    static timeout() { SampleFindMeLinkModule.TIMEOUT }

    static content = {
        githubLink(wait: SampleFindMeLinkModule.timeout()) { $('a', href: contains('https://github.com')) }
        forkMeImage(wait: SampleFindMeLinkModule.timeout()) { githubLink.$('img', src:contains('forkme')) }
    }

    def forkMeImageAltText() { forkMeImage.getAttribute('alt') }

    def mustBePresent() {
        // Assertions are sequential but dependent in this order
        assert githubLink
        assert forkMeImage
        assert 'Fork me on GitHub'.equals(forkMeImageAltText())
        // still here, return true for use in then
        true
    }

}
