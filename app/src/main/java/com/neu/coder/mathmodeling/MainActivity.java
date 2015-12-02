package com.neu.coder.mathmodeling;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

//    private TabBarView tabBarAnimView;
    private RadioGroup main_radiogroup;
    private TabHost tabHost;
    private RadioButton tab_icon_weixin, tab_icon_address, tab_icon_friend,tab_icon_setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_radiogroup = (RadioGroup) findViewById(R.id.main_radiogroup);

        tab_icon_weixin = (RadioButton) findViewById(R.id.tab_icon_weixin);
        tab_icon_address = (RadioButton) findViewById(R.id.tab_icon_address);
        tab_icon_friend = (RadioButton) findViewById(R.id.tab_icon_friend);
        tab_icon_setting = (RadioButton) findViewById(R.id.tab_icon_setting);

        //往TabWidget添加Tab
//        tabhost = getTabHost();
        tabHost = (TabHost)findViewById(R.id.main_tabhost);
        checkListener checkradio = new checkListener();
        main_radiogroup.setOnCheckedChangeListener(checkradio);
        tabHost.addTab(tabHost.newTabSpec("tag1").setContent(new Intent(this,MOOCActivity.class)).setIndicator("tab1"));
//        tabhost.addTab(tabhost.newTabSpec("tag2").setIndicator("1").setContent(new Intent(this,Activity.class)));
//        tabhost.addTab(tabhost.newTabSpec("tag3").setIndicator("2").setContent(new Intent(this,Activity.class)));
//        tabhost.addTab(tabhost.newTabSpec("tag4").setIndicator("3").setContent(new Intent(this,Activity.class)));
    }

    public class checkListener implements OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            // TODO Auto-generated method stub
            //setCurrentTab 通过标签索引设置当前显示的内容
            //setCurrentTabByTag 通过标签名设置当前显示的内容
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
