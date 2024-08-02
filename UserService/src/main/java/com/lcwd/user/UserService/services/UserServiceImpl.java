package com.lcwd.user.UserService.services;

import com.lcwd.user.UserService.entities.Hotel;
import com.lcwd.user.UserService.entities.Rating;
import com.lcwd.user.UserService.entities.User;
import com.lcwd.user.UserService.external.services.HotelService;
import com.lcwd.user.UserService.repositories.UserRepository;
import com.lcwd.user.UserService.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private HotelService hotelService;

    @Override
    public User saveUser(User user) {
        user.setUserId(CommonUtil.generateRandom());
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new RuntimeException("User with given id is not found " + userId));
        /**
         * Fetch rating of the above user from Rating Service
         * http://localhost:8083/ratings/users/user_df37bad4-1cfd-484d-9eb1-e8844af55c3e
         */
        Rating[] ratingsOfUsers = restTemplate.getForObject("http://RATING/ratings/users/" + user.getUserId(),
                Rating[].class);
        List<Rating> ratings = Arrays.stream(ratingsOfUsers).toList();
        logger.info(" {} ", ratingsOfUsers);
        List<Rating> ratingList = ratings.stream().map(rating -> {
            /**
             * Below code is required when we are using rest template
             * ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/" + rating.getHotelId(), Hotel.class);
             Hotel hotel = forEntity.getBody();
             logger.info("response status code {} ", forEntity.getStatusCode());
             */
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            /**
             * Above code required when we are using Feign client
             */
            rating.setHotel(hotel);
            return rating;
        }).toList();
        user.setRatings(ratingList);
        return user;
    }

    @Override
    public User deleteById(String userId) {
        userRepository.deleteById(userId);
        return null;
    }

    @Override
    public User updateUser(User user, String userId) {
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            return userRepository.save(user);
        } else {
            throw new RuntimeException("Entity not found with id: " + userId);
        }
    }
}
