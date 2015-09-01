package com.lealpoints.activities.company_promotion;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lealpoints.model.CompanyPromotion;
import com.lealpoints.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import lealpoints.com.frontend_android.R;

public class CompanyPromotionListViewAdapter extends BaseAdapter {
    private final List<CompanyPromotion> promotions = new ArrayList<>();
    private final LayoutInflater inflater;
    private final CompanyPromotionActivity companyPromotionActivity;


    public CompanyPromotionListViewAdapter(CompanyPromotionActivity companyPromotionActivity) {
        this.companyPromotionActivity = companyPromotionActivity;
        inflater = companyPromotionActivity.getLayoutInflater();
    }

    public List<CompanyPromotion> getPromotions() {
        return promotions;
    }

    @Override
    public int getCount() {
        return promotions.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return promotions.get(position).getCompanyId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.activity_company_promotion_list_view_item, parent, false);
            initializeListItem(convertView, viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        setImageName(viewHolder, position);
        return convertView;
    }


    private void initializeListItem(View convertView, ViewHolder viewHolder) {
        viewHolder.itemDescription = (TextView) convertView.findViewById(R.id.company_promotion_description);
        viewHolder.itemRequiredPoints = (TextView) convertView.findViewById(R.id.company_promotion_required_points);
        convertView.setTag(viewHolder);
    }

    private void setImageName(ViewHolder viewHolder, int position) {
        CompanyPromotion companyPromotion = promotions.get(position);
        viewHolder.itemDescription.setText(StringUtil.toUTF8String(companyPromotion.getDescription()));
        viewHolder.itemRequiredPoints.setText(StringUtil.removeDecimals(companyPromotion.getRequiredPoints()) + " "
                + companyPromotionActivity.getResources().getString(R.string.required_points));
    }

    private class ViewHolder {
        TextView itemDescription;
        TextView itemRequiredPoints;
    }
}
