package com.example.chartapp.api;

import com.example.chartapp.Model.HoaDon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HoaDonApi {

    @GET("products")
    Call<List<HoaDon>> getAllHoaDon();

}
