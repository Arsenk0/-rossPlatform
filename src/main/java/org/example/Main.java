package org.example;

import org.example.model.Expense;
import org.example.service.ExpenseService;
import org.example.storage.JsonStorage;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        JsonStorage jsonStorage = new JsonStorage();
        ExpenseService expenseService = new ExpenseService(jsonStorage);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Додати витрату");
            System.out.println("2. Показати всі витрати");
            System.out.println("3. Оновити витрату");
            System.out.println("4. Видалити витрату");
            System.out.println("5. Сортувати витрати за сумою");
            System.out.println("6. Сортувати витрати за датою");
            System.out.println("7. Пошук витрат");
            System.out.println("8. Вийти");
            System.out.print("Вибір: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: // Додати витрату
                    System.out.print("Введіть назву витрати: ");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    System.out.print("Введіть суму витрати: ");
                    double amount = scanner.nextDouble();
                    System.out.print("Введіть дату витрати (формат YYYY-MM-DD): ");
                    scanner.nextLine();
                    String date = scanner.nextLine();
                    expenseService.addExpense(new Expense(name, amount, date));
                    break;

                case 2: // Показати всі витрати
                    List<Expense> expenses = expenseService.getAllExpenses();
                    for (Expense expense : expenses) {
                        System.out.println(expense);
                    }
                    break;

                case 3: // Оновити витрату
                    System.out.print("Введіть назву витрати для оновлення: ");
                    scanner.nextLine();
                    String updateName = scanner.nextLine();
                    System.out.print("Введіть нову суму витрати: ");
                    double updateAmount = scanner.nextDouble();
                    System.out.print("Введіть нову дату витрати: ");
                    scanner.nextLine();
                    String updateDate = scanner.nextLine();
                    expenseService.updateExpense(updateName, updateAmount, updateDate);
                    break;

                case 4: // Видалити витрату
                    System.out.print("Введіть назву витрати для видалення: ");
                    scanner.nextLine();
                    String deleteName = scanner.nextLine();
                    expenseService.deleteExpense(deleteName);
                    break;

                case 5: // Сортувати витрати за сумою
                    List<Expense> sortedByAmount = expenseService.getAllExpenses();
                    sortedByAmount.sort((e1, e2) -> Double.compare(e1.getAmount(), e2.getAmount()));
                    for (Expense expense : sortedByAmount) {
                        System.out.println(expense);
                    }
                    break;

                case 6: // Сортувати витрати за датою
                    List<Expense> sortedByDate = expenseService.getAllExpenses();
                    sortedByDate.sort((e1, e2) -> e1.getDate().compareTo(e2.getDate()));
                    for (Expense expense : sortedByDate) {
                        System.out.println(expense);
                    }
                    break;

                case 7: // Пошук витрат
                    scanner.nextLine(); // Очистка буферу
                    System.out.println("Введіть назву витрати для пошуку (залиште порожнім для пошуку за датою):");
                    String searchName = scanner.nextLine();
                    System.out.println("Введіть дату витрати для пошуку (формат YYYY-MM-DD, залиште порожнім для пошуку за назвою):");
                    String searchDate = scanner.nextLine();

                    List<Expense> searchResults = expenseService.searchExpenses(searchName, searchDate);
                    if (searchResults.isEmpty()) {
                        System.out.println("Немає витрат, що відповідають пошуковим критеріям.");
                    } else {
                        System.out.println("Результати пошуку:");
                        for (Expense expense : searchResults) {
                            System.out.println(expense);
                        }
                    }
                    break;

                case 8: // Вийти
                    System.exit(0);
                    break;

                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }
}
