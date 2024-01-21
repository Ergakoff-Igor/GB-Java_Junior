package ru.gb.ergakov.Lesson4.Homework4.UI;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ConsoleView {
    Scanner in;
    public ConsoleView() {
        in = new Scanner(System.in, StandardCharsets.UTF_8);
    }

    public int inputDuration() {
        System.out.print("Введите длительность курса в часах: ");
        return in.nextInt();
    }

    public String inputTitle() {
        System.out.print("Введите название курса: ");
        return in.nextLine();
    }

    public int inputID() {
        System.out.print("Введите ID: ");
        return in.nextInt();
    }
}
