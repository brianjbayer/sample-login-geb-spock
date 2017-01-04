import com.gebspockproject.template.annotations.AnnotationsRegistry
import com.gebspockproject.template.targetpages.TargetPages

/***********************************************************************************************************
 *
 * SYSTEM PROPERTY SWITCHES
 *   spock.run.target : specifies target environment/region to execute tests against (e.g. dev, local, staging)
 *   spock.run.includes : specifies comma separated list of annotation names to be executed
 *   spock.run.excludes : specifies comma separated list of annotation names to be excluded from being executed
 *   spock.run.excludes.context=ignore : disables context-specific exclusion
 *
 *   SCENARIOS...
 *   === REGRESSION (NAIVE USER) ===
 *   = Run Fast (Hybrid) =
 *
 *   // Run all tests except those excluded by context (default & HtmlUnit)
 *   $ mvn clean test
 *
 *   // Run all tests locally except those excluded by context (default & HtmlUnit etc.)
 *   $ mvn clean test -Dspock.run.target=local
 *
 *   // Run only those tests excluded by HtmlUnit context except those excluded by context (default)
 *   $ mvn clean test -Dgeb.env=safari -Dspock.run.includes=allexcludedfromhtmlunit -Dspock.run.target=production
 *
 *   // Run only those tests excluded by HtmlUnit context except those excluded by context (default & Non-SignIn etc)
 *   $ mvn clean test -Dgeb.env=safari -Dspock.run.includes=allexcludedfromhtmlunit -Dspock.run.target=local
 *
 *    = Run Allish =
 *   // Run all test except those excluded by context (default)
 *   $ mvn clean test -Dgeb.env=safari -Dspock.run.target=qa
 *
 *   // Run all test except those excluded by context (default & Non-SignIn etc)
 *   $ mvn clean test -Dgeb.env=safari -Dspock.run.target=local
 *
 *
 *   === TEST DEVELOPMENT (TEST DEVELOPER) ===
 *   // Run only WIP tests except those excluded by context (HtmlUnit)
 *   $ mvn clean test -Dspock.run.includes=wip
 *
 *   // Run ALL WIP tests disabling any context exclusion
 *   $ mvn clean test -Dspock.run.includes=wip -Dspock.run.excludes.context=ignore
 *
 *
 *   === INCLUDE & EXCLUDE (POWER USER) ===
 *
 *   = INCLUDE SET = EXCLUDE SET =
 *   // Run no tests
 *   $ mvn clean test -Dspock.run.includes=contentspecific -Dspock.run.excludes=contentspecific
 *
 *   = INCLUDE and EXCLUDE SET =
 *   // Run only mobilebreakpoint tests (which should fail with Htmlunit) excluding content-specific annotated tests and
 *   // those excluded by context (default, Htmlunit, Non-SignIn etc)
 *   $ mvn clean test -Dspock.run.includes=mobilebreakpoint -Dspock.run.excludes=contentspecific -Dspock.run.target=local
 *
 *
 */


//--------------//
//--- RUNNER ---//
//--------------//

runner {
    //------------------------------------------------------------//
    println "[runner] Determining set of annotations to include..."
    //------------------------------------------------------------//

    final setOfAnnotationsSpecifiedToInclude = getSetOfAnnotationsSpecifiedToInclude()
    println "[runner] ...set of annotations specified to include: [${setOfAnnotationsSpecifiedToInclude.join(", ")}]"

    println "[runner] Set Of Annotations To Be Included: [${setOfAnnotationsSpecifiedToInclude.join(", ")}]"


    //------------------------------------------------------------//
    println "[runner] Determining set of annotations to exclude..."
    //------------------------------------------------------------//

    final setOfAnnotationsSpecifiedToExclude = getSetOfAnnotationsSpecifiedToExclude()
    println "[runner] ...set of annotations specified to exclude: [${setOfAnnotationsSpecifiedToExclude.join(", ")}]"

    println "[runner] ...determining set of any optional context-specific annotations to exclude..."
    final setOfOptionalContextSpecificAnnotationsToExclude = getSetOfOptionalContextSpecificAnnotationsToExclude()
    println "[runner] ...set of optional context-specific annotations to exclude: [${setOfOptionalContextSpecificAnnotationsToExclude.join(", ")}]"

    println "[runner] ...adjusting optional context-specific annotations to exclude (removing any annotations specified to be included)..."
    final adjustedSetOfOptionalContextSpecificAnnotationsToExclude = setOfOptionalContextSpecificAnnotationsToExclude - setOfAnnotationsSpecifiedToInclude
    println "[runner] ...adjusted context-specific annotations to exclude: [${adjustedSetOfOptionalContextSpecificAnnotationsToExclude.join(", ")}]"

    println "[runner] ...determining set of effective annotations to exclude (specified + context-specific )..."
    final setOfEffectiveAnnotationsToExclude = setOfAnnotationsSpecifiedToExclude + adjustedSetOfOptionalContextSpecificAnnotationsToExclude

    println "[runner] Set of Annotations To Be Excluded: [${setOfEffectiveAnnotationsToExclude.join(", ")}]"


    //-----------------------------------------------------------------//
    println "[runner] Including (running) any tests with annotations..."
    //-----------------------------------------------------------------//
    //- Include the Specified Include Annotations  -//
    if (setOfAnnotationsSpecifiedToInclude) {
        includeOrExcludeAnnotations(include,
                setOfAnnotationsSpecifiedToInclude,
                "[runner] \tincluding annotation: ")
    }

    //-------------------------------------------------------------------//
    println "[runner] Excluding any tests from running with annotations..."
    //-------------------------------------------------------------------//
    if (setOfEffectiveAnnotationsToExclude) {
        includeOrExcludeAnnotations(exclude,
                setOfEffectiveAnnotationsToExclude,
                "[runner] \texcluding annotation: ")
    }

    //-------------------------------------------------------------------//
    println "[runner] Executing Tests..."
    //-------------------------------------------------------------------//
}


//---------------//
//--- METHODS ---//
//---------------//
//--- ANNOTATION SET BUILDER METHODS ---//
def getSetOfAnnotationsSpecifiedToInclude() {
    return getSetOfAnnotationsFromSystemProperty("spock.run.includes")
}

def getSetOfAnnotationsSpecifiedToExclude() {
    return getSetOfAnnotationsFromSystemProperty("spock.run.excludes")
}

def getSetOfOptionalContextSpecificAnnotationsToExclude() {
    if (isIgnoreContext()) {
        println "[runner] ......ignoring context as specified"
        return [] as Set
    }

    def setOfContextSpecificAnnotationsToExclude = [] as Set

    // Exclude any recommended default annotated tests from running
    final defaultExcludesSet = AnnotationsRegistry.getSetOfRecommendedDefaultAnnotationsToExclude()
    println "[runner] ......set of default context-specific annotations to exclude: [${defaultExcludesSet.join(", ")}]"
    setOfContextSpecificAnnotationsToExclude.addAll(defaultExcludesSet)

    // Exclude any recommended tests from running under HtmlUnit which does not support all browser functionality
    if (isDriverDefaultOrHtmlUnit()) {
        final htmlUnitExcludesSet = AnnotationsRegistry.getSetOfRecommendedAnnotationsToExcludeUnderHtmlUnit()
        println "[runner] ......set of HtmlUnit context-specific annotations to exclude: [${htmlUnitExcludesSet.join(", ")}]"
        setOfContextSpecificAnnotationsToExclude.addAll(htmlUnitExcludesSet)
    }

    // Exclude any recommended tests from running when Non-Development Level target environment
    //TODO: This may be removed if not using different target (relative) URLs (Test Package) in Non-Development target environments
    final boolean isNonDevelopmentLevelTargetEnvironment = ( TargetPages.isNonDevelopmentLevelTargetEnvironment() )
    if (isNonDevelopmentLevelTargetEnvironment) {
        final nonDevelopmentTargetExcludesSet = AnnotationsRegistry.getSetOfRecommendedAnnotationsToExcludeWhenProductionLevelTarget()
        println "[runner] ......set of Non-Development Level Target environment context-specific annotations to exclude: [${nonDevelopmentTargetExcludesSet.join(", ")}]"
        setOfContextSpecificAnnotationsToExclude.addAll(nonDevelopmentTargetExcludesSet)
    }

    //TODO: Add any project-specific context excludes here

    return setOfContextSpecificAnnotationsToExclude
}


//--- CONTEXT DETERMINATION METHODS ---//
boolean isDriverDefaultOrHtmlUnit() {
    // This assumes that the default driver is HtmlUnit
    final gebEnv = System.getProperty("geb.env")
    return ( (! gebEnv) || gebEnv.contains("htmlunit") )
}


boolean isIgnoreContext() {
    final excludesContext = System.getProperty('spock.run.excludes.context')

    return excludesContext && excludesContext.trim() == 'ignore'
}


//--- INCLUDE/EXCLUDE RELATED METHODS ---//
//NOTE: because include/exclude are part of the runner DSL/closure they have to be passed here to the helper-method
def includeOrExcludeAnnotations(includeOrExclude, annotations, eachMsg) {
    annotations.each {
        if (eachMsg) { println "${eachMsg}${it}" }
        includeOrExclude.annotations << it
    }
}


//--- ANNOTATION METHODS ---//
def getSetOfAnnotationsFromSystemProperty(propertyKey) {

    final rawAnnotationNamesList = getAnnotationsNamesListFromSystemProperty(propertyKey)

    final annotationsSet = getAnnotationsSetFrom(rawAnnotationNamesList)
    return annotationsSet
}

def getAnnotationsNamesListFromSystemProperty(propertyKey) {
    final rawAnnotationNamesString = System.getProperty(propertyKey)

    // ASSUMPTION: comma separated list of annotation names
    final rawAnnotationNamesArray = (rawAnnotationNamesString) ? rawAnnotationNamesString.split(',') : []
    return rawAnnotationNamesArray.toList()
}

def getAnnotationsSetFrom(annotationsNamesList) {
    // Convert the Names List to a Set (unique) -- Keep original list if ever desired for debugging/logging
    def annotationsNamesSet = annotationsNamesList.toSet()

    def annotationsSet = [] as Set
    annotationsNamesSet.each {
        final normalizedAnnotationName = AnnotationsRegistry.normalizeAnnotationName(it)
        final validAnnotations = AnnotationsRegistry.getAnnotations(normalizedAnnotationName)
        if (validAnnotations) {
            validAnnotations.each {
                annotationsSet.add(it)
            }
        } else {
            println "[runner] IGNORING Invalid Annotation Name: '$it' ($normalizedAnnotationName)"
        }
    }
    return annotationsSet
}
