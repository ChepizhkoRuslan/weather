package com.chepizhko.weather.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {


    @GET("data/2.5/weather?")
    Call<ResponseBody> getWeather(@Query("lat") int lat, @Query("lon") int lon, @Query("APPID")String appid);

}