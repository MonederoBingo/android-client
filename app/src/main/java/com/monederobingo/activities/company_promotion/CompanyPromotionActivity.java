package com.monederobingo.activities.company_promotion;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.NetworkImageView;
import com.monederobingo.activities.company.CompanyActivity;
import com.monederobingo.app.AppController;
import com.monederobingo.client.BuildConfig;
import com.monederobingo.client.R;
import com.monederobingo.common.ActivityUtil;
import com.monederobingo.common.Constants;
import com.monederobingo.rest.RestClient;
import com.monederobingo.rest.RestClientImpl;

public class CompanyPromotionActivity extends Activity {

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
        String url = BuildConfig.API_URL + Constants.IMAGE_URL + getIntent().getStringExtra(CompanyActivity.COMPANY_LOGO);
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
