package com.example.java.annotation.compile;

/**
 * @author LingengMa
 * @date 2025/09/28 10:48
 * @Description:
 */


@MyCompileTimeAnnotation(message = "This is a compile-time annotation")
public class MyCompileTimeClass {
    public static void main(String[] args) {
        System.out.println("Compile time annotation processed");
    }
}
