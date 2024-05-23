package com.vvt.icommerce.paymentservice.service;

import com.vvt.icommerce.paymentservice.model.Account;

public interface PaymentService {
    Iterable<Account> getAllAccounts();

    Account getAccount(Long id);

    Account save(Account account);
}
