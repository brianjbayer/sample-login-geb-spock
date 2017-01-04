package com.gebspockproject.template.modules

import geb.Module

/*
THIS IS AN EXAMPLE OF A GEB MODULE USED FOR REPEATING CONTENT
 */

class SampleFindMeLinkModule extends Module {

    public static final FINDMELINK_TIMEOUT = 5

    static content = {

        //<a href="https://github.com/tourdedave/the-internet">...</a>
        githubLink(required: false) { $("a", href: contains("https://github.com")) }
            //<img style="position: absolute; top: 0; right: 0; border: 0;" src="/img/forkme_right_green_007200.png" alt="Fork me on GitHub">
            forkMeImage(required: false) { githubLink.$("img", src:contains("forkme") ) }
    }

    def forkMeImageAltText() { forkMeImage.getAttribute("alt") }
}