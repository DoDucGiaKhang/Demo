package com.vvt.icommerce.paymentservice.model;

import com.vvt.icommerce.paymentservice.exception.NotEnoughFundException;

import javax.persistence.*;
import javax.validation.Valid;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @Valid
    private Account from;

    @OneToOne
    @Valid
    private Account to;

    private Double amount;

    private String status;

    public Transaction() {}

    public Transaction(Account from, Account to, Double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.status = TransactionStatus.PENDING.name();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Account getFrom() {
        return from;
    }

    public void setFrom(Account from) {
        this.from = from;
    }

    public Account getTo() {
        return to;
    }

    public void setTo(Account to) {
        this.to = to;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void process() throws Exception{
        if (from.getBalance() >= amount) {
            from.withdraw(amount);
            to.deposit(amount);
            this.status = TransactionStatus.PAID.name();
        } else {
            this.status = TransactionStatus.REJECTED.name();
            throw new NotEnoughFundException("Not enough fund");
        }
    }
}