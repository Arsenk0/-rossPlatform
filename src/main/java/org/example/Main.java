package org.example;

import org.example.model.Expense;
import org.example.service.ExpenseService;
import org.example.storage.JsonStorage;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Ініціалізація сервісів
        JsonStorage jsonStorage = new JsonStorage();
        ExpenseService expenseService = new ExpenseService(jsonStorage);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Додати витрату");
            System.out.println("2. Переглянути всі витрати");
            System.out.println("3. Оновити витрату");
            System.out.println("4. Видалити витрату");
            System.out.println("5. Вийти");
            System.out.print("Виберіть опцію: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистити буфер після числового вводу

            switch (choice) {
                case 1:
                    System.out.print("Введіть назву витрати: ");
                    String name = scanner.nextLine();
                    System.out.print("Введіть суму витрати: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Очистити буфер
                    System.out.print("Введіть дату витрати (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    Expense newExpense = new Expense(name, amount, date);
                    expenseService.addExpense(newExpense);
                    System.out.println("Витрату додано!");
                    break;

                case 2:
                    List<Expense> expenses = expenseService.getAllExpenses();
                    if (expenses.isEmpty()) {
                        System.out.println("Немає збережених витрат.");
                    } else {
                        System.out.println("\nСписок витрат:");
                        for (Expense exp : expenses) {
                            System.out.println(exp);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Введіть назву витрати, яку хочете оновити: ");
                    String updateName = scanner.nextLine();
                    System.out.print("Введіть нову суму витрати: ");
                    double newAmount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Введіть нову дату витрати (YYYY-MM-DD): ");
                    String newDate = scanner.nextLine();
                    expenseService.updateExpense(updateName, newAmount, newDate);
                    break;

                case 4:
                    System.out.print("Введіть назву витрати, яку хочете видалити: ");
                    String deleteName = scanner.nextLine();
                    expenseService.deleteExpense(deleteName);
                    break;

                case 5:
                    System.out.println("Вихід з програми...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Невірний вибір! Спробуйте ще раз.");
            }
        }
    }
}
