package com.gebspockproject.template.pages

import com.gebspockproject.template.modules.SampleFindMeLinkModule
import geb.Page

/*
THIS IS AN EXAMPLE OF A (GENERIC) GEB PAGE WITH A MODULE
 */

class SamplePageWithFindMeLinkPage extends Page {

    static content= {

        findMeLink(required:false) { module SampleFindMeLinkModule }

    }

}