package org.m0t9_.stategram.filters;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface OnMessage {
    String pattern() default ".*";
}