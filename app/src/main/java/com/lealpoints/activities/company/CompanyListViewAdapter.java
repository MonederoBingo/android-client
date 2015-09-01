package com.lealpoints.activities.company;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.lealpoints.activities.company_promotion.CompanyPromotionActivity;
import com.lealpoints.app.AppController;
import com.lealpoints.common.Constants;
import com.lealpoints.model.Company;
import com.lealpoints.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import lealpoints.com.frontend_android.BuildConfig;
import lealpoints.com.frontend_android.R;

public class CompanyListViewAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {
    private final List<Company> companies = new ArrayList<>();
    private final LayoutInflater inflater;
    private final CompanyActivity companyActivity;


    public CompanyListViewAdapter(CompanyActivity companyActivity) {
        this.companyActivity = companyActivity;
        inflater = companyActivity.getLayoutInflater();
    }

    public List<Company> getCompanies() {
        return companies;
    }

    @Override
    public int getCount() {
        return companies.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return companies.get(position).getCompanyId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.activity_company_list_view_item, parent, false);
            initializeListItem(convertView, viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        setImageName(viewHolder, position);
        setImageOnView(viewHolder, position);

        return convertView;
    }


    private void initializeListItem(View convertView, ViewHolder viewHolder) {
        viewHolder.itemThumbnail = (NetworkImageView) convertView.findViewById(R.id.niv_company_list_image);
        viewHolder.itemTitle = (TextView) convertView.findViewById(R.id.item_title);
        viewHolder.itemDescription = (TextView) convertView.findViewById(R.id.item_description);
        convertView.setTag(viewHolder);
    }

    private void setImageName(ViewHolder viewHolder, int position) {
        Company company = companies.get(position);
        viewHolder.itemTitle.setText(company.getName());
        viewHolder.itemDescription.setText(StringUtil.removeDecimals(company.getPoints()) + " "
                + companyActivity.getResources().getString(R.string.points));
    }

    private void setImageOnView(final ViewHolder viewHolder, int position) {
        String url = BuildConfig.API_URL + Constants.IMAGE_URL + companies.get(position).getCompanyId();
        viewHolder.itemThumbnail.setImageUrl(url, AppController.getInstance().getImageLoader());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(companyActivity, CompanyPromotionActivity.class);
        intent.putExtra(CompanyActivity.COMPANY_ID, String.valueOf(id));
        intent.putExtra(CompanyActivity.COMPANY_NAME, companies.get(position).getName());
        intent.putExtra(CompanyActivity.COMPANY_LOGO, companies.get(position).getUrlImageLogo());
        companyActivity.startActivity(intent);
    }

    private class ViewHolder {
        NetworkImageView itemThumbnail;
        TextView itemTitle;
        TextView itemDescription;
    }
}
