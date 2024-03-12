package com.iranthecreator.picpaychallenger.services;

import com.iranthecreator.picpaychallenger.domain.transaction.Transaction;
import com.iranthecreator.picpaychallenger.domain.user.User;
import com.iranthecreator.picpaychallenger.dto.TransactionDTO;
import com.iranthecreator.picpaychallenger.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotifyService notifyService;

    public Transaction createTransaction(TransactionDTO transaction) throws Exception {
        User sender = this.userService.FindUserById(transaction.senderId());
        User receiver = this.userService.FindUserById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.value());

        boolean isAuthorized = this.authorizeTransaction(sender, transaction.value());

        if (!isAuthorized) {
            throw new Exception("Unauthorized Transaction");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));

        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        this.repository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        this.notifyService.sendNotification(sender, "transaction send sucessfuly");
        this.notifyService.sendNotification(receiver, "transaction received sucessfuly");

        return newTransaction;

    }

    public boolean authorizeTransaction(User sender, BigDecimal value) {
       ResponseEntity<Map> authorizationResponse
               = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);

       if (authorizationResponse.getStatusCode() == HttpStatus.OK) {
           String message = (String) Objects.requireNonNull(authorizationResponse.getBody()).get("message");
           return "Autorizado".equalsIgnoreCase(message);

       } else return false;
    }

}
