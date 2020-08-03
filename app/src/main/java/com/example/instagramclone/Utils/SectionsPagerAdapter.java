package com.example.instagramclone.Utils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Class taht stores fragments for tabs
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    public static final String Tag = "SectionPagerAdapter";

    public static List<Fragment> mFragmentList = new ArrayList<>();

    public SectionsPagerAdapter (FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment (Fragment fragment) {
        mFragmentList.add(fragment);
    }
}
