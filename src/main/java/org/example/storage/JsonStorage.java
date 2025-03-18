package org.example.storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.model.Expense;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonStorage {
    private final String FILE_PATH = "src/main/resources/expenses.json";

    // Зчитуємо витрати з JSON файлу
    public List<Expense> readExpenses() {
        List<Expense> expenses = new ArrayList<>();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Expense>>() {}.getType();
            expenses = gson.fromJson(reader, listType);
        } catch (IOException e) {
            System.out.println("Помилка при зчитуванні даних з файлу.");
        }
        return expenses;
    }

    // Зберігаємо витрати у JSON файл
    public void saveExpenses(List<Expense> expenses) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            Gson gson = new Gson();
            gson.toJson(expenses, writer);
        } catch (IOException e) {
            System.out.println("Помилка при збереженні даних у файл.");
        }
    }
}
