package ru.gb.ergakov.Lesson4.Homework4.Hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.gb.ergakov.Lesson4.Homework4.UI.ConsoleView;
import ru.gb.ergakov.Lesson4.Homework4.models.Course;
import ru.gb.ergakov.Lesson4.Seminar4.models.Student;

public class Program {

    private static final ConsoleView cv = new ConsoleView();
    public static void main(String[] args) {

        // Создание фабрики сессий
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate2.cfg.xml")
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // Создание сессии
        try (Session session = sessionFactory.getCurrentSession()){

            // Начало транзакции
            session.beginTransaction();

//            // Создание объекта
//            Course course = new Course(cv.inputTitle(), cv.inputDuration());
//
//            // Сохранение объекта в базе данных
//            session.save(course);
//            System.out.println("Object course save successfully");

            // Чтение объекта из базы данных
            Course retrievedCourse = session.get(Course.class, cv.inputID());
            System.out.println(retrievedCourse);

            System.out.println("Object course retrieved successfully");
            System.out.println("Retrieved course object: " + retrievedCourse);

            // Обновление данных (Изменение продолжительности курса)
//            retrievedCourse.setDuration(cv.inputDuration());
//            session.update(retrievedCourse);
//            System.out.println("Object student update successfully");

            // Удаление объекта
            session.delete(retrievedCourse);
            System.out.println("Object student delete successfully");

            // Коммит транзакции
            session.getTransaction().commit();
            System.out.println("Transaction commit successfully");

        }

    }

}
