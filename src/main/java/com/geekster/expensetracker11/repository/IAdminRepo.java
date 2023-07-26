package com.geekster.expensetracker11.repository;


import com.geekster.expensetracker11.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepo extends JpaRepository<Admin,Long> {

}
