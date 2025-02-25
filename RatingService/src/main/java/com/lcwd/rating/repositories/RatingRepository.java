package com.lcwd.rating.repositories;

import com.lcwd.rating.entites.Rating;
import com.lcwd.rating.service.RatingService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,String> {
    List<Rating> findByUserId(String userId);
    List<Rating> findByHotelId(String hotelId);

}
