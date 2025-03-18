package org.example.storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.model.Expense;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonStorage {
    private static final String FILE_PATH = "src/main/resources/expenses.json";
    private final Gson gson = new Gson();

    public List<Expense> readExpenses() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            return gson.fromJson(reader, new TypeToken<List<Expense>>() {}.getType());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void saveExpenses(List<Expense> expenses) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(expenses, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
