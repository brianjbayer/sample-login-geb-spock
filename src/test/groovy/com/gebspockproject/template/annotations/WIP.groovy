package com.gebspockproject.template.annotations

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target



/*
  The WIP annotation (tag) indicates that the annotated Spock Test
  is currently a Work in Progress and thus its disposition (pass or fail)
  may not be correct.

  NOTE: Spock tests with the WIP tag will be excluded from running by default
 */


//--- MobileBreakpoint ---//
@Target([ElementType.TYPE, ElementType.METHOD])
@Retention(RetentionPolicy.RUNTIME)
public @interface  WIP {
}
