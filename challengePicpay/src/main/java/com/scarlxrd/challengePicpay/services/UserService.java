package com.scarlxrd.challengePicpay.services;

import com.scarlxrd.challengePicpay.domain.user.User;
import com.scarlxrd.challengePicpay.domain.user.UserTyper;
import com.scarlxrd.challengePicpay.dtos.UserDTO;
import com.scarlxrd.challengePicpay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
        Optional<User> users = repository.findById(id);
        return users.orElseThrow(()->new Exception("Usuário não encotrado"));
    }
    public User createUser(UserDTO data){
        User newUser = new User(data);
        this.saveUser(newUser);
        return  newUser;
    }
    public List<User> getAllUsers(){
        return this.repository.findAll();
    }
    public void saveUser(User user){
        this.repository.save(user);
    }
}
