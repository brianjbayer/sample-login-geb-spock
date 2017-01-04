package com.gebspockproject.template.targetpages

public enum TargetPageType {
    //--- The Enumerations ---//
    // "Register" the high-level type of target page here...

    //--- Sample Page With FindMeLink On It ---//
    SAMPLE_PAGE_WITH_FINDMELINK_PAGE('Sample Page With FindMeLink'),

    //--- Sample Login Page ---//
    SAMPLE_LOGIN_PAGE('Sample Login Page'),


    //TODO: Add project-specific Target Page Type (equivalence class)


    //-- Test Development Pages --//
    THIS_PAGE_SHOULD_FAIL('This page should cause the test to fail'),

    //--- Attributes ---//
    private final description

    //--- Constructors ---//
    TargetPageType(description) { this.description = description }

    //--- Accessors ---//
    public description() { description }
}
