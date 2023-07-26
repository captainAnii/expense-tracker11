package com.geekster.expensetracker11.repository;


import com.geekster.expensetracker11.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User,Long> {
    User findFirstByUserEmail(String newEmail);

}
