package com.lcwd.rating.service;

import com.lcwd.rating.entites.Rating;

import java.util.List;

public interface RatingService {
    Rating createRating(Rating rating);

    List<Rating> getAllRating();
    Rating getRatingById(String ratingId);

    /**
     * Get all by userId
     */
    List<Rating> getRatingByUserId(String userId);

    /**
     * Get all rating by HotelId
     * @param hotelId
     * @return
     */
    List<Rating> getRatingByHotelId(String hotelId);
}
