package com.scarlxrd.challengePicpay.services;

import com.scarlxrd.challengePicpay.domain.user.User;
import com.scarlxrd.challengePicpay.domain.user.UserTyper;
import com.scarlxrd.challengePicpay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    public void validateTransction(User sender , BigDecimal amount) throws Exception {
        if(sender.getUserTyper()== UserTyper.MERCHANT){
            throw new Exception("Usuário do tipo lojista não está autorizado a realizar transação");
        }
        if(sender.getBalance().compareTo(amount)<0){
            throw new Exception("Saldo insuficiente");
        }

    }
    public User findUserById(Long id) throws Exception {
        return this.repository.findUserById(id).orElseThrow(()-> new Exception("Usuário não encontrado"));
    }
    public void saveUser(User user){
        this.repository.save(user);
    }
}
