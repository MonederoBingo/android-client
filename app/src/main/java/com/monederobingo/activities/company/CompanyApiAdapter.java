package com.monederobingo.activities.company;

import android.view.View;
import android.widget.Toast;

import com.monederobingo.model.Company;
import com.monederobingo.model.ServiceResult;
import com.monederobingo.rest.ApiAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CompanyApiAdapter extends ApiAdapter {

    private final CompanyActivity companyActivity;
    private final CompanyListViewAdapter companyListViewAdapter;

    public CompanyApiAdapter(CompanyActivity companyActivity, CompanyListViewAdapter companyListViewAdapter) {
        super(companyActivity);
        this.companyActivity = companyActivity;
        this.companyListViewAdapter = companyListViewAdapter;
    }

    @Override
    public void onResponse(ServiceResult serviceResult, Map<String, String> requestParams) {
        try {
            if (serviceResult.isSuccess()) {
                companyListViewAdapter.getCompanies().clear();
                companyListViewAdapter.getCompanies().addAll(parseCompanies(serviceResult.getObject()));
                companyListViewAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            Toast.makeText(companyActivity, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void startLoading() {
        companyActivity.getProgressBar().setVisibility(View.VISIBLE);
        companyActivity.getMainListView().setVisibility(View.GONE);
    }

    @Override
    public void stopLoading() {
        companyActivity.getProgressBar().setVisibility(View.GONE);
        companyActivity.getMainListView().setVisibility(View.VISIBLE);
    }

    private Collection<? extends Company> parseCompanies(String object) throws JSONException {
        List<Company> companies = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(object);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int companyId = jsonObject.optInt("companyId");
            String name = jsonObject.optString("name");
            String urlImageLogo = jsonObject.optString("urlImageLogo");
            double points = jsonObject.optDouble("points");
            companies.add(new Company(companyId, name, urlImageLogo, points));
        }
        return companies;
    }

}
