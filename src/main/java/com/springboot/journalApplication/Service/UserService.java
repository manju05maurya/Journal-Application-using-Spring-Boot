package com.springboot.journalApplication.Service;

import com.springboot.journalApplication.Entity.User;
import com.springboot.journalApplication.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getUserByName(String username){
        return userRepository.findByUsername(username);
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public void saveNewUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("array"));
        userRepository.save(user);
    }

    public void deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        userRepository.delete(user);

    }

    public void updateUser( User newUser){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User oldUser= userRepository.findByUsername(username);
       if(oldUser != null){
           oldUser.setUsername(newUser.getUsername());
           oldUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
           oldUser.setRoles(Arrays.asList("array"));
           userRepository.save(oldUser);
       }


    }
}
