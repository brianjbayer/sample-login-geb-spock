package com.gebspockproject.template.annotations

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target



/*
  The TargetEnvironment annotations (tags) indicate that the annotated Spock Test
  has Target Environment implications (e.g. only run properly in a given envoronment)
 */


//--- NonProdOnly ---//
@Target([ElementType.TYPE, ElementType.METHOD])
@Retention(RetentionPolicy.RUNTIME)
public @interface DevelopmentLevelTargetOnly {
}
