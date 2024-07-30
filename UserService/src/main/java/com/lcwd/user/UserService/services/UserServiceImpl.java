package com.lcwd.user.UserService.services;

import com.lcwd.user.UserService.entities.User;
import com.lcwd.user.UserService.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;


    @Override
    public User saveUser(User user) {
       String randomUserId = "user_"+ UUID.randomUUID();
       user.setUserId(randomUserId);
        return  userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        return userRepository.findById(userId).
                orElseThrow(()-> new RuntimeException("User with given id is not found "+userId));
    }

    @Override
    public void deleteById(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(User user,String userId) {
        Optional<User> existingUser = userRepository.findById(userId);
        if(existingUser.isPresent()){
           return userRepository.save(user);
        }else {
            throw new RuntimeException("Entity not found with id: " + userId);
        }
    }
}
