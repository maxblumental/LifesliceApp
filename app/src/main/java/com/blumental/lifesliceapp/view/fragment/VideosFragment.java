package com.blumental.lifesliceapp.view.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.blumental.lifesliceapp.R;
import com.blumental.lifesliceapp.presenter.VideosPresenter;
import com.blumental.lifesliceapp.presenter.VideosPresenterImpl;
import com.blumental.lifesliceapp.view.adapter.VideoListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideosFragment extends Fragment implements VideosView {

    private VideosPresenter presenter;

    private BroadcastReceiver tagChangeReceiver;

    @BindView(R.id.videoView)
    VideoView videoView;

    @BindView(R.id.videoList)
    RecyclerView videoList;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter = new VideosPresenterImpl();
        presenter.setView(this);
    }

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

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
        registerTagChangeReceiver();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(tagChangeReceiver);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.detach();
    }

    private void registerTagChangeReceiver() {
        IntentFilter intentFilter =
                new IntentFilter(TagFragment.TAG_CHANGED_ACTION);
        tagChangeReceiver = createTagChangeBroadCastReceiver();
        LocalBroadcastManager.getInstance(getContext())
                .registerReceiver(tagChangeReceiver, intentFilter);
    }

    private BroadcastReceiver createTagChangeBroadCastReceiver() {
        return new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String tag = intent.getStringExtra(TagFragment.TAG_KEY);
                presenter.onTagChange(tag);
            }
        };
    }
}
