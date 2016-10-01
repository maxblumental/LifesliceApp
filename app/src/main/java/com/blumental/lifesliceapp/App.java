package com.blumental.lifesliceapp;

import android.app.Application;

import com.blumental.lifesliceapp.di.ApplicationComponent;
import com.blumental.lifesliceapp.di.DaggerApplicationComponent;

public class App extends Application {
    public static ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder().build();
    }
}
