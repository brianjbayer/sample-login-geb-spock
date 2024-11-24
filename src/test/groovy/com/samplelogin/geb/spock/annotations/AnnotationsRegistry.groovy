package com.samplelogin.geb.spock.annotations

class AnnotationsRegistry {

    // --- ANNOTATION LISTS --- //
    // "Register" any Breakpoint annotations here
    private static final allBreakpointAnnotations = [
            MobileBreakpoint,
            NonMobileBreakpoint,
            TabletBreakpoint,
            DesktopBreakpoint,
    ]

    // "Register" any annotations here that should be excluded from Running by default
    public static final getSetOfRecommendedDefaultAnnotationsToExclude() {
        return [WIP].toSet()
    }

    // --- ANNOTATION NAMES --- //
    // Register (define) the Annotation Name here...
    public static final MOBILE_BREAKPOINT_ANNOTATION_NAME              = normalizeAnnotationName('mobilebreakpoint')
    public static final NONMOBILE_BREAKPOINT_ANNOTATION_NAME           = normalizeAnnotationName('nonmobilebreakpoint')
    public static final TABLET_BREAKPOINT_ANNOTATION_NAME              = normalizeAnnotationName('tabletbreakpoint')
    public static final DESKTOP_BREAKPOINT_ANNOTATION_NAME             = normalizeAnnotationName('desktopbreakpoint')
    public static final WIP_ANNOTATION_NAME                            = normalizeAnnotationName('wip')
    public static final END_TO_END_ANNOTATION_NAME                     = normalizeAnnotationName('endtoend')

    // Special "All" Annotations Names
    public static final ALL_BREAKPOINTS_ANNOTATION_NAME           = normalizeAnnotationName('allbreakpoints')

    // --- ANNOTATION NAME TO ANNOTATION LIST MAP--- //
    // "Register" the annotation(s) and its name here so that it can be specified by name (include/exclude)
    private static final nameToAnnotations = [
            (MOBILE_BREAKPOINT_ANNOTATION_NAME)            : [ MobileBreakpoint ],
            (NONMOBILE_BREAKPOINT_ANNOTATION_NAME)         : [ NonMobileBreakpoint ],
            (TABLET_BREAKPOINT_ANNOTATION_NAME)            : [ TabletBreakpoint ],
            (DESKTOP_BREAKPOINT_ANNOTATION_NAME)           : [ DesktopBreakpoint ],
            (WIP_ANNOTATION_NAME)                          : [ WIP ],
            (END_TO_END_ANNOTATION_NAME)                   : [ EndToEnd ],

            // Special "All" Annotations
            (ALL_BREAKPOINTS_ANNOTATION_NAME)          : allBreakpointAnnotations,
    ]

    //=== METHODS ===//
    public static final normalizeAnnotationName(name) {
        name.trim().toLowerCase()
    }

    public static final getAnnotations(annotationName) {
        nameToAnnotations.get(annotationName)
    }

}
