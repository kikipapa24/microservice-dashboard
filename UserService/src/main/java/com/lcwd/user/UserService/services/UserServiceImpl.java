package com.lcwd.user.UserService.services;

import com.lcwd.user.UserService.entities.User;
import com.lcwd.user.UserService.repositories.UserRepository;
import com.lcwd.user.UserService.util.CommonUtil;
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
       user.setUserId(CommonUtil.generateRandom());
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
    public User deleteById(String userId) {
        userRepository.deleteById(userId);
        return null;
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
