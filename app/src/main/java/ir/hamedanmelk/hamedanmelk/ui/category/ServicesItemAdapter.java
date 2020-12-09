package ir.hamedanmelk.hamedanmelk.ui.category;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.micro.CompanyTypeModel;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

public class ServicesItemAdapter extends BaseAdapter {
    private final ArrayList<CompanyTypeModel> companyTypeModels;
    private final Context context;
    private static final String TAG = "CompanyItemAdapter";

    public ServicesItemAdapter(ArrayList<CompanyTypeModel>items, Context c) {
        companyTypeModels = items;
        context = c;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        if (view == null) {
            view = ViewGroup.inflate(parent.getContext(), R.layout.fragment_company_item_adapter, null);
        }
        TextView  titleTxt= (TextView)view.findViewById(R.id.ServicesFragmentTitleTxt);
        titleTxt.setText(companyTypeModels.get(position).getTitle());


        return view;
    }

    @Override
    public int getCount() {
        return companyTypeModels.size();
    }

    @Override
    public Object getItem(int position) {
        return companyTypeModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}