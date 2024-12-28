## Annotations
This project includes some annotations to use or you can add your own.

There are some breakpoint-related (phone, tablet, desktop) annotations
and `WIP` and `EndToEnd`.  The `EndToEnd` is used in this project.

> :no_entry_sign: By default tests tagged with the `WIP` annotation
> are **excluded** from running and must be explicitly specified

### Adding an Annotation
To add a new Annotation...
  1. Define the annotation (interface) file
     e.g. `annotations/EndToEnd.groovy`...
     ```groovy
     // Work In Progress Annotation
     @Target([ElementType.TYPE, ElementType.METHOD])
     @Retention(RetentionPolicy.RUNTIME)

     public @interface WIP { }
     ```
  2. If applicable, in the `annotations/AnnotationsRegistry.groovy`
     file, "Register" the Annotation in any applicable annotations
     lists under `//--- ANNOTATION LISTS --- //`
     e.g. `WIP` in `getSetOfRecommendedDefaultAnnotationsToExclude()`
  3. In the `annotations/AnnotationsRegistry.groovy` file,
     add the constant for the annotation's name so that it can be specified
     via system property (command line) under `// --- ANNOTATION NAMES --- //`
  4. Add the mapping of the annotation name to annotation under
     `// --- ANNOTATION NAME TO ANNOTATION LIST MAP--- //`
     e.g...
     ```groovy
     public static final WIP_ANNOTATION_NAME     = normalizeAnnotationName('wip')
     ```
