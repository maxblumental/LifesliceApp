package com.blumental.lifesliceapp.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
        MainActivity activity = (MainActivity) getActivity();
        activity.switchToVideosTab();
    }
}
