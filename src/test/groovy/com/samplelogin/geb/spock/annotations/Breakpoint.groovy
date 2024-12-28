package com.samplelogin.geb.spock.annotations

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

//--- MobileBreakpoint ---//
@Target([ElementType.TYPE, ElementType.METHOD])
@Retention(RetentionPolicy.RUNTIME)

public @interface MobileBreakpoint { }

//--- NonMobileBreakpoint ---//
@Target([ElementType.TYPE, ElementType.METHOD])
@Retention(RetentionPolicy.RUNTIME)

public @interface NonMobileBreakpoint { }

//--- Tablet(NonMobile)Breakpoint ---//
@Target([ElementType.TYPE, ElementType.METHOD])
@Retention(RetentionPolicy.RUNTIME)

public @interface TabletBreakpoint { }

//--- Desktop(NonMobile)Breakpoint ---//
@Target([ElementType.TYPE, ElementType.METHOD])
@Retention(RetentionPolicy.RUNTIME)

public @interface DesktopBreakpoint { }
