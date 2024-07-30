package com.lcwd.hotel.service;

import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.repositories.HotelRepository;
import com.lcwd.hotel.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HotelServiceImpl implements HotelService{
    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel createHotel(Hotel hotel) {
        hotel.setId(CommonUtil.generateRandom());
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotel() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(String id) {
        return hotelRepository.findById(id).
                orElseThrow(()-> new RuntimeException("Hotel with given id is not found "+id));
    }

    @Override
    public Hotel deleteById(String id) {
        return null;
    }

    @Override
    public Hotel updateHotel(Hotel hotel) {
        return null;
    }
}
