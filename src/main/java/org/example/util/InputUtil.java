package org.example.util;

import java.util.Scanner;

public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Неправильний формат. Введіть число: ");
            }
        }
    }

    public static double readDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Неправильний формат. Введіть число: ");
            }
        }
    }

    public static String readString() {
        return scanner.nextLine().trim();
    }

    // Додано метод для читання рядка
    public static String readLine() {
        return scanner.nextLine().trim();
    }
}
