package com.lcwd.user.UserService.util;

import java.util.UUID;

public class CommonUtil {
    public static String generateRandom(){
        UUID randomUUID = UUID.randomUUID();
        String uuidString = "User_".concat(randomUUID.toString().replace("-", ""));
        return  uuidString.substring(0, 15);
    }
}
