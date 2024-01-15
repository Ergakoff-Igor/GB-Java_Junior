package ru.gb.ergakov.lesson3.homework3;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.*;


public class Student implements Serializable {
    private String name;
    private int age;
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

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", GPA=" + GPA +
                '}';
    }
}
