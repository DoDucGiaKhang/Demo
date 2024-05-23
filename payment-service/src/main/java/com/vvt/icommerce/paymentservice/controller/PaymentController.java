package com.vvt.icommerce.paymentservice.controller;

import com.vvt.icommerce.paymentservice.dto.AccountDTO;
import com.vvt.icommerce.paymentservice.model.Account;
import com.vvt.icommerce.paymentservice.service.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @Autowired
    PaymentServiceImpl paymentService;

    @GetMapping(value = { "/accounts" })
    public @NotNull Iterable<Account> getAccounts(@RequestParam(required = false) String keyword) {
        return paymentService.getAllAccounts();
    }

    @GetMapping(value = {"/accounts/{id}"})
    public Account getAccount(@PathVariable Long id) {
        return paymentService.getAccount(id);
    }

    @PostMapping(value = {"/accounts"})
    public ResponseEntity<Account> updateAccount(@RequestBody AccountDTO accountDto){
        Account account = paymentService.getAccount(accountDto.getId());
        if (accountDto.getId() != null) account.setId(accountDto.getId());
        account.setBalance(accountDto.getBalance());
        paymentService.save(account);
        return new ResponseEntity(account, HttpStatus.OK);
    }

    @PostMapping(value = {"/accounts/create"})
    public ResponseEntity<Account> createAccount(@RequestBody AccountDTO accountDto){
        Account account = new Account(accountDto.getUserId(), accountDto.getBalance());
        account.setBalance(accountDto.getBalance());
        paymentService.save(account);
        return new ResponseEntity(account, HttpStatus.OK);
    }
}