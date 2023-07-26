package com.geekster.expensetracker11.service;

import com.geekster.expensetracker11.model.Expense;
import com.geekster.expensetracker11.model.User;
import com.geekster.expensetracker11.repository.IExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
@Service
public class ExpenseService {

    @Autowired
    IExpenseRepo expenseRepository;

    public Expense createExpense(Expense expense) {
        // Add any validation or business logic here before saving the expense
        return expenseRepository.save(expense);
    }

    public Expense updateExpense(Long id, Expense expense) {
        // Check if the expense exists before updating
        Expense existingExpense = expenseRepository.findById(id).orElse(null);
        if (existingExpense == null) {
            // Handle the case when the expense with the given id does not exist
            // You can throw an exception or return null/empty object depending on your use case
            return null;
        }

        // Update the existing expense with the new values
        existingExpense.setTitle(expense.getTitle());
        existingExpense.setDescription(expense.getDescription());
        existingExpense.setPrice(expense.getPrice());
        existingExpense.setDate(expense.getDate());
        existingExpense.setTime(expense.getTime());

        return expenseRepository.save(existingExpense);
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public List<Expense> getExpensesByDateAndTime(Date date, Date time) {
        // Implement the logic to get expenses by date and time from the repository
        // You can use the provided ExpenseRepository method `findByDateAndTime`
        return expenseRepository.findByDateAndTime(date, time);
    }

    public double getTotalExpenditureByMonth(User user, int month, int year) {
        // Implement the logic to calculate the total expenditure for a given month and user
        // You can use the provided ExpenseRepository method `findByUserAndDate`
        // to fetch expenses for the specified user and month
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1, 0, 0, 0);
        Date startOfMonth = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date endOfMonth = calendar.getTime();

        List<Expense> expenses = expenseRepository.findByUserAndDate(user, startOfMonth);
        double totalExpenditure = expenses.stream()
                .filter(expense -> expense.getDate().compareTo(endOfMonth) <= 0)
                .mapToDouble(Expense::getPrice)
                .sum();

        return totalExpenditure;
    }
}
