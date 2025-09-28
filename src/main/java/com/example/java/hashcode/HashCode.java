package com.example.java.hashcode;

/**
 * @author LingengMa
 * @date 2025/09/27 15:23
 * @Description:
 */

class Person {
    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o instanceof Person person &&
                age == person.age &&
                name.equals(person.name);
    }
}

public class HashCode {
    public static void main(String[] args) {
        Person p1 = new Person(18, "Alice");
        Person p2 = new Person(18, "Alice");

        System.out.println(p1.equals(p2)); // true

        System.out.println(p1.hashCode()); // e.g., 12345678
        System.out.println(p2.hashCode()); // e.g., 87654321
    }
}
