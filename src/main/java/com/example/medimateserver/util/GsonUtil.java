package com.example.medimateserver.util;

import com.google.gson.Gson;

public class GsonUtil {
    private static Gson gson;

    public static Gson gI() {
        // Trả về thể hiện duy nhất của lớp
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

}
