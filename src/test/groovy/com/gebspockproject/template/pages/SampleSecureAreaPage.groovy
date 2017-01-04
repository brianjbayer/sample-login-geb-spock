package com.gebspockproject.template.pages

import geb.Page

/*
THIS IS AN EXAMPLE OF A VERY SIMPLE GEB PAGE (LANDING PAGE)
*/

class SampleSecureAreaPage extends Page {

    static at = {  header.text() == "Secure Area" }

    static content= {

        header { $("h2") }

    }

}