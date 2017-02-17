package com.bee.autoweather;

import android.app.Application;

import com.bee.autoweather.network.AutoWeatherApi;
import com.bee.autoweather.network.AutoWeatherRestClient;
import com.squareup.leakcanary.LeakCanary;

import io.objectbox.BoxStore;

/**
 * Created by barisemreefe on 03/02/2017.
 */

public class AutoWeatherApplication extends Application {
    private static AutoWeatherApi autoWeatherApi;
    private static AutoWeatherRestClient autoWeatherRestClient;
    private static BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        autoWeatherRestClient = new AutoWeatherRestClient(this);
        initializeLeakCanary();
        boxStore = MyObjectBox.builder().androidContext(this).build();
    }
    public static AutoWeatherApi getApi() {
        if(autoWeatherApi == null) {
            autoWeatherApi = autoWeatherRestClient.getApi();
        }
        return autoWeatherApi;
    }
    private void initializeLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
    public BoxStore getBoxStore() {
        return boxStore;
    }
}
