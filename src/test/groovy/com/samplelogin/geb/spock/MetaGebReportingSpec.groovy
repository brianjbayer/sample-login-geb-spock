package com.samplelogin.geb.spock

import geb.spock.GebReportingSpec
import geb.navigator.Navigator
import com.samplelogin.geb.spock.viewports.Viewports

// Intended to extend the Geb Spec to make it more of a natural language

class MetaGebReportingSpec extends GebReportingSpec {

    def setupSpec() {
        //=== EXTEND NAVIGATOR CLASS ---//
        Navigator.metaClass.getCount = { delegate.size() }

        Navigator.metaClass.isOnlyOne = { delegate.size() == 1 }
        Navigator.metaClass.hasAtLeastOne = { delegate.size() > 0 }
        Navigator.metaClass.isPresent = { delegate }
        Navigator.metaClass.isNotPresent = { ! delegate }

        Navigator.metaClass.eachHasOnlyOne = { delegate.every { it.isOnlyOne() } }

        Navigator.metaClass.doesNotHaveClass = { ! delegate.hasClass(it) }

        //--- Displayed ---//
        Navigator.metaClass.isNotDisplayed = { ! delegate.isDisplayed() }
        Navigator.metaClass.eachAreDisplayed = { delegate.every { it.isDisplayed() } }

        Navigator.metaClass.isHidden = { ! delegate.isDisplayed() }
        Navigator.metaClass.eachAreHidden = { delegate.every { !it.isDisplayed() } }

        //--- Attributes ---//
        Navigator.metaClass.href = { delegate.getAttribute('href') }
        Navigator.metaClass.hrefs = { delegate.collect({ it.href() }) }

        //=== EXTEND LIST CLASS ===//
        List.metaClass.doesNotContain = { elem -> return (! delegate.contains(elem)) }

        //=== EXTEND STRING CLASS //
        String.metaClass.isNotEmpty = { ! delegate.isEmpty() }
    }

    //=== METHODS ===//
    //--- Set Specific Breakpoints ---//
    def theViewportIsAtTheMobileBreakpoint() {
        Viewports.setToGenericMaxMobileBreakpoint(driver)
    }

    def theViewportIsAtTheNonMobileBreakpoint() {
        Viewports.setToGenericMinNonMobileBreakpoint(driver)
    }

    def theViewportIsAtTheTabletBreakpoint() {
        Viewports.setToGenericMaxTabletBreakpoint(driver)
    }

    def theViewportIsAtTheDesktopBreakpoint() {
        Viewports.setToGenericMinDesktopBreakpoint(driver)
    }

    //--- Others ---//
    def currentUrl() {
        return (driver.getCurrentUrl())
    }

}
