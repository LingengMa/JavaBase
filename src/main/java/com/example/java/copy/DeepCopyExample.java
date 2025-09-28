package com.example.java.copy;

/**
 * @author LingengMa
 * @date 2025/09/28 09:57
 * @Description:
 */


import java.util.Arrays;

// 被包含的类
class Address {
    String city;

    public Address(String city) {
        this.city = city;
    }

    // 用于打印信息
    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                '}';
    }
}

// 主类，实现Cloneable接口以支持浅拷贝
class Person implements Cloneable {
    String name;
    int age;
    Address address;  // 引用类型字段

    public Person(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    // 浅拷贝
    @Override
    protected Person clone() throws CloneNotSupportedException {
        return (Person) super.clone();  // 只复制基本类型和引用地址
    }

    // 深拷贝（手动实现）
    public Person deepCopy() {
        return new Person(this.name, this.age, new Address(this.address.city));
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                '}';
    }
}

public class DeepCopyExample {
    public static void main(String[] args) throws CloneNotSupportedException {
        Address address = new Address("Beijing");
        Person original = new Person("John", 25, address);

        // 浅拷贝
        Person shallowCopy = original.clone();

        // 修改原对象中的引用对象
        original.address.city = "Shanghai";

        System.out.println("浅拷贝后 original: " + original);
        System.out.println("浅拷贝后 shallowCopy: " + shallowCopy);

        System.out.println("---------------------------------");

        // 重置
        address = new Address("Beijing");
        original = new Person("John", 25, address);

        // 深拷贝
        Person deepCopy = original.deepCopy();

        // 修改原对象中的引用对象
        original.address.city = "Shanghai";

        System.out.println("深拷贝后 original: " + original);
        System.out.println("深拷贝后 deepCopy: " + deepCopy);
    }
}
