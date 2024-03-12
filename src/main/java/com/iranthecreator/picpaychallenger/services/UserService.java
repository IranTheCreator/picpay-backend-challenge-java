package com.iranthecreator.picpaychallenger.services;

import com.iranthecreator.picpaychallenger.domain.user.User;
import com.iranthecreator.picpaychallenger.domain.user.UserType;
import com.iranthecreator.picpaychallenger.dto.UserDTO;
import com.iranthecreator.picpaychallenger.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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
        System.out.println(id);
        return this.repository.findUserById(id).orElseThrow(()-> new Exception("User Not Found"));

    }

    public User createUser(UserDTO data) {
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;

    }

    public ResponseEntity<List<User>> getAllUsers() {
       List<User> users = this.repository.findAll();
       return new ResponseEntity<>(users, HttpStatus.OK);

    }


    public void saveUser(User user) {
        this.repository.save(user);
    }





}
