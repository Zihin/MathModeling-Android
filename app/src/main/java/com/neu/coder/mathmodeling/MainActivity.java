package com.neu.coder.mathmodeling;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;

import com.neu.coder.mathmodeling.Club.ClubActivity;
import com.neu.coder.mathmodeling.MOOC.MOOCActivity;
import com.neu.coder.mathmodeling.Quiz.QuizActivity;
import com.neu.coder.mathmodeling.Resources.ResourcesActivity;
import com.neu.coder.mathmodeling.Settings.SettingActivity;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class MainActivity extends Activity {

    private RadioGroup main_radiogroup;
    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initImageLoader();   //初始化网络图片缓存库
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
        tabHost.addTab(tabHost.newTabSpec("tag5").setIndicator("4").setContent(new Intent(this,SettingActivity.class)));
    }

    public class CheckListener implements OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch(checkedId){
                case R.id.tab_icon_mooc:
                    tabHost.setCurrentTab(0);
                    break;
                case R.id.tab_icon_club:
                    tabHost.setCurrentTab(1);
                    break;
                case R.id.tab_icon_quiz:
                    tabHost.setCurrentTab(2);
                    break;
                case R.id.tab_icon_resources:
                    tabHost.setCurrentTab(3);
                    break;
                case R.id.tab_icon_settings:
                    tabHost.setCurrentTab(4);
                    break;
            }
        }
    }

    //初始化网络图片缓存库
    private void initImageLoader(){
        //网络图片例子,结合常用的图片缓存库UIL,你可以根据自己需求自己换其他网络图片库
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().
                showImageForEmptyUri(R.drawable.mooc_bar_bg)
                .cacheInMemory(true).cacheOnDisk(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }
}
