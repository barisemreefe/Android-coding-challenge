package com.bee.autoweather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.hey).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWeatherInfo("istanbul");
            }
        });
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
        Job job = dispatcher.newJobBuilder().setService(WeatherJob.class).setTag(WeatherJob.TAG).setRecurring(true).setLifetime(Lifetime.UNTIL_NEXT_BOOT).setTrigger(Trigger.executionWindow(1,10)).build();
        dispatcher.schedule(job);
    }

    private void getWeatherInfo(String city) {
        Call<Object> weatherCall = AutoWeatherApplication.getApi().getWeatherFor(city,getString(R.string.open_weather_map_key));
        weatherCall.enqueue(weatherCallback);
    }
    private final Callback<Object> weatherCallback = new Callback<Object>() {

        @Override
        public void onResponse(Call<Object> call, Response<Object> response) {

        }

        @Override
        public void onFailure(Call<Object> call, Throwable t) {

        }
    };
}
