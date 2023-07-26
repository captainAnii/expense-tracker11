package com.geekster.expensetracker11.repository;


import com.geekster.expensetracker11.model.AuthenticationToken;
import com.geekster.expensetracker11.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthTokenRepo extends JpaRepository<AuthenticationToken,Long> {
    AuthenticationToken findFirstByUser(User user);

    AuthenticationToken findFirstByTokenValue(String authTokenValue);

}
