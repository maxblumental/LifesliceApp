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
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.blumental.lifesliceapp.App;
import com.blumental.lifesliceapp.R;
import com.blumental.lifesliceapp.model.Record;
import com.blumental.lifesliceapp.presenter.VideosPresenter;
import com.blumental.lifesliceapp.view.adapter.VideoListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;

public class VideosFragment extends Fragment implements VideosView {

    private VideosPresenter presenter;

    private BroadcastReceiver tagChangeReceiver;

    @BindView(R.id.videoView)
    VideoView videoView;

    @BindView(R.id.videoList)
    RecyclerView videoList;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private VideoListAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter = App.component.videosPresenter();
        presenter.setView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.videos_fragment, container, false);
        ButterKnife.bind(this, view);

        videoList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new VideoListAdapter();
        videoList.setAdapter(adapter);

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

    @Override
    public void showRecords(List<Record> records) {
        adapter.setRecords(records);
    }

    @Override
    public void showError(String message) {
        makeText(getContext(), message, LENGTH_LONG).show();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
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
