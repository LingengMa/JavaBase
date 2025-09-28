package com.example.java.annotation.compile;

/**
 * @author LingengMa
 * @date 2025/09/28 10:47
 * @Description: 
 */


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS) // 改为CLASS级别，在编译时保留
@Target(ElementType.TYPE) // 示例为作用于类
public @interface MyCompileTimeAnnotation {
    String message();
}
