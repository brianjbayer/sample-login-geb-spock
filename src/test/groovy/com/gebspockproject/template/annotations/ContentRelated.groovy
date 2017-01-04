package com.gebspockproject.template.annotations

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target



/*
  The Content annotations (tags) indicate that the annotated Spock Test
  verifies expectations based on content and authoring
 */


//TODO - Probably need a "ContentAssumed" or something like that assumes a certain type of content but not specific content value(s)

//--- Content Specific ---//
@Target([ElementType.TYPE, ElementType.METHOD])
@Retention(RetentionPolicy.RUNTIME)
public @interface  ContentSpecific {
}


