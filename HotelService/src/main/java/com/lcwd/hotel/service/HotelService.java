package com.lcwd.hotel.service;

import com.lcwd.hotel.entities.Hotel;
import org.springframework.stereotype.Service;

import java.util.List;


public interface HotelService {
    Hotel createHotel(Hotel hotel);
    List<Hotel> getAllHotel();
    Hotel getHotelById(String id);
    Hotel deleteById(String id);
    Hotel updateHotel(Hotel hotel);

}
