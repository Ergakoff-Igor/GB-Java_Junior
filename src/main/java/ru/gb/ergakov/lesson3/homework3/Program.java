package ru.gb.ergakov.lesson3.homework3;

public class Program {
    public static final String FILE_BIN = "students.bin";
    public static final String FILE_JSON = "students.json";
    public static final String FILE_XML = "students.xml";

    public static final String FILE_BIN_2 = "students2.bin";
    public static final String FILE_JSON_2 = "students2.json";
    public static final String FILE_XML_2 = "students2.xml";

    public static void main(String[] args) {
        Student1 student1 = new Student1("Igor", 31, 6.5);
        System.out.println("Создан экземпляр -> " + student1);

        new AppSerializable().app(FILE_BIN, FILE_JSON, FILE_XML, student1);

//        Student2 student2 = new Student2("Igor", 31, 6.5);
//        System.out.println("Создан экземпляр -> " + student1);
//
//        new AppExternalizable().app(FILE_BIN_2, FILE_JSON_2, FILE_XML_2, student2);
    }
}
