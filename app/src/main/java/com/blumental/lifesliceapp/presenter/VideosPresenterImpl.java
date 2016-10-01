package com.blumental.lifesliceapp.presenter;

import com.blumental.lifesliceapp.interactor.GetVideoListByTagInteractor;
import com.blumental.lifesliceapp.model.Record;
import com.blumental.lifesliceapp.view.fragment.VideosView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

public class VideosPresenterImpl implements VideosPresenter {

    private VideosView view;

    GetVideoListByTagInteractor interactor;

    Subscription getVideosSubscription;

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
        if (getVideosSubscription != null) {
            getVideosSubscription.unsubscribe();
        }
    }

    @Override
    public void onTagChange(String tag) {
        view.showProgress();
        getVideosSubscription = interactor.createObservable(tag, 0)
                .subscribe(new Action1<List<Record>>() {
                    @Override
                    public void call(List<Record> records) {
                        view.hideProgress();
                        view.showRecords(records);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        view.hideProgress();
                        view.showError(throwable.getMessage());
                    }
                });

    }
}
