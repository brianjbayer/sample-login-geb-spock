package com.gebspockproject.template.annotations

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target



/*
  The Breakpoint annotations (tags) indicate that the annotated Spock Test
  verifies expectations at a breakpoint and thus set a specific
  viewport size.

  NOTE: Spock tests with the *Breakpoint tag will be excluded from
        running under the Htmlunit "browser" as viewport sizing
        and DOM manipulation by JavaScript is "unreliable" in Htmlunit
 */


//--- MobileBreakpoint ---//
@Target([ElementType.TYPE, ElementType.METHOD])
@Retention(RetentionPolicy.RUNTIME)
public @interface  MobileBreakpoint {
}


//--- NonMobileBreakpoint ---//
@Target([ElementType.TYPE, ElementType.METHOD])
@Retention(RetentionPolicy.RUNTIME)
public @interface  NonMobileBreakpoint {
}

//--- Tablet(NonMobile)Breakpoint ---//
@Target([ElementType.TYPE, ElementType.METHOD])
@Retention(RetentionPolicy.RUNTIME)
public @interface  TabletBreakpoint {
}

//--- Desktop(NonMobile)Breakpoint ---//
@Target([ElementType.TYPE, ElementType.METHOD])
@Retention(RetentionPolicy.RUNTIME)
public @interface  DesktopBreakpoint {
}
