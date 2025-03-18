package org.example.util;

import java.util.Scanner;

public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static String nextLine() {
        return scanner.nextLine().trim();
    }

    public static int nextInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Помилка! Введіть число:");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // Очищення буфера після введення числа
        return value;
    }

    public static double nextDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.println("Помилка! Введіть число:");
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }
}
