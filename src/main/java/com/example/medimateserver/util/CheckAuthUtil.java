package com.example.medimateserver.util;

import com.example.medimateserver.config.jwt.JwtProvider;
import com.example.medimateserver.dto.UserDto;

public class CheckAuthUtil {
    private static CheckAuthUtil checkAuthUtil;

    public static CheckAuthUtil gI() {
        if (checkAuthUtil == null) {
            checkAuthUtil = new CheckAuthUtil();
        }
        return checkAuthUtil;
    }
    public boolean check(String token, String databaseToken, Integer id) {
        String oldToken = token;
        token = JwtProvider.getUsernameFromToken(token);
        UserDto user = GsonUtil.gI().fromJson(token, UserDto.class);
        if (user.getId() == id && oldToken.equals(databaseToken)) {
            return true;
        }
        return false;
    }
}
