package com.lcwd.hotel.util;

import java.util.UUID;

public class CommonUtil {
    public static String generateRandom(){
        UUID randomUUID = UUID.randomUUID();
        String uuidString = "Hotel_".concat(randomUUID.toString().replace("-", ""));
        return  uuidString.substring(0, 15);
    }
}
