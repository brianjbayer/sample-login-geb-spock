package com.gebspockproject.template

import geb.spock.GebReportingSpec
import geb.navigator.Navigator
import com.gebspockproject.template.viewports.Viewports


/*
The intent of this class is to dynamically extend the Geb Spec to make it more of a natural language
 */

class MetaGebReportingSpec extends GebReportingSpec {


    def setupSpec() {
        //=== EXTEND NAVIGATOR CLASS ---//
        Navigator.metaClass.getCount = { delegate.size() }

        Navigator.metaClass.isOnlyOne = { delegate.size() == 1 }
        Navigator.metaClass.hasAtLeastOne = { delegate.size() > 0 }
        Navigator.metaClass.isPresent = { delegate }
        Navigator.metaClass.isNotPresent = { ! delegate }

        Navigator.metaClass.eachHasOnlyOne = { delegate.every{ it.isOnlyOne() } }

        Navigator.metaClass.doesNotHaveClass = { ! delegate.hasClass(it) }

        //--- Displayed ---//
        Navigator.metaClass.isNotDisplayed = { ! delegate.isDisplayed() }
        Navigator.metaClass.eachAreDisplayed = { delegate.every{ it.isDisplayed() } }

        Navigator.metaClass.isHidden = { ! delegate.isDisplayed() }
        Navigator.metaClass.eachAreHidden = { delegate.every{ !it.isDisplayed() } }


        //--- Attributes ---//
        Navigator.metaClass.href = { delegate.getAttribute("href") }
        Navigator.metaClass.hrefs = { delegate.collect ( {it.href()} ) }


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

    //--- Wait Fors ---//
    // NOTE: The timeout exceptions from these methods are a tad more readable than those of metaprogramming
    //       method extensions on the Navigator class
    def mustBePresentEventually(element, timeout) {
        waitFor(timeout) {
            element
        }
        return element
    }
    def mustNotBePresentEventually(element, timeout) {
        waitFor(timeout) {
            !element
        }
        //Still here, must return true if used in then
        return true
    }


    def mustBeDisplayedEventually(element, timeout) {
        waitFor(timeout) {
            element.isDisplayed()
        }
        return element
    }
    def mustBeHiddenEventually(element, timeout) {
        waitFor(timeout) {
            ! element.isDisplayed()
        }
        return element
    }


    def eachMustBeDisplayedEventually(elements, timeout) {
        waitFor(timeout) {
            elements.every{ it.isDisplayed()}
        }
        return elements
    }


    //--- Others ---//
    def currentUrl() {
        return ( driver.getCurrentUrl() )
    }

}
