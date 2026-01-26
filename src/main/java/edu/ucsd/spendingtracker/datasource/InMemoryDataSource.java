package edu.ucsd.spendingtracker.datasource;

import java.util.ArrayList;
import java.util.List;

import edu.ucsd.spendingtracker.model.Expense;

public class InMemoryDataSource implements IDataSource {
    private List<Expense> expenses = new ArrayList<>();

    public InMemoryDataSource() {}

    public List<Expense> getExpenses() {
        return List.copyOf(expenses);
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public void deleteExpense(int id) {
        expenses.removeIf(expense -> expense.getId() == id);
    }
}

