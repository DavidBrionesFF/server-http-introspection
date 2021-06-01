package com.nattechnologiesagency.core.anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target( { ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GetRoute {
    String path();
    String typeFile();
}
