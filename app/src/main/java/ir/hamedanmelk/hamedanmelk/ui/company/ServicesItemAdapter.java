package ir.hamedanmelk.hamedanmelk.ui.company;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.Objects;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.micro.CompanyTypeModel;

public class ServicesItemAdapter extends BaseAdapter {
    private final ArrayList<CompanyTypeModel> companyTypeModels;
    private final Context context;
    private final Activity activity;
    private static final String TAG = "CompanyItemAdapter";

    public ServicesItemAdapter(ArrayList<CompanyTypeModel>items, Context c,Activity act) {
        companyTypeModels = items;
        context = c;
        activity = act;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        if (view == null) {
            view = ViewGroup.inflate(parent.getContext(), R.layout.fragment_service_item_adapter, null);
        }
        TextView  titleTxt= (TextView)view.findViewById(R.id.CompaniesFragmentTitleTxt);
        titleTxt.setText(companyTypeModels.get(position).getTitle());
        titleTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final NavController controller= Navigation.findNavController(Objects.requireNonNull(activity),R.id.nav_host_fragment);
                Bundle args=new Bundle();
                args.putString("id",companyTypeModels.get(position).getId());
                controller.navigate(R.id.companyFragment,args);
            }
        });


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