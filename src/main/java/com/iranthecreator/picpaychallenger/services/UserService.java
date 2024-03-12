package com.iranthecreator.picpaychallenger.services;

import com.iranthecreator.picpaychallenger.domain.user.User;
import com.iranthecreator.picpaychallenger.domain.user.UserType;
import com.iranthecreator.picpaychallenger.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if (sender.getUserType() == UserType.MERCHANT) {
            throw new Exception("The Merchant user is not Authorized to send amount");
        }

        if (sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Insuficient Balance");
        }
    }

    public User FindUserById(Long id) throws Exception {
        return this.repository.findUserById(id).orElseThrow(()-> new Exception("User Not Found"));

    }


    public void saveUser(User user) {
        this.repository.save(user);
    }



}
