package com.example.medimateserver.model;

import com.google.gson.Gson;

public class Gcustome {
    private static Gson gson;

    private Gcustome() {
        // Khởi tạo Gson
        gson = new Gson();
    }

    public static Gson getInstance() {
        // Trả về thể hiện duy nhất của lớp
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

}
