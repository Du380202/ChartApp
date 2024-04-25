package com.example.chartapp.api;

import com.example.chartapp.Model.DoanhThu;
import com.example.chartapp.Model.ResponseObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BaoCaoApi {

    @GET("reports")
    Call<ResponseObject> layBaoCaoDoanhThu(@Query("year") int nam);
}
