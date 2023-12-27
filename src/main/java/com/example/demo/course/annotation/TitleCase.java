package com.example.demo.course.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TitleCase {
    Language value() default Language.ANY;

    enum Language {
        RU, EN, ANY
    }
}
