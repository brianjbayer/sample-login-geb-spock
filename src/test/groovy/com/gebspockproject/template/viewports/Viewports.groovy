package com.gebspockproject.template.viewports


import org.openqa.selenium.Dimension


class Viewports {

    //--- VIEWPORT DIMENSIONS AND OTHER CONSTANTS) ---//
    // Generic (Not Device Specific) Viewport Dimensions
    private static final GENERIC_HEIGHT = 768

    public static final MAX_MOBILE_WIDTH = 767
    static final GENERIC_MAX_MOBILE_BREAKPOINT = new Dimension(MAX_MOBILE_WIDTH, GENERIC_HEIGHT)

    public static final MIN_NONMOBILE_WIDTH = (MAX_MOBILE_WIDTH + 1)
    static final GENERIC_MIN_NONMOBILE_BREAKPOINT = new Dimension(MIN_NONMOBILE_WIDTH, GENERIC_HEIGHT)

    public static final MAX_TABLET_WIDTH = 999
    static final GENERIC_MAX_TABLET_BREAKPOINT = new Dimension(MAX_TABLET_WIDTH, GENERIC_HEIGHT)

    public static final MIN_DESKTOP_WIDTH = (MAX_TABLET_WIDTH + 1)
    static final GENERIC_MIN_DESKTOP_BREAKPOINT = new Dimension(MIN_DESKTOP_WIDTH, GENERIC_HEIGHT)


    //--- SET BREAKPOINT METHODS ---//
    // Generic (Not Device Specific)
    public static final setToGenericMaxMobileBreakpoint(driver) {
        driver.manage().window().setSize(GENERIC_MAX_MOBILE_BREAKPOINT)
    }

    public static final setToGenericMinNonMobileBreakpoint(driver) {
        driver.manage().window().setSize(GENERIC_MIN_NONMOBILE_BREAKPOINT)
    }

    public static final setToGenericMaxTabletBreakpoint(driver) {
        driver.manage().window().setSize(GENERIC_MAX_TABLET_BREAKPOINT)
    }

    public static final setToGenericMinDesktopBreakpoint(driver) {
        driver.manage().window().setSize(GENERIC_MIN_DESKTOP_BREAKPOINT)
    }


}

