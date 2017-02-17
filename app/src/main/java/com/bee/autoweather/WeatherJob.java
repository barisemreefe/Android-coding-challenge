package com.bee.autoweather;

import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import io.objectbox.Box;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by barisemreefe on 09/02/2017.
 */

public class WeatherJob extends JobService {
    public static final String TAG = "WeatherJob";
    private String query;

    @Override
    public boolean onStartJob(JobParameters job) {
        Log.d(TAG, "run " + System.currentTimeMillis());
        getWeather("Istanbul");
        return false;
    }

    private void getWeather(String query) {
        Call<Object> weatherCall = AutoWeatherApplication.getApi().getWeatherFor(query,getString(R.string.open_weather_map_key));
        weatherCall.enqueue(weatherCallback);
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false;
    }

    private final Callback<Object> weatherCallback = new Callback<Object>() {

        @Override
        public void onResponse(Call<Object> call, Response<Object> response) {
            if (response.isSuccessful()) {
                Box<WeatherInfo> weatherInfoBox = ((AutoWeatherApplication) getApplication()).getBoxStore().boxFor(WeatherInfo.class);
                weatherInfoBox.put(new WeatherInfo(System.currentTimeMillis(),));
            }
        }

        @Override
        public void onFailure(Call<Object> call, Throwable t) {

        }
    };
}
