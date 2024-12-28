import com.samplelogin.geb.spock.annotations.AnnotationsRegistry

//--- RUNNER ---//
runner {
    println 'Determining Tests...'
    final setOfAnnotationsSpecifiedToInclude = getSetOfAnnotationsSpecifiedToInclude()
    println "Specified Annotations To Be Included: [${setOfAnnotationsSpecifiedToInclude.join(', ')}]"

    final setOfAnnotationsSpecifiedToExclude = getSetOfAnnotationsSpecifiedToExclude()
    println "Specified Annotations To Be Excluded: [${setOfAnnotationsSpecifiedToExclude.join(', ')}]"
    final setOfAnnotationsToExcludeByDefault = AnnotationsRegistry.getSetOfRecommendedDefaultAnnotationsToExclude()
    println "Annotations To Be Excluded By Default: [${setOfAnnotationsToExcludeByDefault.join(', ')}]"
    final setOfAnnotationsToExclude = setOfAnnotationsSpecifiedToExclude + setOfAnnotationsToExcludeByDefault
    println "Annotations To Be Excluded: [${setOfAnnotationsToExclude.join(', ')}]"

    println 'Including (running) any tests with annotations...'
    if (setOfAnnotationsSpecifiedToInclude) {
        includeOrExcludeAnnotations(include,
                setOfAnnotationsSpecifiedToInclude,
                "\tincluding annotation: ")
    }

    println 'Excluding any tests from running with annotations...'
    if (setOfAnnotationsToExclude) {
        includeOrExcludeAnnotations(exclude,
                setOfAnnotationsToExclude,
                "\texcluding annotation: ")
    }

    println 'Executing Tests...'
}

//--- ANNOTATION SET BUILDER METHODS ---//
def getSetOfAnnotationsSpecifiedToInclude() {
    getSetOfAnnotationsFromSystemProperty('spock.run.includes')
}

def getSetOfAnnotationsSpecifiedToExclude() {
    getSetOfAnnotationsFromSystemProperty('spock.run.excludes')
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
    final rawAnnotationNamesList = getAnnotationsNamesFromSystemProperty(propertyKey)
    getAnnotationsSetFrom(rawAnnotationNamesList)
}

def getAnnotationsNamesFromSystemProperty(propertyKey) {
    final annotationsNamesValue = System.getProperty(propertyKey)

    // ASSUMPTION: comma separated list of annotation names
    final annotationNames = (annotationsNamesValue) ? annotationsNamesValue.split(',') : []
    annotationNames.toList()
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
    annotationsSet
}
