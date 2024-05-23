package com.vvt.icommerce.paymentservice.service;

import com.vvt.icommerce.paymentservice.exception.AccountNotFoundException;
import com.vvt.icommerce.paymentservice.exception.NotEnoughFundException;
import com.vvt.icommerce.paymentservice.exception.OrderAlreadyCancelledException;
import com.vvt.icommerce.paymentservice.exception.OrderNotFoundException;
import com.vvt.icommerce.paymentservice.messaging.OrderProcessor;
import com.vvt.icommerce.paymentservice.messaging.OrderMessage;
import com.vvt.icommerce.paymentservice.model.Account;
import com.vvt.icommerce.paymentservice.model.OrderStatus;
import com.vvt.icommerce.paymentservice.model.PaymentOrder;
import com.vvt.icommerce.paymentservice.model.Transaction;
import com.vvt.icommerce.paymentservice.model.TransactionStatus;
import com.vvt.icommerce.paymentservice.repository.AccountRepository;
import com.vvt.icommerce.paymentservice.repository.PaymentOrderRepository;
import com.vvt.icommerce.paymentservice.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private OrderProcessor orderProcessor;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PaymentOrderRepository paymentOrderRepository;

    @Autowired
    private TransactionRepository transactionRepository;


    private Account getIcommerceAccount() {
        return accountRepository.findAccountByUserId(-1L);
    }

    @StreamListener(OrderProcessor.IN_ORDERS)
    public void createOrder(PaymentOrder order) {
        log.info("Pulling order: {} for ${} with status {}", order.getOrderId(), order.getTotalPrice(), order.getStatus());
        Transaction transaction = null;
        try {
            Account userAccount = accountRepository.findById(
                    order.getUserId()).orElseThrow(() -> new AccountNotFoundException("Account not found"));
            Account icommerceAccount = getIcommerceAccount();
            if (icommerceAccount == null) throw new AccountNotFoundException("Icommerce account not found");
            transaction = new Transaction(userAccount, icommerceAccount, order.getTotalPrice());
            transactionRepository.save(transaction);
            transaction.process();
            accountRepository.save(userAccount);
            accountRepository.save(icommerceAccount);
            order.setStatus(OrderStatus.PAID.name());
        } catch (NotEnoughFundException e) {
            order.setStatus(OrderStatus.NOT_ENOUGH_FUND.name());
            transaction.setStatus(TransactionStatus.REJECTED.name());
        } catch (Exception e) {
            order.setStatus(OrderStatus.REJECTED.name());
            transaction.setStatus(TransactionStatus.REJECTED.name());
        }
        paymentOrderRepository.save(order);
        transactionRepository.save(transaction);
        OrderMessage orderMessage = new OrderMessage(order);
        log.info("Publishing order status: {}, id: {} for ${}", order.getStatus(), order.getOrderId(), order.getTotalPrice());
        orderProcessor.outPaymentOrders().send(orderMessage.getMessage());
    }

    @StreamListener(OrderProcessor.IN_CANCELLATION_ORDERS)
    public void cancelOrder(PaymentOrder order) {
        log.info("Pulling order: {} for ${} with status {}", order.getOrderId(), order.getTotalPrice(), order.getStatus());
        Transaction transaction = null;
        try {
            PaymentOrder paidOrder = paymentOrderRepository.findById(order.getOrderId()).orElseThrow(() -> new OrderNotFoundException("Order not found"));
            if (paidOrder.getStatus() != OrderStatus.PAID.name()) {
                log.info("Order {} was already cancelled.", order.getOrderId());
                throw new OrderAlreadyCancelledException("Order already cancelled");
            }
            Account userAccount = accountRepository.findById(
                    order.getUserId()).orElseThrow(() -> new AccountNotFoundException("Account not found"));
            Account icommerceAccount = getIcommerceAccount();
            if (icommerceAccount == null) throw new AccountNotFoundException("Icommerce account not found");
            transaction = new Transaction(icommerceAccount, userAccount, order.getTotalPrice());
            transactionRepository.save(transaction);
            transaction.process();
            transactionRepository.save(transaction);
            accountRepository.save(userAccount);
            accountRepository.save(icommerceAccount);
            order.setStatus(OrderStatus.CANCELLED.name());
        } catch (Exception e) {
            order.setStatus(OrderStatus.CANCELLED.name());
        }
        paymentOrderRepository.save(order);
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Iterable<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccount(Long id) {
        return accountRepository
                .findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
    }
}