package com.blumental.lifesliceapp.repository;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class VineServiceFactory {

    @Provides
    public VineService create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(VineService.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(VineService.class);
    }
}
