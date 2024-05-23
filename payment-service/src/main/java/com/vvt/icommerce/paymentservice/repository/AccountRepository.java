package com.vvt.icommerce.paymentservice.repository;

import com.vvt.icommerce.paymentservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    public Account findAccountByUserId(Long userId);
}
