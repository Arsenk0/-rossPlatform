package org.example.storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.model.Expense;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonStorage {
    private final String filePath = "expenses.json";

    public List<Expense> loadExpenses() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return new ArrayList<>();  // Якщо файл не існує, повертаємо порожній список
            }

            FileReader reader = new FileReader(file);
            Type listType = new TypeToken<List<Expense>>(){}.getType();
            List<Expense> expenses = new Gson().fromJson(reader, listType);
            reader.close();
            return expenses != null ? expenses : new ArrayList<>();  // Якщо список порожній, повертаємо порожній список
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();  // Якщо виникла помилка, повертаємо порожній список
        }
    }

    public void saveExpenses(List<Expense> expenses) {
        try {
            FileWriter writer = new FileWriter(filePath);
            new Gson().toJson(expenses, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
