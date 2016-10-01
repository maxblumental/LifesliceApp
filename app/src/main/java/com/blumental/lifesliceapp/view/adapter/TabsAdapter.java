package com.blumental.lifesliceapp.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.blumental.lifesliceapp.view.fragment.TagFragment;
import com.blumental.lifesliceapp.view.fragment.VideosFragment;

public class TabsAdapter extends FragmentPagerAdapter {

    private final static int TABS_NUMBER = 2;

    public TabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TagFragment();
            case 1:
                return new VideosFragment();
        }

        throw new IndexOutOfBoundsException("Invalid tab index!");
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Enter tag";
            case 1:
                return "Videos";
        }

        throw new IndexOutOfBoundsException("Invalid tab index!");
    }

    @Override
    public int getCount() {
        return TABS_NUMBER;
    }
}
