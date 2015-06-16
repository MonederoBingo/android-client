package com.neerpoints.activities.company_promotion;

import android.view.View;
import android.widget.Toast;

import com.neerpoints.model.CompanyPromotion;
import com.neerpoints.model.ServiceResult;
import com.neerpoints.rest.ApiAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CompanyPromotionApiAdapter extends ApiAdapter {

    private final CompanyPromotionActivity companyPromotionActivity;
    private final CompanyPromotionListViewAdapter companyPromotionListViewAdapter;

    public CompanyPromotionApiAdapter(CompanyPromotionActivity companyPromotionActivity, CompanyPromotionListViewAdapter companyPromotionListViewAdapter) {
        super(companyPromotionActivity);
        this.companyPromotionActivity = companyPromotionActivity;
        this.companyPromotionListViewAdapter = companyPromotionListViewAdapter;
    }

    @Override
    public void onResponse(ServiceResult serviceResult, Map<String, String> requestParams) {
        try {
            if (serviceResult.isSuccess()) {
                companyPromotionListViewAdapter.getPromotions().clear();
                companyPromotionListViewAdapter.getPromotions().addAll(parseCompanyPromotions(serviceResult.getObject()));
                companyPromotionListViewAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            Toast.makeText(companyPromotionActivity, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void startLoading() {
        companyPromotionActivity.getProgressBar().setVisibility(View.VISIBLE);
        companyPromotionActivity.getLinearLayout().setVisibility(View.GONE);
    }

    @Override
    public void stopLoading() {
        companyPromotionActivity.getProgressBar().setVisibility(View.GONE);
        companyPromotionActivity.getLinearLayout().setVisibility(View.VISIBLE);
    }

    private Collection<? extends CompanyPromotion> parseCompanyPromotions(String object) throws JSONException {
        List<CompanyPromotion> companyPromotions = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(object);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int promotionConfigurationId = jsonObject.optInt("promotionConfigurationId");
            int companyId = jsonObject.optInt("companyId");
            String description = jsonObject.optString("description");
            double requiredPoints = jsonObject.optDouble("requiredPoints");
            companyPromotions.add(new CompanyPromotion(promotionConfigurationId, companyId, description, requiredPoints));
        }
        return companyPromotions;
    }
}
