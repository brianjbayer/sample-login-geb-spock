package com.gebspockproject.template.annotations


class AnnotationsRegistry {
    /*********************************************************************************************************
     To add a new Annotation...

     1. Define the annotation (interface)

     2. "Register" the Annotation in any applicable annotations lists under //=== ANNOTATION LISTS ===//

     3. Add the constant for the annotation's name so that it can be specified via system property
        (command line) under //=== ANNOTATION NAMES ===//

     4. Add the mapping of the annotation name to annotation under //=== ANNOTATION NAME TO ANNOTATION LIST MAP ===//
     *********************************************************************************************************/

    //=== ANNOTATION LISTS ===//
    // "Register" any Breakpoint annotations here
    private static final allBreakpointAnnotations = [
            MobileBreakpoint,
            NonMobileBreakpoint,
            TabletBreakpoint,
            DesktopBreakpoint,
    ]

    // "Register" any annotations here that should be excluded from Running by default
    // Note that the actual exclusion of these annotations is the responsibility of the Spock Runner (e.g. SpockConfig)
    private static final recommendedDefaultAnnotationsToExclude = [
            WIP,
            OnDemand,
    ]

    // "Register" any annotations here that should be excluded when Running under HtmlUnit, which does not
    // support JS or viewport sizing
    // Note that the actual exclusion of these annotations is the responsibility of the Spock Runner (e.g. SpockConfig)
    private static final recommendedAnnotationsToExcludeUnderHtmlUnit = allBreakpointAnnotations + [ JavaScript ]


    // "Register" any annotations here that should be excluded from Running in a production level target environment
    // Note that the actual exclusion of these annotations is the responsibility of the Spock Runner (e.g. SpockConfig)
    private static final recommendedAnnotationsToExcludeWhenProductionLevelTarget = [
            DevelopmentLevelTargetOnly,
            ContentSpecific,
            AuthoringDependent,
    ]


    //=== ANNOTATION NAMES ===//
    // Register (define) the Annotation Name here...
    public static final MOBILE_BREAKPOINT_ANNOTATION_NAME              = normalizeAnnotationName('mobilebreakpoint')
    public static final NONMOBILE_BREAKPOINT_ANNOTATION_NAME           = normalizeAnnotationName('nonmobilebreakpoint')
    public static final TABLET_BREAKPOINT_ANNOTATION_NAME              = normalizeAnnotationName('tabletbreakpoint')
    public static final DESKTOP_BREAKPOINT_ANNOTATION_NAME             = normalizeAnnotationName('desktopbreakpoint')
    public static final WIP_ANNOTATION_NAME                            = normalizeAnnotationName('wip')
    public static final CONTENT_SPECIFIC_ANNOTATION_NAME               = normalizeAnnotationName('contentspecific')
    public static final JAVASCRIPT_ANNOTATION_NAME                     = normalizeAnnotationName('javascript')
    public static final DEVELOPMENT_LEVEL_TARGET_ONLY_ANNOTATION_NAME  = normalizeAnnotationName('developmentleveltargetonly')
    public static final AUTHORING_SPECIFIC_ANNOTATION_NAME             = normalizeAnnotationName('authoringdependent')
    public static final END_TO_END_ANNOTATION_NAME                     = normalizeAnnotationName('endtoend')
    public static final ON_DEMAND_ANNOTATION_NAME                      = normalizeAnnotationName('ondemand')


    // Special "All" Annotations Names
    public static final ALL_EXCLUDED_FROM_HTMLUNIT_ANNOTATION_NAME = normalizeAnnotationName('allexcludedfromhtmlunit')
    public static final ALL_BREAKPOINTS__ANNOTATION_NAME           = normalizeAnnotationName('allbreakpoints')


    //=== ANNOTATION NAME TO ANNOTATION LIST MAP ===//
    // "Register" the annotation(s) and its name here so that it can be specified by name (include/exclude)
    private static final nameToAnnotations = [
            (MOBILE_BREAKPOINT_ANNOTATION_NAME)            : [ MobileBreakpoint ],
            (NONMOBILE_BREAKPOINT_ANNOTATION_NAME)         : [ NonMobileBreakpoint ],
            (TABLET_BREAKPOINT_ANNOTATION_NAME)            : [ TabletBreakpoint ],
            (DESKTOP_BREAKPOINT_ANNOTATION_NAME)           : [ DesktopBreakpoint ],
            (WIP_ANNOTATION_NAME)                          : [ WIP ],
            (CONTENT_SPECIFIC_ANNOTATION_NAME)             : [ ContentSpecific],
            (JAVASCRIPT_ANNOTATION_NAME)                   : [ JavaScript ],
            (DEVELOPMENT_LEVEL_TARGET_ONLY_ANNOTATION_NAME): [ DevelopmentLevelTargetOnly ],
            (AUTHORING_SPECIFIC_ANNOTATION_NAME)           : [ AuthoringDependent ],
            (END_TO_END_ANNOTATION_NAME)                   : [ EndToEnd ],
            (ON_DEMAND_ANNOTATION_NAME)                    : [ OnDemand ],

            // Special "All" Annotations
            (ALL_EXCLUDED_FROM_HTMLUNIT_ANNOTATION_NAME): recommendedAnnotationsToExcludeUnderHtmlUnit,
            (ALL_BREAKPOINTS__ANNOTATION_NAME)          : allBreakpointAnnotations,
    ]


    //=== METHODS ===//
    public static final normalizeAnnotationName(name) {
        return name.trim().toLowerCase()
    }

    public static final getAnnotations(annotationName) {
        return nameToAnnotations.get(annotationName)
    }

    public static final getSetOfRecommendedDefaultAnnotationsToExclude(){
        return recommendedDefaultAnnotationsToExclude.toSet()
    }

    public static final getSetOfRecommendedAnnotationsToExcludeUnderHtmlUnit(){
        return recommendedAnnotationsToExcludeUnderHtmlUnit.toSet()
    }

    public static final getSetOfRecommendedAnnotationsToExcludeWhenProductionLevelTarget() {
        return recommendedAnnotationsToExcludeWhenProductionLevelTarget.toSet()
    }

}
