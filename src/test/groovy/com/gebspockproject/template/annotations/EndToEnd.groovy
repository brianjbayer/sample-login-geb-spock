package com.gebspockproject.template.annotations

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target



/*
  The EndToEnd annotation (tag) indicates that the annotated Spock Test
  performs an EndToEnd Test.
 */


@Target([ElementType.TYPE, ElementType.METHOD])
@Retention(RetentionPolicy.RUNTIME)
public @interface  EndToEnd {
}
