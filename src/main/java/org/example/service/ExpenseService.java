package org.example.service;

import org.example.model.Expense;
import org.example.storage.JsonStorage;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    // Отримання всіх витрат
    public List<Expense> getAllExpenses() {
        return jsonStorage.readExpenses();
    }

    // Сортування за сумою (від меншої до більшої)
    public List<Expense> sortByAmount() {
        return jsonStorage.readExpenses()
                .stream()
                .sorted(Comparator.comparingDouble(Expense::getAmount))
                .collect(Collectors.toList());
    }

    // Сортування за датою (від новішої до старішої)
    public List<Expense> sortByDate() {
        return jsonStorage.readExpenses()
                .stream()
                .sorted(Comparator.comparing(Expense::getDate).reversed())
                .collect(Collectors.toList());
    }
}
