package org.example.service;

import org.example.model.Expense;
import org.example.storage.JsonStorage;

import java.util.List;
import java.util.Iterator;
import java.util.Collections;
import java.util.Comparator;

public class ExpenseService {
    private JsonStorage jsonStorage;

    public ExpenseService(JsonStorage jsonStorage) {
        this.jsonStorage = jsonStorage;
    }

    // Додавання витрати
    public void addExpense(Expense expense) {
        List<Expense> expenses = jsonStorage.readExpenses();
        expenses.add(expense);
        jsonStorage.saveExpenses(expenses);
    }

    // Отримати всі витрати
    public List<Expense> getAllExpenses() {
        return jsonStorage.readExpenses();
    }

    // Оновлення витрати
    public void updateExpense(String name, double amount, String date) {
        List<Expense> expenses = jsonStorage.readExpenses();
        for (Expense expense : expenses) {
            if (expense.getName().equals(name)) {
                expense.setAmount(amount);
                expense.setDate(date);  // Оновлення дати
                jsonStorage.saveExpenses(expenses);
                return;
            }
        }
        System.out.println("Витрату не знайдено!");
    }

    // Видалення витрати
    public void deleteExpense(String name) {
        List<Expense> expenses = jsonStorage.readExpenses();
        Iterator<Expense> iterator = expenses.iterator();
        while (iterator.hasNext()) {
            Expense expense = iterator.next();
            if (expense.getName().equals(name)) {
                iterator.remove();
                jsonStorage.saveExpenses(expenses);
                return;
            }
        }
        System.out.println("Витрату не знайдено!");
    }

    // Сортування витрат за сумою
    public void sortExpensesByAmount() {
        List<Expense> expenses = jsonStorage.readExpenses();
        expenses.sort(Comparator.comparingDouble(Expense::getAmount));
        jsonStorage.saveExpenses(expenses);
    }

    // Сортування витрат за датою
    public void sortExpensesByDate() {
        List<Expense> expenses = jsonStorage.readExpenses();
        expenses.sort(Comparator.comparing(Expense::getDate));
        jsonStorage.saveExpenses(expenses);
    }
}
