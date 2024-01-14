package com.scarlxrd.challengePicpay.services;

import com.scarlxrd.challengePicpay.domain.transaction.Transaction;
import com.scarlxrd.challengePicpay.domain.user.User;
import com.scarlxrd.challengePicpay.dtos.TransactionDto;
import com.scarlxrd.challengePicpay.repositories.TransctionRepository;
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
    private TransctionRepository transctionRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;


    public Transaction createTransaction(TransactionDto transaction) throws Exception {
        User sender = userService.findUserById(transaction.senderId());
        User receiver =userService.findUserById(transaction.receiverId());
        userService.validateTransction(sender,transaction.value());

        boolean isAuthorized = this.authorizeTransaction(sender, transaction.value());
        if(!isAuthorized){
            throw new Exception("Transação não autorizada");
        }

        Transaction transaction1 = new Transaction();
        transaction1.setAmount(transaction.value());
        transaction1.setSender(sender);
        transaction1.setReceiver(receiver);
        transaction1.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        this.transctionRepository.save(transaction1);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);
        this.notificationService.sendNotification(sender, "Transação realizada com sucesso");
        this.notificationService.sendNotification(receiver, "Transação recebida com sucesso");
        return  transaction1;

    }
    public boolean authorizeTransaction(User sender, BigDecimal value){
      ResponseEntity<Map> authorizationResponse =restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);
      if(authorizationResponse.getStatusCode()== HttpStatus.OK ){
          String message = (String) Objects.requireNonNull(authorizationResponse.getBody()).get("message");
          return "Autorizado".equalsIgnoreCase(message);
      }else return false;
    }
}
