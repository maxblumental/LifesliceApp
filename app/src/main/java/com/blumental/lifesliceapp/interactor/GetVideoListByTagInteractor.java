package com.blumental.lifesliceapp.interactor;

import com.blumental.lifesliceapp.model.GetVideoByTagResponse;
import com.blumental.lifesliceapp.model.Record;
import com.blumental.lifesliceapp.model.TagsData;
import com.blumental.lifesliceapp.repository.VineService;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

import static rx.android.schedulers.AndroidSchedulers.mainThread;
import static rx.schedulers.Schedulers.io;

public class GetVideoListByTagInteractor {

    private VineService vineService;

    @Inject
    public GetVideoListByTagInteractor(VineService vineService) {
        this.vineService = vineService;
    }

    public Observable<List<Record>> createObservable(String tag, int pageId) {
        return vineService.getVideos(tag, pageId)
                .subscribeOn(io())
                .map(new Func1<GetVideoByTagResponse, List<Record>>() {
                    @Override
                    public List<Record> call(GetVideoByTagResponse response) {
                        return getRecordList(response);
                    }
                })
                .observeOn(mainThread());
    }

    private List<Record> getRecordList(GetVideoByTagResponse response) {
        TagsData data = response.getData();
        if (data == null || data.getRecords() == null) {
            return null;
        }
        return Arrays.asList(data.getRecords());
    }
}
