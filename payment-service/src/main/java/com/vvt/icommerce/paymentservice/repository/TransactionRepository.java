package com.vvt.icommerce.paymentservice.repository;

import com.vvt.icommerce.paymentservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}

