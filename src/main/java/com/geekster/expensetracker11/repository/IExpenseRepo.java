package com.geekster.expensetracker11.repository;


import com.geekster.expensetracker11.model.Expense;
import com.geekster.expensetracker11.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface IExpenseRepo extends JpaRepository<Expense,Long> {
    List<Expense> findByDateAndTime(Date date, Date time);
    List<Expense> findByUserAndDate(User user, Date date);

}
