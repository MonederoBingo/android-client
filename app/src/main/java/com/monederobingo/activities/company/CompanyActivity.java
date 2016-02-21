package com.monederobingo.activities.company;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.monederobingo.app.AppController;
import com.monederobingo.client.R;
import com.monederobingo.common.ActivityUtil;
import com.monederobingo.rest.RestClient;
import com.monederobingo.rest.RestClientImpl;

public class CompanyActivity extends ActionBarActivity {

    public static final String COMPANY_ID = "com.monederobingo.activities.company.companyId";
    public static final String COMPANY_NAME = "com.monederobingo.activities.company.companyName";
    public static final String COMPANY_LOGO = "com.monederobingo.activities.company.companyLogo";
    ProgressBar progressBar;
    private ListView mainListView;
    private CompanyListViewAdapter listViewAdapter;
    private RestClient restClientImpl = RestClientImpl.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_list);
        initializeViews();
        initializeAdapters();
        getCompaniesFromApi();
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public ListView getMainListView() {
        return mainListView;
    }

    private void initializeViews() {
        mainListView = (ListView) findViewById(R.id.activity_company_list);
        progressBar = (ProgressBar) findViewById(R.id.pb_company_list);
    }

    private void initializeAdapters() {
        listViewAdapter = new CompanyListViewAdapter(this);
        mainListView.setAdapter(listViewAdapter);
        mainListView.setOnItemClickListener(listViewAdapter);
        ActivityUtil.setActionBarTitle(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_company, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.company_refresh:
                getCompaniesFromApi();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getCompaniesFromApi() {
        String phone = AppController.getInstance().getPhoneFromPreferences();
        CompanyApiAdapter companyApiAdapter = new CompanyApiAdapter(this, listViewAdapter);
        restClientImpl.callApi(Request.Method.GET, "points_in_company/" + phone, null, companyApiAdapter, this);
    }

    @Override
    protected void onStop() {
        restClientImpl.stopService(this.getLocalClassName());
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        ActivityUtil.exit(this);
    }
}
