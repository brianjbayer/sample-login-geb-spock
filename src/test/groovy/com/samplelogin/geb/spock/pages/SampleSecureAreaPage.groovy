package com.samplelogin.geb.spock.pages


/*
THIS IS AN EXAMPLE OF A VERY SIMPLE GEB PAGE (LANDING PAGE)
*/

class SampleSecureAreaPage extends BasePage {

    static at = { header.text().contains('Secure Area') }

    static content = {
        header { $('h2') }
    }

}
