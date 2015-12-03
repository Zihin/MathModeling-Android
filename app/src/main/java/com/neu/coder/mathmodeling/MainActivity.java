package com.neu.coder.mathmodeling;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;

import com.neu.coder.mathmodeling.Club.ClubActivity;
import com.neu.coder.mathmodeling.MOOC.MOOCActivity;
import com.neu.coder.mathmodeling.Quiz.QuizActivity;
import com.neu.coder.mathmodeling.Resources.ResourcesActivity;

public class MainActivity extends AppCompatActivity {

    private RadioGroup main_radiogroup;
    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_radiogroup = (RadioGroup) findViewById(R.id.main_radiogroup);

        tabHost = (TabHost) this.findViewById(R.id.tabhost);
        LocalActivityManager mLocalActivityManager = new LocalActivityManager(this, false);
        mLocalActivityManager.dispatchCreate(savedInstanceState);
        tabHost.setup(mLocalActivityManager);

        CheckListener checkradio = new CheckListener();
        main_radiogroup.setOnCheckedChangeListener(checkradio);

        tabHost.addTab(tabHost.newTabSpec("tag1").setIndicator("0").setContent(new Intent(this,MOOCActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("tag2").setIndicator("1").setContent(new Intent(this,ClubActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("tag3").setIndicator("2").setContent(new Intent(this,QuizActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("tag4").setIndicator("3").setContent(new Intent(this,ResourcesActivity.class)));
    }

    public class CheckListener implements OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch(checkedId){
                case R.id.tab_icon_weixin:
                    tabHost.setCurrentTab(0);
                    break;
                case R.id.tab_icon_address:
                    tabHost.setCurrentTab(1);
                    break;
                case R.id.tab_icon_friend:
                    tabHost.setCurrentTab(2);
                    break;
                case R.id.tab_icon_setting:
                    tabHost.setCurrentTab(3);
                    break;
            }
        }
    }
}
