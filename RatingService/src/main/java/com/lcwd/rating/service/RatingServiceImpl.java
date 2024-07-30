package com.lcwd.rating.service;

import com.lcwd.rating.entites.Rating;
import com.lcwd.rating.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;
    @Override
    public Rating createRating(Rating rating) {
        return null;
    }

    @Override
    public List<Rating> getAllRating() {
        return null;
    }

    @Override
    public Rating getRatingById(String ratingId) {
        return null;
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return null;
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return null;
    }
}
