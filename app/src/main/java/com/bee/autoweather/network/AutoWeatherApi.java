package com.bee.autoweather.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by barisemreefe on 03/02/2017.
 */

public interface AutoWeatherApi {
    @GET("weather/")
    Call<Object> getWeatherFor(@Query("q") String city,@Query("APPID") String apiKey);

}
