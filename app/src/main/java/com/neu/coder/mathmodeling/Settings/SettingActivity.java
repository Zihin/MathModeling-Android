package com.neu.coder.mathmodeling.Settings;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.neu.coder.mathmodeling.R;

public class SettingActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] COUNTRIES=new String[]{getString(R.string.list_signin),getString(R.string.list_login),getString(R.string.list_setting)};

        setContentView(R.layout.activity_setting);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, COUNTRIES));

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        switch (position){
            case 0:
                startActivity(new Intent(this,SignInAty.class));
                break;
            case 1:
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, SettingsGuideAty.class));
                break;
            case 3:
//                getFragmentManager().beginTransaction().replace(R.id.menuList, new BlankFragment()).commit();
                break;
        }
    }
}
