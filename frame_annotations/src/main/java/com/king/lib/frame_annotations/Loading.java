package com.king.lib.frame_annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Desc:
 *
 * @author：Jing Yang
 * @date: 2020/7/3 11:32
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface Loading {
    String value();
}
