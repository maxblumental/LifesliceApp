package com.blumental.lifesliceapp.view.fragment;

import com.blumental.lifesliceapp.model.Record;

import java.util.List;

public interface VideosView {

    void showRecords(List<Record> records);

    void showError(String message);

    void showProgress();

    void hideProgress();
}