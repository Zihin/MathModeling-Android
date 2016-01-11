package com.neu.coder.mathmodeling.Club;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.neu.coder.mathmodeling.R;


/**
 * Created by Zihin on 1/10/2016.
 */
public class FragmentContent extends Fragment {

    private String[] data = {"A", "U", "Y", "H", "G", "F", "D", "S", "V", "F", "D", "S", "V", "F", "D", "S", "V", "F", "D", "S", "V"};

    public static Fragment getInstance(Bundle bundle) {
        FragmentContent fragment = new FragmentContent();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        TextView tv = (TextView) view.findViewById(R.id.tv_id);
        switch (getArguments().getString("title")) {
            case "新闻":
                ListView listView = (ListView) view.findViewById(R.id.list_view);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, data);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String data="http://www.baidu.com";
                        Intent intent = new Intent(getActivity(), WebViewActivity.class);
                        intent.putExtra("web_type",data);
                        startActivity(intent);
                    }
                });
                break;
            case "活动":
                tv.setText("正在开发么么哒~");
                break;
            case "简介":
                tv.setText("正在开发么么哒~");
                break;
            case "论坛":
                tv.setText("正在开发么么哒~");
                break;
            default:
                tv.setText(getArguments().getString("title"));
        }
    }
}