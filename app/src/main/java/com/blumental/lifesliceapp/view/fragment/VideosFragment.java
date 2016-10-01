package com.blumental.lifesliceapp.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.blumental.lifesliceapp.R;
import com.blumental.lifesliceapp.view.adapter.VideoListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideosFragment extends Fragment implements VideosView {

    @BindView(R.id.videoView)
    VideoView videoView;

    @BindView(R.id.videoList)
    RecyclerView videoList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.videos_fragment, container, false);
        ButterKnife.bind(this, view);

        videoList.setLayoutManager(new LinearLayoutManager(getContext()));
        videoList.setAdapter(new VideoListAdapter());

        return view;
    }
}
