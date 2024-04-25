package com.example.chartapp.constant;

import java.util.ArrayList;
import java.util.List;

public class Constant {
    public static final String BASE_URL = "https://7bc9-2001-ee1-df0a-4e90-38de-decc-ba3b-a5a0.ngrok-free.app/api/";
    public static List<String> gioiTinhOptions = new ArrayList<>();
    static {
        gioiTinhOptions.add("Nam");
        gioiTinhOptions.add("Nữ");
        gioiTinhOptions.add("Unisex");
    }

}
