package com.example.java.annotation.runtime;

/**
 * @author LingengMa
 * @date 2025/09/28 10:46
 * @Description:
 */


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

// 定义一个运行时注解
@Retention(RetentionPolicy.RUNTIME)
@interface MyRuntimeAnnotation {
    String value();
}

// 使用运行时注解
class MyClass {

    @MyRuntimeAnnotation("This is a runtime annotation")
    public void myMethod() {
        System.out.println("Method with runtime annotation");
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        MyClass obj = new MyClass();
        Method method = obj.getClass().getMethod("myMethod");

        // 使用反射获取方法上的运行时注解
        if (method.isAnnotationPresent(MyRuntimeAnnotation.class)) {
            MyRuntimeAnnotation annotation = method.getAnnotation(MyRuntimeAnnotation.class);
            System.out.println("Annotation value: " + annotation.value());
        }

        method.invoke(obj);
    }
}
