package com.blumental.lifesliceapp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.blumental.lifesliceapp.R;
import com.blumental.lifesliceapp.view.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TagFragment extends Fragment implements TabView {

    public static final String TAG_CHANGED_ACTION = "tag changed action";
    public static final String TAG_KEY = "tag key";

    @BindView(R.id.tagEditText)
    EditText tagEditText;

    @OnClick(R.id.nextButton)
    public void handleNextButtonClick() {
        switchToVideosTab();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tag_fragment, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void switchToVideosTab() {
        broadcastTag(tagEditText.getText().toString());
        MainActivity activity = (MainActivity) getActivity();
        activity.switchToVideosTab();
    }

    private void broadcastTag(String value) {
        Intent intent = new Intent(TAG_CHANGED_ACTION);
        intent.putExtra(TAG_KEY, value);
        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
    }
}
