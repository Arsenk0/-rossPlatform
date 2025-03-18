package org.example;

import org.example.model.Expense;
import org.example.service.ExpenseService;
import org.example.storage.JsonStorage;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        JsonStorage storage = new JsonStorage();
        ExpenseService service = new ExpenseService(storage);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Меню ===");
            System.out.println("1. Додати витрату");
            System.out.println("2. Переглянути всі витрати");
            System.out.println("3. Оновити витрату");
            System.out.println("4. Видалити витрату");
            System.out.println("5. Вийти");
            System.out.print("Оберіть опцію: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // очищення буфера після nextInt()

            switch (choice) {
                case 1:
                    System.out.print("Введіть назву витрати: ");
                    String name = scanner.nextLine();
                    System.out.print("Введіть суму: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // очищення буфера
                    System.out.print("Введіть дату (рррр-мм-дд): ");
                    String date = scanner.nextLine();
                    service.addExpense(new Expense(name, amount, date));
                    break;
                case 2:
                    List<Expense> expenses = service.getAllExpenses();
                    if (expenses.isEmpty()) {
                        System.out.println("Немає жодної витрати.");
                    } else {
                        for (Expense expense : expenses) {
                            System.out.println(expense);
                        }
                    }
                    break;
                case 3:
                    System.out.print("Введіть назву витрати, яку хочете оновити: ");
                    String updateName = scanner.nextLine();
                    System.out.print("Введіть нову суму: ");
                    double newAmount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Введіть нову дату (рррр-мм-дд): ");
                    String newDate = scanner.nextLine();
                    service.updateExpense(updateName, newAmount, newDate);
                    break;
                case 4:
                    System.out.print("Введіть назву витрати, яку хочете видалити: ");
                    String deleteName = scanner.nextLine();
                    service.deleteExpense(deleteName);
                    break;
                case 5:
                    System.out.println("Вихід...");
                    return;
                default:
                    System.out.println("Неправильний вибір, спробуйте ще раз.");
            }
        }
    }
}
