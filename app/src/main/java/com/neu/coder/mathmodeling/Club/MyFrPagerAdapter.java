package com.neu.coder.mathmodeling.Club;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zihin on 1/10/2016.
 */
public class MyFrPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<String> titles;

    private List<Fragment> fragments;

    public MyFrPagerAdapter(FragmentManager fm, ArrayList<String> list, List<Fragment> fragments) {
        super(fm);
        this.titles = list;
        this.fragments = fragments;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
}