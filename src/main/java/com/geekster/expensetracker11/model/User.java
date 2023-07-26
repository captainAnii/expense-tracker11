package com.geekster.expensetracker11.model;

import com.geekster.expensetracker11.model.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @NotBlank
    private String username;
    @Email
    private String userEmail;
    @NotBlank
    private String userPassword;
    @NotBlank
    private String userAddress;
    private Gender gender;
    @OneToMany(mappedBy = "user")
    private List<Expense> expenses;
}
