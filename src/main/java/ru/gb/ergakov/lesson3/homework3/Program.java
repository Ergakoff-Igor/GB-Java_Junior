package ru.gb.ergakov.lesson3.homework3;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.gb.ergakov.lesson3.seminar3.task2.ToDoV2;

import java.io.*;
import java.util.List;

public class Program {
    public static final String FILE_BIN = "students.bin";
    public static final String FILE_JSON = "students.json";
    public static final String FILE_XML = "students.xml";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final XmlMapper xmlMapper = new XmlMapper();

    public static void main(String[] args) {

        Student student = new Student("Igor", 31, 6.5);
        System.out.println("Создан экземпляр -> " + student);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_BIN))) {
            oos.writeObject(student);
            System.out.println("Сериализация выполнена успешно!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_BIN))) {
            student = (Student) ois.readObject();
            System.out.println("Десериализация выполнена успешно!");
            System.out.println(student);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public static void saveTasksToFile(String fileName, List<ToDoV2> tasks) {
        try {
            if (fileName.endsWith(".json")) {
                objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                objectMapper.writeValue(new File(fileName), tasks);
            } else if (fileName.endsWith(".bin")) {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                    oos.writeObject(tasks);
                }
            } else if (fileName.endsWith(".xml")) {
                //String s = xmlMapper.writeValueAsString(tasks);
                xmlMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                xmlMapper.writeValue(new File(fileName), tasks);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
