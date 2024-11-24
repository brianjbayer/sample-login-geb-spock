package com.samplelogin.geb.spock.pages

import geb.Page

class BasePage extends Page {

    // Default Timeout
    public static final TIMEOUT = 5
    static timeout() { BasePage.TIMEOUT }

    // Wait for at check by default
    static atCheckWaiting = BasePage.timeout()

}
