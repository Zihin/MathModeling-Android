package com.neu.coder.mathmodeling.Resources;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.neu.coder.mathmodeling.R;

import java.util.List;

/**
 * Created by zxy on 16/1/6.
 */
public class DefaultListAdapter extends ArrayAdapter<ResourceItem> implements ListAdapter {
    private final LayoutInflater layoutInflater;

    public DefaultListAdapter(Context context, List<ResourceItem> items) {
        super(context, 0, items);
        layoutInflater = LayoutInflater.from(context);
    }

    public DefaultListAdapter(Context context) {
        super(context, 0);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;

        ResourceItem item = getItem(position);
        boolean isRegular = getItemViewType(position) == 0;

        if (convertView == null) {
            v = layoutInflater.inflate(
                    isRegular ? R.layout.activity_resources_adapter_item : R.layout.activity_resources_adapter_item_odd, parent, false);
        } else {
            v = convertView;
        }

        TextView textView;
        if (isRegular) {
            textView = (TextView) v.findViewById(R.id.textview);
        } else {
            textView = (TextView) v.findViewById(R.id.textview_odd);
        }

        textView.setText(String.valueOf(item.getPosition()));

        return v;
    }

    @Override public int getViewTypeCount() {
        return 2;
    }

    @Override public int getItemViewType(int position) {
        return position % 2 == 0 ? 1 : 0;
    }

    public void appendItems(List<ResourceItem> newItems) {
        addAll(newItems);
        notifyDataSetChanged();
    }

    public void setItems(List<ResourceItem> moreItems) {
        clear();
        appendItems(moreItems);
    }
}
