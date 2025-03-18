package org.example.service;

import org.example.model.Expense;
import org.example.storage.JsonStorage;

import java.util.List;
import java.util.Iterator;

public class ExpenseService {
    private final JsonStorage jsonStorage;

    public ExpenseService(JsonStorage jsonStorage) {
        this.jsonStorage = jsonStorage;
    }

    // Додавання витрати
    public void addExpense(Expense expense) {
        if (expense.getAmount() <= 0) {
            System.out.println("Сума витрати має бути більше 0!");
            return;
        }
        List<Expense> expenses = jsonStorage.readExpenses();
        expenses.add(expense);
        jsonStorage.saveExpenses(expenses);
        System.out.println("Витрату успішно додано!");
    }

    // Отримання списку витрат
    public List<Expense> getAllExpenses() {
        return jsonStorage.readExpenses();
    }

    // Оновлення витрати
    public void updateExpense(String name, double amount, String date) {
        List<Expense> expenses = jsonStorage.readExpenses();
        boolean found = false;
        for (Expense expense : expenses) {
            if (expense.getName().equalsIgnoreCase(name)) {
                expense.setAmount(amount);
                expense.setDate(date);
                found = true;
                break;
            }
        }
        if (found) {
            jsonStorage.saveExpenses(expenses);
            System.out.println("Витрату оновлено!");
        } else {
            System.out.println("Витрату не знайдено!");
        }
    }

    // Видалення витрати
    public void deleteExpense(String name) {
        List<Expense> expenses = jsonStorage.readExpenses();
        Iterator<Expense> iterator = expenses.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            Expense expense = iterator.next();
            if (expense.getName().equalsIgnoreCase(name)) {
                iterator.remove();
                found = true;
                break;
            }
        }
        if (found) {
            jsonStorage.saveExpenses(expenses);
            System.out.println("Витрату видалено!");
        } else {
            System.out.println("Витрату не знайдено!");
        }
    }
}
