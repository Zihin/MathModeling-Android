package com.neu.coder.mathmodeling.Resources;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.neu.coder.mathmodeling.R;

public class ResourcesActivity extends AppCompatActivity implements View.OnClickListener  {
    private TextView usaReferenceView;
    private TextView domesticReferenceView;
    private TextView matlabReferenceView;
    private TextView matlabAlgorithmView;
    private TextView teachingVedioView;
    private TextView resourceSharingView;
    private TextView questionsView;
    private TextView offLineResourceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        setupTextViews();

    }

    private void setupTextViews() {
        usaReferenceView = (TextView) findViewById(R.id.usaReference);
        usaReferenceView.setOnClickListener(this);
        domesticReferenceView = (TextView) findViewById(R.id.domesticReference);
        domesticReferenceView.setOnClickListener(this);
        matlabReferenceView = (TextView) findViewById(R.id.matlabReference);
        matlabReferenceView.setOnClickListener(this);
        matlabAlgorithmView = (TextView) findViewById(R.id.matlabAlgorithm);
        matlabAlgorithmView.setOnClickListener(this);
        teachingVedioView = (TextView) findViewById(R.id.teachingVedio);
        teachingVedioView.setOnClickListener(this);
        resourceSharingView = (TextView) findViewById(R.id.resourceSharing);
        resourceSharingView.setOnClickListener(this);
        questionsView = (TextView) findViewById(R.id.questions);
        questionsView.setOnClickListener(this);
        offLineResourceView = (TextView) findViewById(R.id.offLineResource);
        offLineResourceView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String text = (String) ((TextView)v).getText();
        Log.i("Resouce", "onItemClick: id = " + id);

        Intent intent = new Intent();
        intent.setClass(this, ResouceDetailViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("text", text);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
