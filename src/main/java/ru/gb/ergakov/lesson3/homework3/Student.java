package ru.gb.ergakov.lesson3.homework3;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *TODO
 */
public abstract class Student {
    private String name;
    private int age;

    @JsonIgnore
    transient double GPA;


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Student(String name, int age, double GPA) {
        this.name = name;
        this.age = age;
        this.GPA = GPA;
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        this.GPA = 0.0;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student1{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", GPA=" + GPA +
                '}';
    }
}
