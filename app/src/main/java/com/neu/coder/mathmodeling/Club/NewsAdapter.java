package com.neu.coder.mathmodeling.Club;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.neu.coder.mathmodeling.R;

import java.util.List;

/**
 * Created by Zihin on 1/12/2016.
 */
public class NewsAdapter extends ArrayAdapter<News> {

    private int resourceId;

    public NewsAdapter(Context context, int textViewResourceId, List<News> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        News news = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView newsTitle = (TextView) view.findViewById(R.id.news_title);
        newsTitle.setText(news.getTitle());
        return view;
    }
}
