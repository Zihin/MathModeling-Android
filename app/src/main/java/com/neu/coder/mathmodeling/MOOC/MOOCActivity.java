package com.neu.coder.mathmodeling.MOOC;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bigkoo.convenientbanner.CBViewHolderCreator;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.ConvenientBanner.Transformer;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.neu.coder.mathmodeling.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MOOCActivity extends AppCompatActivity implements MOOCAdapter.MoocItemClickListener {
    private String listRequestURL = "http://1.footballapp.sinaapp.com/mooclist.php?nextPage=";
    private String[] images = {
            "http://7xpjee.com1.z0.glb.clouddn.com/0logo.jpg",
            "http://7xpjee.com1.z0.glb.clouddn.com/01.jpg",
            "http://7xpjee.com1.z0.glb.clouddn.com/02.jpg",
            "http://7xpjee.com1.z0.glb.clouddn.com/03.jpg",
            "http://7xpjee.com1.z0.glb.clouddn.com/04.jpg",
            "http://7xpjee.com1.z0.glb.clouddn.com/05.jpg",
            "http://7xpjee.com1.z0.glb.clouddn.com/06.jpg"
    };
    private List<String> networkImages;

    private XRecyclerView mRecyclerView;
    private MOOCAdapter moocAdapter;
    private ArrayList<MoocItemData> datas;
    private int times = 0;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mooc);

        mQueue = Volley.newRequestQueue(this);
        setUpScrollBar();
        setUpRecyclerView();
        setUpAdapter();

        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                times = 0;
                datas.clear();
                volleyGetHttp();
            }

            @Override
            public void onLoadMore() {
                times ++;
                volleyGetHttp();
            }
        });

        volleyGetHttp();
    }

    private void volleyGetHttp() {
        String requestURL = listRequestURL + times;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(requestURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject arg0) {
                        Iterator<String> it = arg0.keys();
                        int itemNumber = 0;
                        JSONArray maps;
                        try {
                            itemNumber = arg0.getInt(it.next());
                            maps = arg0.getJSONArray(it.next());
                            for (int i = 0; i < maps.length(); ++i) {
                                JSONObject obj = (JSONObject) maps.get(i);
                                MoocItemData data = new MoocItemData(obj);
                                datas.add(data);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        moocAdapter.notifyDataSetChanged();
                        mRecyclerView.loadMoreComplete();
                        if (itemNumber > 0) {
                            mRecyclerView.refreshComplete();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError arg0) {
            }
        });

        mQueue.add(jsonObjectRequest);
    }

    private void setUpRecyclerView() {
        mRecyclerView = (XRecyclerView)this.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLaodingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
    }

    private void setUpAdapter() {
        datas = new ArrayList<MoocItemData>();
        moocAdapter = new MOOCAdapter(datas);
        mRecyclerView.setAdapter(moocAdapter);
        moocAdapter.setOnItemClickListener(this);
    }

    private void setUpScrollBar() {
        ConvenientBanner convenientBanner = (ConvenientBanner)findViewById(R.id.convenientBanner);

        networkImages= Arrays.asList(images);
        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        },networkImages).setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                //设置翻页的效果，不需要翻页效果可用不设
                .setPageTransformer(Transformer.DefaultTransformer);
    }

    @Override
    public void onItemClick(View view, int postion) {
        MoocItemData data = datas.get(postion - 1);
        String url = data.getUrl();

        Intent intent = new Intent();
        intent.setClass(this, MoocWebView.class);
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
