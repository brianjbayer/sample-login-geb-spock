package com.gebspockproject.template.targetpages


class TargetPages {
    /*********************************************************************************************************
    To add a new Target (environment)...

    1. Add the constant for the target (environment's) base URL under //=== TARGET BASE URLS ===//

    2. Add the constant for the target (environment's) name so that it can be specified via system property
       (command line) under //=== TARGET NAMES ===//

    3. Add the mapping of the target name to base url under //=== TARGET NAMES TO BASE URL MAP ===//
     *********************************************************************************************************/

    //=== TARGET BASE URLS ===//
    // Register (define) the Base URLs for the target (environments) here...

    //TODO: Configure (and add) project-specific BASE URLs

    //- Local -//
    // Assumes standard AEM Install Publish Instance port of 4503
    private static final LOCAL_BASE_URL = "http://localhost:4503"

    //- Dev -//
    private static final DEV_BASE_URL = "http://the-internet.herokuapp.com/"

    //- QA -//
    private static final QA_BASE_URL = "http://the-internet.herokuapp.com/"

    //- PRODUCTION -//
    private static final PRODUCTION_BASE_URL = "http://the-internet.herokuapp.com/"

    //- Default Base URL- //
    private static final DEFAULT_BASE_URL = DEV_BASE_URL


    //=== TARGET NAMES ===//
    // Register (define) the target (environment) names here...

    //TODO: Add any additional project-specific TARGET NAMES

    //- Local -//
    public static final LOCAL_TARGET_NAME = normalizeTargetName('local')

    //- Dev -//
    public static final DEV_TARGET_NAME = normalizeTargetName('dev')

    //- QA -//
    public static final QA_TARGET_NAME = normalizeTargetName('qa')

    //- PRODUCTION -//
    public static final PRODUCTION_TARGET_NAME = normalizeTargetName('production')


    //=== TARGET NAMES TO BASE URL MAP ===//
    // "Register" the target base URL and its name here so that it can be specified by name in system property
    private static final nameToTargetBaseURL = [

            //TODO: Register any additional project-specfic TARGET NAME TO BASE URL mappings

            //- Local -//
            (LOCAL_TARGET_NAME) : LOCAL_BASE_URL,

            //- Dev -//
            (DEV_TARGET_NAME) : DEV_BASE_URL,

            //-QA -//
            (QA_TARGET_NAME) : QA_BASE_URL,

            //- PRODUCTION -//
            (PRODUCTION_TARGET_NAME) : PRODUCTION_BASE_URL,
    ]

    private static final getBaseURL() {
        final normalizedTargetName = getNormalizedTargetValue()
        // If the System Property is unspecified, use default
        final targetBaseURL = (normalizedTargetName) ? getTargetBaseURL(normalizedTargetName) : DEFAULT_BASE_URL
        return targetBaseURL
    }

    private static final targetPagesBaseUrl = getBaseURL()


    //=== DEVELOPMENT LEVEL TARGET (TEST PACKAGE) URLS AND PAGES ===//
    // Add the Test Page URL here as well as any additionally needed contextual attributes for that URL here...
    private static final developmentLevelTargetTestPackageUrls = [

            //TODO: Add project-specific Development-Level Target Test URLs and their attributes

            //-- Sample Login Page --//
            // Here is an example of adding URL specific attributes (pageLoadTimeout)
            theSampleLoginPage:
                    [ url: "$targetPagesBaseUrl/login",
                      pageLoadTimeout: 10,
                    ],

            //-- Test Development Pages --//
            aPageThatShouldFail: [
                    url: "https://youtu.be/dQw4w9WgXcQ",
            ],
    ]

    // Associate the high-level test page type to a specific physical test page here...
    private static final developmentLevelTargetTestPackagePages = [

            //TODO: Add project-specific Development-Level Target association of Target Page Types to URLs

            //--- Sample Page With FindMeLink On It ---//
            (TargetPageType.SAMPLE_PAGE_WITH_FINDMELINK_PAGE) : developmentLevelTargetTestPackageUrls.theSampleLoginPage,

            //-- Sample Login Page --//
            (TargetPageType.SAMPLE_LOGIN_PAGE) : developmentLevelTargetTestPackageUrls.theSampleLoginPage,

            //-- Test Development Pages --//
            (TargetPageType.THIS_PAGE_SHOULD_FAIL) : developmentLevelTargetTestPackageUrls.aPageThatShouldFail,

    ] as EnumMap


    //=== NON-DEVELOPMENT LEVEL TARGET URLS AND PAGES ===//
    private static final nonDevelopmentLevelTargetUrls = [

            //TODO: Add project-specific Non-Development-Level Target URLs and their attributes

            //-- Sample Login Page --//
            theInternetSampleLoginPage:
                    [ url: "$targetPagesBaseUrl/login", ],
    ]

    private static final nonDevelopmentLevelTargetPages = [

            //TODO: Add project-specific Non-Development-Level Target association of Target Page Types to URLs

            //--- Sample Page With FindMeLink On It ---//
            (TargetPageType.SAMPLE_PAGE_WITH_FINDMELINK_PAGE) : nonDevelopmentLevelTargetUrls.theInternetSampleLoginPage,

            //-- Sample Login Page --//
            (TargetPageType.SAMPLE_LOGIN_PAGE) : nonDevelopmentLevelTargetUrls.theInternetSampleLoginPage,

    ] as EnumMap


    //=== PUBLIC METHODS ===//
    //--- Accessors ---//
    public static final get(key) {
        def page
        if ( isNonDevelopmentLevelTargetEnvironment() ) {
            page = nonDevelopmentLevelTargetPages.get(key)
        } else {
            page = developmentLevelTargetTestPackagePages.get(key)
        }
        return page
    }

    public static final url(key) { get(key).url }

    public static final getTargetBaseURL(targetName) {
        return nameToTargetBaseURL.get(targetName)
    }

    //--- Booleans ---//
    public static final isNonDevelopmentLevelTargetEnvironment() {
        final targetEnv = getNormalizedTargetValue()
        return (isProductionEnvironment(targetEnv) || isQAEnvironment(targetEnv))
    }

    //TODO: Implement any project-specfic target-related, context-determination boolean methods here


    //--- Others ---//
    public static final normalizeTargetName(name) {
        return name.trim().toLowerCase()
    }


    //=== PRIVATE METHODS ===//
    private static final isProductionEnvironment(targetEnv) {
        // ASSUMPTION: All production names start with the base production name (e.g. "production")
        return (targetEnv.contains(PRODUCTION_TARGET_NAME))
    }

    private static final isQAEnvironment(targetEnv) {
        // ASSUMPTION: All QA names start with the base qa name (e.g. "qa")
        return (targetEnv.contains(QA_TARGET_NAME))
    }


    private static final getNormalizedTargetValue() {
        final systemPropertyValue = System.getProperty("spock.run.target")
        final normalizedTargetValue = (systemPropertyValue) ? normalizeTargetName(systemPropertyValue) : ''
        return normalizedTargetValue
    }

}
