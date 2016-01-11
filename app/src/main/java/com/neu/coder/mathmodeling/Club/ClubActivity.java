package com.neu.coder.mathmodeling.Club;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.neu.coder.mathmodeling.R;

public class ClubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);
<<<<<<< HEAD
=======

        pager = (ViewPager) findViewById(R.id.pager);
        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        ArrayList<String> titles = new ArrayList<>();
        titles.add("新闻");
        titles.add("活动");
        titles.add("简介");
        titles.add("论坛");
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (String s : titles) {
            Bundle bundle = new Bundle();
            bundle.putString("title", s);
            fragments.add(FragmentContent.getInstance(bundle));
        }
        pager.setAdapter(new MyFrPagerAdapter(getSupportFragmentManager(), titles, fragments));
        tabs.setViewPager(pager);
        pager.setCurrentItem(0);
>>>>>>> dev
    }
}
