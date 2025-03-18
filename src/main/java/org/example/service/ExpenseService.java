package org.example.service;

import org.example.model.Expense;
import org.example.storage.JsonStorage;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ExpenseService {
    private final JsonStorage jsonStorage;

    public ExpenseService(JsonStorage jsonStorage) {
        this.jsonStorage = jsonStorage;
    }

    public void addExpense(Expense expense) {
        List<Expense> expenses = jsonStorage.loadExpenses();
        expenses.add(expense);
        jsonStorage.saveExpenses(expenses);
    }

    public List<Expense> getAllExpenses() {
        return jsonStorage.loadExpenses();
    }

    public void updateExpense(String name, double amount, String date) {
        List<Expense> expenses = jsonStorage.loadExpenses();
        for (Expense expense : expenses) {
            if (expense.getName().equalsIgnoreCase(name)) {
                expense.setAmount(amount);
                expense.setDate(date);
                jsonStorage.saveExpenses(expenses);
                return;
            }
        }
    }

    public void deleteExpense(String name) {
        List<Expense> expenses = jsonStorage.loadExpenses();
        expenses.removeIf(expense -> expense.getName().equalsIgnoreCase(name));
        jsonStorage.saveExpenses(expenses);
    }

    public List<Expense> sortByAmount() {
        List<Expense> expenses = jsonStorage.loadExpenses();
        expenses.sort(Comparator.comparingDouble(Expense::getAmount));
        return expenses;
    }

    public List<Expense> sortByDate() {
        List<Expense> expenses = jsonStorage.loadExpenses();
        expenses.sort(Comparator.comparing(Expense::getDate));
        return expenses;
    }

    public List<Expense> searchExpenses(String name, String date) {
        List<Expense> expenses = jsonStorage.loadExpenses();
        return expenses.stream()
                .filter(expense -> (name.isEmpty() || expense.getName().equalsIgnoreCase(name)) &&
                        (date.isEmpty() || expense.getDate().equals(date)))
                .toList();
    }
}
