package com.vvt.icommerce.paymentservice.model;

import com.vvt.icommerce.paymentservice.exception.NotEnoughFundException;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Entity
@Transactional
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Double balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void deposit(Double amount){
        this.balance += amount;
    }

    public void withdraw(Double amount) throws Exception{
        if (this.balance < amount) throw new NotEnoughFundException("Not enough fund!");
        this.balance -= amount;
    }

    public Account() {}

    public Account(Long userId, Double balance) {
        this.userId = userId;
        this.balance = balance;
    }
}