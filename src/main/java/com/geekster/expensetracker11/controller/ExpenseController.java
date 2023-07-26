package com.geekster.expensetracker11.controller;


import com.geekster.expensetracker11.model.Expense;
import com.geekster.expensetracker11.model.User;
import com.geekster.expensetracker11.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@RestController
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;


    @GetMapping("expenses")
    List<Expense> getAllExpenses()
    {
        return expenseService.getAllExpenses();
    }

    @PostMapping
    public Expense createExpense(@RequestBody Expense expense) {
        return expenseService.createExpense(expense);
    }

    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
        return expenseService.updateExpense(id, expense);
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
    }



    @GetMapping("/dateAndTime")
    public List<Expense> getExpensesByDateAndTime(@RequestParam Date date, @RequestParam(required = false) Date time) {
        return expenseService.getExpensesByDateAndTime(date, time);
    }

    @GetMapping("/totalExpenditure")
    public double getTotalExpenditureByMonth(@RequestParam Long userId, @RequestParam int month, @RequestParam int year) {
        // You can add user authentication here to ensure only the authorized user can access their expenses.
        // For simplicity, we assume the user ID is provided via the 'userId' parameter.
        User user = new User();
        user.setUserId(userId);
        return expenseService.getTotalExpenditureByMonth(user, month, year);
    }

}
