package com.example.java.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author LingengMa
 * @date 2025/09/28 09:50
 * @Description:
 */

class Person {
    private String name;
    public int age;

    public Person() {
        this.name = "默认名称";
        this.age = 0;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void sayHello() {
        System.out.println("Hello, 我的名字是 " + name + ", 年龄是 " + age);
    }

    private void secretMethod() {
        System.out.println("这是一个私有方法！");
    }
}

public class ReflectionExample {
    public static void main(String[] args) throws Exception {
        // 获取类的 Class 对象
        Class<?> personClass = Class.forName("com.example.java.reflection.Person");

        // 获取类的构造方法并创建实例
        Constructor<?> constructor = personClass.getConstructor(String.class, int.class);
        Object personObject = constructor.newInstance("张三", 25);

        // 调用 public 方法
        Method sayHelloMethod = personClass.getMethod("sayHello");
        sayHelloMethod.invoke(personObject);

        // 访问 public 字段
        Field ageField = personClass.getField("age");
        System.out.println("年龄字段的值为：" + ageField.get(personObject));

        // 修改 public 字段的值
        ageField.set(personObject, 30);
        System.out.println("修改后的年龄字段值为：" + ageField.get(personObject));

        // 访问私有方法（需要 setAccessible(true)）
        Method secretMethod = personClass.getDeclaredMethod("secretMethod");
        secretMethod.setAccessible(true);
        secretMethod.invoke(personObject);

        // 获取无参构造函数并创建对象
        Constructor<?> defaultConstructor = personClass.getConstructor();
        Object defaultPerson = defaultConstructor.newInstance();
        Method defaultSayHello = personClass.getMethod("sayHello");
        defaultSayHello.invoke(defaultPerson);
    }
}
