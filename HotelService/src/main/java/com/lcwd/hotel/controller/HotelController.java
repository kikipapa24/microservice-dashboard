package com.lcwd.hotel.controller;

import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @PostMapping(value = "/createhotel")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(hotel));
    }

    @GetMapping(value = "/{hotelId}")
    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    public ResponseEntity<Hotel> getHotelById(@PathVariable String hotelId){
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotelById(hotelId));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_internal')")
    public ResponseEntity<List<Hotel>> getAllHotel(){
        return ResponseEntity.ok(hotelService.getAllHotel());
    }


}
