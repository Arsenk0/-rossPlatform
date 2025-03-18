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

    public void addExpense(Expense expense) {
        List<Expense> expenses = jsonStorage.readExpenses();
        expenses.add(expense);
        jsonStorage.saveExpenses(expenses);
    }

    public List<Expense> getAllExpenses() {
        return jsonStorage.readExpenses();
    }

    public void updateExpense(String name, double amount, String date) {
        List<Expense> expenses = jsonStorage.readExpenses();
        for (Expense expense : expenses) {
            if (expense.getName().equals(name)) {
                expense.setAmount(amount);
                expense.setDate(date);
                jsonStorage.saveExpenses(expenses);
                System.out.println("Витрату оновлено!");
                return;
            }
        }
        System.out.println("Витрату не знайдено!");
    }

    public void deleteExpense(String name) {
        List<Expense> expenses = jsonStorage.readExpenses();
        Iterator<Expense> iterator = expenses.iterator();
        while (iterator.hasNext()) {
            Expense expense = iterator.next();
            if (expense.getName().equals(name)) {
                iterator.remove();
                jsonStorage.saveExpenses(expenses);
                System.out.println("Витрату видалено!");
                return;
            }
        }
        System.out.println("Витрату не знайдено!");
    }
}
