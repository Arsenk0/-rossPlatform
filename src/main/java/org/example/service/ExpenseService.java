package org.example.service;

import org.example.model.Expense;
import org.example.storage.JsonStorage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExpenseService {
    private JsonStorage jsonStorage;

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
                return;
            }
        }
        System.out.println("Витрату не знайдено!");
    }

    // Метод для пошуку витрат по назві та даті
    public List<Expense> searchExpenses(String name, String date) {
        List<Expense> expenses = jsonStorage.readExpenses();
        List<Expense> result = new ArrayList<>();

        // Пошук за назвою
        if (name != null && !name.isEmpty()) {
            for (Expense expense : expenses) {
                if (expense.getName().toLowerCase().contains(name.toLowerCase())) {
                    result.add(expense);
                }
            }
        }

        // Пошук за датою
        if (date != null && !date.isEmpty()) {
            for (Expense expense : expenses) {
                if (expense.getDate().equals(date)) {
                    result.add(expense);
                }
            }
        }

        return result;
    }
}
