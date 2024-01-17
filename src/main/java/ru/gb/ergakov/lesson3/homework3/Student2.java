package ru.gb.ergakov.lesson3.homework3;

import java.io.*;
import java.util.Base64;


/**
 * TODO
 */
public class Student2 implements Externalizable {
    private String name;
    private int age;
    private double GPA;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Student2(String name, int age, double GPA) {
        this.name = name;
        this.age = age;
        this.GPA = GPA;
    }

    public Student2() {
    }

    @Override
    public String toString() {
        return "Student1{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", GPA=" + GPA +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.getName());
        out.writeObject(this.getAge());
        out.writeObject(this.encryptString((Double.toString(this.GPA))));
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        age = (int) in.readObject();
        GPA = Double.parseDouble(this.decryptString((String) in.readObject()));
    }

    private String encryptString(String data) {
        String encryptedData = Base64.getEncoder().encodeToString(data.getBytes());
        System.out.println(encryptedData);
        return encryptedData;
    }

    private String decryptString(String data) {
        String decrypted = new String(Base64.getDecoder().decode(data));
        System.out.println(decrypted);
        return decrypted;
    }
}
