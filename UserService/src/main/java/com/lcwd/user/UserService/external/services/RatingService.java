package com.lcwd.user.UserService.external.services;

import com.lcwd.user.UserService.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@FeignClient(name = "RATING")
public interface RatingService {
    /**
     * get
     */
    @PostMapping("/ratings")
    ResponseEntity<Rating> createRating(@RequestBody Rating values);

    @PutMapping("/ratings/{ratingId}")
    ResponseEntity<Rating> updateRating(@PathVariable String ratingId, Rating rating);

    @DeleteMapping("/ratings/{ratingId}")
    void deleteRating(@PathVariable String ratingId);
}
