package com.neu.coder.mathmodeling.Club;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.neu.coder.mathmodeling.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Zihin on 1/10/2016.
 */
public class FragmentContent extends Fragment {

    String[] introData;
    private List<News> newsList = new ArrayList<News>();
    private List<News> activityList = new ArrayList<News>();
    private List<News> introList = new ArrayList<News>();


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
                ListView newsListView = (ListView) view.findViewById(R.id.list_view);

                initNews();
//                if (newsData == null) {
//                    newsData = new String[newsList.size()];
//                    for (int i = 0; i < newsList.size(); i++) {
//                        newsData[i] = newsList.get(i).getTitle();
//                    }
//                }
                NewsAdapter newsAdapter = new NewsAdapter(this.getActivity(), R.layout.news_item, newsList);
//                ArrayAdapter<String> newsAdapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1, newsData);
                newsListView.setAdapter(newsAdapter);
                newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getActivity(), WebViewActivity.class);
                        intent.putExtra("web_type", newsList.get(i).getBody());
                        startActivity(intent);
                    }
                });
                break;
            case "活动":
                ListView activityListView = (ListView) view.findViewById(R.id.list_view);

                initActivity();
                NewsAdapter activityAdapter = new NewsAdapter(this.getActivity(), R.layout.news_item, activityList);
                activityListView.setAdapter(activityAdapter);
                activityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getActivity(), WebViewActivity.class);
                        intent.putExtra("web_type", activityList.get(i).getBody());
                        startActivity(intent);
                    }
                });
                break;
            case "简介":
                ListView introListView = (ListView) view.findViewById(R.id.list_view);

                initIntro();
                NewsAdapter introAdapter = new NewsAdapter(this.getActivity(), R.layout.news_item, introList);
                introListView.setAdapter(introAdapter);
                introListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getActivity(), WebViewActivity.class);
                        intent.putExtra("web_type", introList.get(i).getBody());
                        startActivity(intent);
                    }
                });
                break;
            case "论坛":
                tv.setText("正在开发中……");
                break;
            default:
                tv.setText(getArguments().getString("title"));
        }
    }

    private void initNews() {
        News news1 = new News("组队信息发布2", "http://mp.weixin.qq.com/s?__biz=MzA5MzcxOTkxMA==&mid=402680830&idx=1&sn=a85223bad35b339224f72ca7c1efc62e&3rd=MzA3MDU4NTYzMw==&scene=6#rd");
        newsList.add(news1);
        News news2 = new News("组队信息发布1", "http://mp.weixin.qq.com/s?__biz=MzA5MzcxOTkxMA==&mid=402653466&idx=2&sn=8d25acb5399bcc0ff140c65830b4ee1a&3rd=MzA3MDU4NTYzMw==&scene=6#rd");
        newsList.add(news2);
        News news3 = new News("南湖分社第一次全体部员会议圆满结束", "http://mp.weixin.qq.com/s?__biz=MzA5MzcxOTkxMA==&mid=400101872&idx=2&sn=0e97e7efd5ce507baa91434a6925411a&3rd=MzA3MDU4NTYzMw==&scene=6#rd");
        newsList.add(news3);
        News news4 = new News("浑南数学建模大讲堂成功开课", "http://mp.weixin.qq.com/s?__biz=MzA5MzcxOTkxMA==&mid=400101872&idx=1&sn=b8dd09a56883440807fc644cd04f8355&3rd=MzA3MDU4NTYzMw==&scene=6#rd");
        newsList.add(news4);
        News news5 = new News("东大数模浑南分社全部招新圆满成功", "http://mp.weixin.qq.com/s?__biz=MzA5MzcxOTkxMA==&mid=400016130&idx=1&sn=3c71c3c5c4f457f83efe36e0a5cb2d93&3rd=MzA3MDU4NTYzMw==&scene=6#rd");
        newsList.add(news5);
        News news6 = new News("数学建模大讲堂第一期圆满结束啦", "http://mp.weixin.qq.com/s?__biz=MzA5MzcxOTkxMA==&mid=400005613&idx=1&sn=6289fcf34d77bf005d98a0a35b083741&3rd=MzA3MDU4NTYzMw==&scene=6#rd");
        newsList.add(news6);
        News news7 = new News("浑南主社招新宣讲大会圆满举行!", "http://mp.weixin.qq.com/s?__biz=MzA5MzcxOTkxMA==&mid=209202100&idx=1&sn=8ccfbb0c2d29aa85dd339b0ab43ebf14&3rd=MzA3MDU4NTYzMw==&scene=6#rd");
        newsList.add(news7);
        News news8 = new News("招新进行时！", "http://mp.weixin.qq.com/s?__biz=MzA5MzcxOTkxMA==&mid=208979896&idx=2&sn=54c107cae26a9014c496bf186a379110&3rd=MzA3MDU4NTYzMw==&scene=6#rd");
        newsList.add(news8);
        News news9 = new News("我校学生喜获全国大学生数学建模竞赛辽宁赛区竞赛一等奖20项", "http://mp.weixin.qq.com/s?__biz=MzA5MzcxOTkxMA==&mid=400132461&idx=3&sn=692d510c0d747c5e7a5cbf497f010296&3rd=MzA3MDU4NTYzMw==&scene=6#wechat_redirect");
        newsList.add(news9);
        News news10 = new News("数学建模大讲堂第一期开讲咯", "http://mp.weixin.qq.com/s?__biz=MzA5MzcxOTkxMA==&mid=209278373&idx=1&sn=e8c7b9dc0b3347e2d6452887e7a0da65&3rd=MzA3MDU4NTYzMw==&scene=6#rd");
        newsList.add(news10);
        News news11 = new News("夏天怎么防蚊子最有效？", "http://mp.weixin.qq.com/s?__biz=MzA5MzcxOTkxMA==&mid=207625446&idx=1&sn=6296bf73878f593dfa8ebaa94a79a896&3rd=MzA3MDU4NTYzMw==&scene=6#rd");
        newsList.add(news11);
        News news12 = new News("只要时间久，情人眼里就能出西施", "http://mp.weixin.qq.com/s?__biz=MzA5MzcxOTkxMA==&mid=207625446&idx=2&sn=c71a801100e3d0983dff4459f3f84927&3rd=MzA3MDU4NTYzMw==&scene=6#rd");
        newsList.add(news12);
    }

    private void initActivity() {
        News activity1 = new News("智力挑战赛震撼来袭", "http://mp.weixin.qq.com/s?__biz=MzA5MzcxOTkxMA==&mid=400303564&idx=1&sn=b1b7d92ef2dfc2903fc2da3bee27b42a&3rd=MzA3MDU4NTYzMw==&scene=6#rd");
        activityList.add(activity1);
        News activity2 = new News("“建龙钢铁”社团文化节—数独大赛华丽空降", "http://mp.weixin.qq.com/s?__biz=MzA5MzcxOTkxMA==&mid=400132461&idx=1&sn=202adcdcbe7377334e3b0ad442569e85&3rd=MzA3MDU4NTYzMw==&scene=6#wechat_redirect");
        activityList.add(activity2);
        News activity3 = new News("南湖招新宣讲会，我们不见不散！", "http://mp.weixin.qq.com/s?__biz=MzA5MzcxOTkxMA==&mid=208979896&idx=1&sn=3fbb40e68e23a7d688aab2902412e66a&3rd=MzA3MDU4NTYzMw==&scene=6#rd");
        activityList.add(activity3);
        News activity4 = new News("美赛史无前例，重大变革！ 2016美赛参赛指南", "http://mp.weixin.qq.com/s?__biz=MzA5MzcxOTkxMA==&mid=400132461&idx=2&sn=891c300467d6176f31ae0ea5d47b6f7e&3rd=MzA3MDU4NTYzMw==&scene=6#wechat_redirect");
        activityList.add(activity4);
        News activity5 = new News("2015年全国大学生数学建模竞赛组队通知", "http://mp.weixin.qq.com/s?__biz=MzA5MzcxOTkxMA==&mid=207625446&idx=3&sn=b764b38e8ab8e7883e59f69146f27b16&3rd=MzA3MDU4NTYzMw==&scene=6#rd");
        activityList.add(activity5);
        News activity6 = new News("记数学建模蜕变经历杂谈", "http://mp.weixin.qq.com/s?__biz=MzA5MzcxOTkxMA==&mid=206478814&idx=1&sn=eff097c9921edc56223d95496a52f9f9&3rd=MzA3MDU4NTYzMw==&scene=6#rd");
        activityList.add(activity6);
        News activity7 = new News("能否用一句话描述下究竟什么叫“最优化” 问题？", "http://mp.weixin.qq.com/s?__biz=MzA5MzcxOTkxMA==&mid=206247561&idx=2&sn=157777242ef1cdb4490a76586ad4653b&3rd=MzA3MDU4NTYzMw==&scene=6#rd");
        activityList.add(activity7);
        News activity8 = new News("2015校选赛试题及小编贴士", "http://mp.weixin.qq.com/s?__biz=MzA5MzcxOTkxMA==&mid=206047430&idx=2&sn=c9f4a9f7674c431bfe3a9837ebff770c&3rd=MzA3MDU4NTYzMw==&scene=6#rd");
        activityList.add(activity8);
        News activity9 = new News("想过校选的看过来 周六讲座来袭！", "http://mp.weixin.qq.com/s?__biz=MzA5MzcxOTkxMA==&mid=204996202&idx=3&sn=9843196146e028f7604257ef145ab76f&3rd=MzA3MDU4NTYzMw==&scene=6#rd");
        activityList.add(activity9);
        News activity10 = new News("数模大讲堂第四期！", "http://mp.weixin.qq.com/s?__biz=MzA5MzcxOTkxMA==&mid=204303136&idx=1&sn=7419e760f7121f22cefe5e96106edf9f&3rd=MzA3MDU4NTYzMw==&scene=6#rd");
        activityList.add(activity10);
        News activity11 = new News("校选赛相关事宜", "http://mp.weixin.qq.com/s?__biz=MzA5MzcxOTkxMA==&mid=204303136&idx=2&sn=0687b50715201c00e77f1018beb19b37&3rd=MzA3MDU4NTYzMw==&scene=6#rd");
        activityList.add(activity11);
        News activity12 = new News("大成113，我们不见不散！", "http://mp.weixin.qq.com/s?__biz=MzA5MzcxOTkxMA==&mid=203797780&idx=2&sn=e4cdad88ef34ca767735b0d08de07211&3rd=MzA3MDU4NTYzMw==&scene=6#rd");
        activityList.add(activity12);
    }

    private void initIntro() {
        News intro1 = new News("协会介绍", "http://mp.weixin.qq.com/s?__biz=MzA5MzcxOTkxMA==&mid=402653466&idx=1&sn=edcfc948769d22bc737b539270bb1e0e&3rd=MzA3MDU4NTYzMw==&scene=6#rd");
        introList.add(intro1);
        News intro2 = new News("协会宗旨", "http://mp.weixin.qq.com/s?__biz=MzA5MzcxOTkxMA==&mid=204422071&idx=1&sn=683af9fef77257bcdf3b8b4c1954a090&scene=18&scene=23&srcid=0112wnv8XNpIfQaXV89bFZtW#rd");
        introList.add(intro2);
        News intro3 = new News("部门介绍", "http://mp.weixin.qq.com/s?__biz=MzA5MzcxOTkxMA==&mid=204422001&idx=1&sn=9798d7e57b3d2a70c998aee8523f833d&scene=18&scene=23&srcid=0112ML0XM1Lli3LPLam2TaCj#rd");
        introList.add(intro3);
        News intro4 = new News("协会发展", "http://mp.weixin.qq.com/s?__biz=MzA5MzcxOTkxMA==&mid=204422077&idx=1&sn=b90abb2bb4cbdf834c090b56caa1bd5c&scene=18&scene=23&srcid=0112XggABsCE9gbRdn4uZZUf#rd");
        introList.add(intro4);

    }


}