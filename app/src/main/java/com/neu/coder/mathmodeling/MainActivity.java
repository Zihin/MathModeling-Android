package com.neu.coder.mathmodeling;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    private RadioGroup main_radiogroup;
    private TabHost tabHost;
    private RadioButton tab_icon_weixin, tab_icon_address, tab_icon_friend, tab_icon_setting;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("数学建模");
        setSupportActionBar(toolbar);
        main_radiogroup = (RadioGroup) findViewById(R.id.main_radiogroup);

        tab_icon_weixin = (RadioButton) findViewById(R.id.tab_icon_weixin);
        tab_icon_address = (RadioButton) findViewById(R.id.tab_icon_address);
        tab_icon_friend = (RadioButton) findViewById(R.id.tab_icon_friend);
        tab_icon_setting = (RadioButton) findViewById(R.id.tab_icon_setting);

        tabHost = (TabHost) this.findViewById(R.id.tabhost);
        LocalActivityManager mLocalActivityManager = new LocalActivityManager(this, false);
        mLocalActivityManager.dispatchCreate(savedInstanceState);
        tabHost.setup(mLocalActivityManager);

        CheckListener checkradio = new CheckListener();
        main_radiogroup.setOnCheckedChangeListener(checkradio);

        tabHost.addTab(tabHost.newTabSpec("tag1").setIndicator("1").setContent(new Intent(this,MOOCActivity.class)));
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
