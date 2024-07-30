package com.lcwd.rating.util;

import java.util.UUID;

public class CommonUtil {
    public static String generateRandom(){
        UUID randomUUID = UUID.randomUUID();
        String uuidString = "Rating_".concat(randomUUID.toString().replace("-", ""));
        return  uuidString.substring(0, 15);
    }
}
