package com.blumental.lifesliceapp.presenter;

import com.blumental.lifesliceapp.view.fragment.VideosView;

public interface VideosPresenter {

    void setView(VideosView view);

    void detach();

    void onResume();

    void onPause();

    void onTagChange(String tag);
}

