package com.service.employee.repository;

import com.service.employee.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
    String table = "user_account";

    UserAccount findByUsername(String username);
}
