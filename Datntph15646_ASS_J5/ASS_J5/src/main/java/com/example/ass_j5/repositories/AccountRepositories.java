package com.example.ass_j5.repositories;

import com.example.ass_j5.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepositories extends JpaRepository<Account,String> {
    Account findByEmailAndPass(String email, String pass);
    Account findByIdAndPass(String Id,String pass);


}

