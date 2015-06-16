package com.neerpoints.activities.company_promotion;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.NetworkImageView;
import com.neerpoints.activities.company.CompanyActivity;
import com.neerpoints.app.AppController;
import com.neerpoints.common.Constants;
import com.neerpoints.rest.RestClient;
import com.neerpoints.rest.RestClientImpl;

import neerpoints.com.frontend_android.R;


public class CompanyPromotionActivity extends ActionBarActivity {

    ProgressBar progressBar;
    private ListView mainListView;
    private RestClient restClientImpl = RestClientImpl.getInstance();
    private CompanyPromotionListViewAdapter listViewAdapter;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_promotion_list);
        initializeViews();
        initializeAdapters();
        getPromotionsFromApi();
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public LinearLayout getLinearLayout() {
        return linearLayout;
    }

    private void initializeViews() {
        mainListView = (ListView) findViewById(R.id.activity_company_promotion_list);
        progressBar = (ProgressBar) findViewById(R.id.pb_company_promotion_list);
        linearLayout = (LinearLayout) findViewById(R.id.promotion_list_layout);
        TextView companyName = (TextView) findViewById(R.id.promotion_list_company_name);
        companyName.setText(getIntent().getStringExtra(CompanyActivity.COMPANY_NAME));

        NetworkImageView logo = (NetworkImageView) findViewById(R.id.niv_company_promotion_list_image);
        String url = Constants.IMAGE_URL + getIntent().getStringExtra(CompanyActivity.COMPANY_LOGO);
        logo.setImageUrl(url, AppController.getInstance().getImageLoader());
    }

    private void initializeAdapters() {
        listViewAdapter = new CompanyPromotionListViewAdapter(this);
        mainListView.setAdapter(listViewAdapter);
    }

    private void getPromotionsFromApi() {
        String companyId = getIntent().getStringExtra(CompanyActivity.COMPANY_ID);
        CompanyPromotionApiAdapter companyPromotionApiAdapter = new CompanyPromotionApiAdapter(this, listViewAdapter);
        restClientImpl.callApi(Request.Method.GET, "promotion_configuration/" + companyId, null, companyPromotionApiAdapter, this);
    }

    @Override
    protected void onStop() {
        restClientImpl.stopService(this);
        super.onStop();
    }
}
