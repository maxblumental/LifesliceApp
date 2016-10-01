package com.blumental.lifesliceapp.repository;

import com.blumental.lifesliceapp.model.GetVideoByTagResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface VineService {

    String BASE_URL = "https://api.vineapp.com/";

    @GET("timelines/tags/{tag}")
    Observable<GetVideoByTagResponse> getVideos(@Path("tag") String tag, @Query("page") int pageId);
}
