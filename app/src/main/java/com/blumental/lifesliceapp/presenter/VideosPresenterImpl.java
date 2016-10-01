package com.blumental.lifesliceapp.presenter;

import com.blumental.lifesliceapp.interactor.GetVideoListByTagInteractor;
import com.blumental.lifesliceapp.view.fragment.VideosView;

import javax.inject.Inject;

public class VideosPresenterImpl implements VideosPresenter {

    private VideosView view;

    GetVideoListByTagInteractor interactor;

    @Inject
    public VideosPresenterImpl(GetVideoListByTagInteractor interactor) {
        this.interactor = interactor;
    }

    public void setView(VideosView view) {
        this.view = view;
    }

    @Override
    public void detach() {
        view = null;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onTagChange(String tag) {

    }
}
