package org.example;

import org.example.model.Expense;
import org.example.service.ExpenseService;
import org.example.storage.JsonStorage;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JsonStorage jsonStorage = new JsonStorage();
        ExpenseService expenseService = new ExpenseService(jsonStorage);

        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Додати витрату");
            System.out.println("2. Показати всі витрати");
            System.out.println("3. Оновити витрату");
            System.out.println("4. Видалити витрату");
            System.out.println("5. Сортувати витрати за сумою");
            System.out.println("6. Сортувати витрати за датою");
            System.out.println("7. Вийти");
            System.out.print("Вибір: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Очистити буфер

            switch (choice) {
                case 1:
                    // Додавання витрати
                    System.out.print("Введіть назву витрати: ");
                    String name = scanner.nextLine();
                    System.out.print("Введіть суму витрати: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();  // Очистити буфер
                    System.out.print("Введіть дату витрати (формат: YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    Expense expense = new Expense(name, amount, date);
                    expenseService.addExpense(expense);
                    System.out.println("Витрату додано!");
                    break;
                case 2:
                    // Показати всі витрати
                    List<Expense> expenses = expenseService.getAllExpenses();
                    for (Expense exp : expenses) {
                        System.out.println(exp);
                    }
                    break;
                case 3:
                    // Оновити витрату
                    System.out.print("Введіть назву витрати, яку хочете оновити: ");
                    String updateName = scanner.nextLine();
                    System.out.print("Введіть нову суму витрати: ");
                    double newAmount = scanner.nextDouble();
                    scanner.nextLine();  // Очистити буфер
                    System.out.print("Введіть нову дату витрати (формат: YYYY-MM-DD): ");
                    String newDate = scanner.nextLine();
                    expenseService.updateExpense(updateName, newAmount, newDate);
                    System.out.println("Витрату оновлено!");
                    break;
                case 4:
                    // Видалити витрату
                    System.out.print("Введіть назву витрати, яку хочете видалити: ");
                    String deleteName = scanner.nextLine();
                    expenseService.deleteExpense(deleteName);
                    System.out.println("Витрату видалено!");
                    break;
                case 5:
                    // Сортувати витрати за сумою
                    expenseService.sortExpensesByAmount();
                    System.out.println("Витрати відсортовано за сумою!");
                    break;
                case 6:
                    // Сортувати витрати за датою
                    expenseService.sortExpensesByDate();
                    System.out.println("Витрати відсортовано за датою!");
                    break;
                case 7:
                    // Вихід
                    System.out.println("До зустрічі!");
                    return;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }
}
