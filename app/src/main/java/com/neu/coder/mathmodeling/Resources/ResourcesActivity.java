package com.neu.coder.mathmodeling.Resources;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;

import com.felipecsl.asymmetricgridview.library.Utils;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridView;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridViewAdapter;
import com.neu.coder.mathmodeling.R;

import java.util.ArrayList;
import java.util.List;

public class ResourcesActivity extends AppCompatActivity {
    private AsymmetricGridView gridView;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);
        gridView = (AsymmetricGridView) findViewById(R.id.ResourceGridView);

        // Choose your own preferred column width
        gridView.setRequestedColumnWidth(Utils.dpToPx(this, 120));
        ResourceItem item = new ResourceItem(1, 1, 0);
        final List<ResourceItem> items = new ArrayList<ResourceItem>();
        items.add(item);

        // initialize your items array
        adapter = new DefaultListAdapter(this, items);
        AsymmetricGridViewAdapter asymmetricAdapter =
                new AsymmetricGridViewAdapter<>(this, gridView, adapter);
        gridView.setAdapter(asymmetricAdapter);
    }
}
