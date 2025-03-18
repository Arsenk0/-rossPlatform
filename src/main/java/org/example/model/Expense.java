package org.example.model;

public class Expense {
    private String name;
    private double amount;
    private String date;

    public Expense(String name, double amount, String date) {
        this.name = name;
        this.amount = amount;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Витрата: " + name + ", сума: " + amount + ", дата: " + date;
    }
}
