package com.example.java.string;

/**
 * @author LingengMa
 * @date 2025/09/28 15:11
 * @Description:
 */


public class InternExample {
    public static void main(String[] args) {
        String s1 = new String("hello");
        String s2 = new String("hello");
        String s3 = s1.intern();

        System.out.println(s1 == s2);     // false，两个对象不同
        System.out.println(s1 == s3);     // false，比较的是堆对象和常量池对象
        System.out.println(s2 == s3);     // false

        String s4 = "hello";
        System.out.println(s3 == s4);     // true，都指向常量池中的同一个对象
    }
}
