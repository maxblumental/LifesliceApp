package com.blumental.lifesliceapp.di;

import com.blumental.lifesliceapp.presenter.VideosPresenterImpl;
import com.blumental.lifesliceapp.repository.VineServiceFactory;

import dagger.Component;

@Component(modules = {VineServiceFactory.class})
public interface ApplicationComponent {
    VideosPresenterImpl videosPresenter();
}
