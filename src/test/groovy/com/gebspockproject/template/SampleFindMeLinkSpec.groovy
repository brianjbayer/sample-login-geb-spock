package com.gebspockproject.template

import com.gebspockproject.template.modules.SampleFindMeLinkModule
import com.gebspockproject.template.pages.SampleLoginPage
import com.gebspockproject.template.pages.SamplePageWithFindMeLinkPage
import com.gebspockproject.template.targetpages.TargetPageType
import com.gebspockproject.template.targetpages.TargetPages
import spock.lang.Unroll

/*
THIS SPEC IS AN EXAMPLE OF SPECIFYING/VERIFYING THE CONTENT A MODULE

IT ALSO CONTAINS AN EXAMPLE OF USING TARGET PAGE SPECIFIC ATTRIBUTE
 */

@Unroll
class SampleFindMeLinkSpec extends SampleLoginBaseSpec {

    def "There must be a single displayed link to github in the FindMeLink: '#pageType.description()'"() {
        when:
        final findMeLink = oneIsOnAPageWithASampleFindMeLink(pageType)

        then:
        final theGitHubLink = mustBePresentEventually(findMeLink.githubLink, SampleFindMeLinkModule.FINDMELINK_TIMEOUT)

        and:
        theGitHubLink.isOnlyOne()

        and:
        mustBeDisplayedEventually(theGitHubLink, SampleLoginPage.SAMPLE_LOGIN_TIMEOUT)

        where:
        pageType << [TargetPageType.SAMPLE_PAGE_WITH_FINDMELINK_PAGE,]

    }

    def "There must be a single displayed \"fork me image\" in the FindMeLink: '#pageType.description()'"() {
        when:
        final findMeLink = oneIsOnAPageWithASampleFindMeLink(pageType)

        then:
        final theForkMeImage = mustBePresentEventually(findMeLink.forkMeImage, SampleFindMeLinkModule.FINDMELINK_TIMEOUT)

        and:
        theForkMeImage.isOnlyOne()

        and:
        mustBeDisplayedEventually(theForkMeImage, SampleLoginPage.SAMPLE_LOGIN_TIMEOUT)

        where:
        pageType << [TargetPageType.SAMPLE_PAGE_WITH_FINDMELINK_PAGE,]

    }

    def "The \"fork me image\" Alt Text must be \"Fork me on GitHub\"in the FindMeLink: '#pageType.description()'"() {
        given:
        final findMeLink = oneIsOnAPageWithASampleFindMeLink(pageType)

        when:
        mustBePresentEventually(findMeLink.forkMeImage, SampleFindMeLinkModule.FINDMELINK_TIMEOUT)

        then:
        "Fork me on GitHub".equals(findMeLink.forkMeImageAltText())

        where:
        pageType << [TargetPageType.SAMPLE_PAGE_WITH_FINDMELINK_PAGE,]

    }


    //=== STEPS ===//
    private oneIsOnAPageWithASampleFindMeLink(pageType) {
        // Here is an example of using a target page (url) specific attribute (pageLoadTimeout)
        final targetPage = TargetPages.get(pageType)
        final targetPageURL = targetPage.url
        final targetPageLoadTimeout = (targetPage.pageLoadTimeout) ? targetPage.pageLoadTimeout : 15
        go targetPageURL
        // ASSUMPTION: No redirects
        waitFor(targetPageLoadTimeout) {
            targetPageURL == currentUrl()
        }
        // Set the page context
        page(SamplePageWithFindMeLinkPage)

        // Ensure FindMeLink is present on this page
        final findMeLink = mustBePresentEventually(findMeLink, SampleFindMeLinkModule.FINDMELINK_TIMEOUT)

        return findMeLink
    }


}