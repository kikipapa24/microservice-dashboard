package com.lcwd.user.UserService.services;

import com.lcwd.user.UserService.entities.User;

import java.util.List;

public interface UserService {

    /**
     * Creating new user in Database
     * @param user
     * @return
     */
    User saveUser(User user);

    /**
     * Get all entries related from user
     * @return
     */
    List<User> getAllUser();

    /**
     * getUserById based on ID
     * @param userId
     * @return
     */
    User getUserById(String userId);

    /**
     * TO delete entries from DB based on Id
     * @param userId
     */
    void deleteById(String userId);

    /**
     * To update any user in db
     * @param user
     * @return
     */
    User updateUser(User user,String userId);

}
