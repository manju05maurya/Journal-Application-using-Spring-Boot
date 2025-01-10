package com.springboot.journalApplication.Service;

import com.springboot.journalApplication.Entity.User;
import com.springboot.journalApplication.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getUserByName(String username){
        return userRepository.findByUsername(username);
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(String username){
        User user = userRepository.findByUsername(username);
        userRepository.delete(user);

    }

    public void updateUser( User newUser){
       User oldUser= userRepository.findByUsername(newUser.getUsername());
       if(oldUser != null){
           oldUser.setUsername(newUser.getUsername());
           oldUser.setPassword(newUser.getPassword());
       }

    }
}
