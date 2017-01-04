package com.gebspockproject.template.annotations

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target



/*
  The JavaScript annotation (tag) indicates that the annotated Spock Test
  relies on JavaScript execution to realize the expected behavior.

  NOTE: Spock tests with the JavaScript tag will be excluded from
        running under the Htmlunit "browser" as DOM manipulation
        by JavaScript is "unreliable" in Htmlunit
 */


@Target([ElementType.TYPE, ElementType.METHOD])
@Retention(RetentionPolicy.RUNTIME)
public @interface  JavaScript {
}
