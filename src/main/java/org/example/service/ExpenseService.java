package org.example.service;

import org.example.model.Expense;
import org.example.storage.JsonStorage;

import java.util.ArrayList;
import java.util.List;

public class ExpenseService {
    private List<Expense> expenses;
    private final JsonStorage jsonStorage;

    public ExpenseService(JsonStorage jsonStorage) {
        this.jsonStorage = jsonStorage;
        this.expenses = jsonStorage.loadExpenses();  // Завантажуємо витрати з файлу JSON
    }

    public void addExpense(Expense expense) {
        if (expenses == null) {
            expenses = new ArrayList<>();  // Ініціалізація списку витрат, якщо він ще не ініціалізований
        }
        expenses.add(expense);
        jsonStorage.saveExpenses(expenses);  // Зберігаємо витрати після додавання
    }

    public List<Expense> getAllExpenses() {
        return expenses;
    }

    public void updateExpense(String name, double newAmount, String newDate) {
        for (Expense expense : expenses) {
            if (expense.getName().equals(name)) {
                expense = new Expense(name, newAmount, newDate);
                jsonStorage.saveExpenses(expenses);  // Оновлюємо файл після зміни витрати
                break;
            }
        }
    }

    public void deleteExpense(String name) {
        expenses.removeIf(expense -> expense.getName().equals(name));
        jsonStorage.saveExpenses(expenses);  // Оновлюємо файл після видалення витрати
    }

    public List<Expense> searchExpenses(String name, String date) {
        List<Expense> result = new ArrayList<>();
        for (Expense expense : expenses) {
            if ((name.isEmpty() || expense.getName().contains(name)) &&
                    (date.isEmpty() || expense.getDate().equals(date))) {
                result.add(expense);
            }
        }
        return result;
    }
}