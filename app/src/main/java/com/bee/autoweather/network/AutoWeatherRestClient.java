package com.bee.autoweather.network;

import android.content.Context;

import com.bee.autoweather.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by barisemreefe on 03/02/2017.
 */

public class AutoWeatherRestClient {
    private static final String TAG = "AutoWeatherRestClient";
    private static final String BASE_URL="http://api.openweathermap.org/data/2.5/forecast/";
    private final static int CONNECTION_TIMEOUT = 15;
    private final static int READ_TIMEOUT = 15;
    private AutoWeatherApi api;
    private OkHttpClient okHttpClient;

    public AutoWeatherRestClient(final Context context) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel((BuildConfig.DEBUG) ?
                HttpLoggingInterceptor.Level.BODY :
                HttpLoggingInterceptor.Level.NONE);
        okHttpClient = new OkHttpClient.Builder().readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(BASE_URL).client(okHttpClient)
                .build();
        api = retrofit.create(AutoWeatherApi.class);
    }

    public AutoWeatherApi getApi() {
        return api;
    }
}
