package ir.hamedanmelk.hamedanmelk.ui.company;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.CompanyModel;
import ir.hamedanmelk.hamedanmelk.models.PreSaleModel;
import ir.hamedanmelk.hamedanmelk.models.micro.CompanyTypeModel;
import ir.hamedanmelk.hamedanmelk.models.micro.DistrictModel;
import ir.hamedanmelk.hamedanmelk.tools.DownloadImage;
import ir.hamedanmelk.hamedanmelk.tools.MYSQlDBHelper;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PreSaleModel}.
 *
 */
public class CompaniesRecyclerViewAdapter extends RecyclerView.Adapter<CompaniesRecyclerViewAdapter.ViewHolder> {

    private final List<CompanyModel> companyModels;
    Activity activity;
    private static final String TAG = "PreSaleFragment";
    MYSQlDBHelper dbHelper;
    public CompaniesRecyclerViewAdapter(List<CompanyModel> items, Activity act) {

        companyModels = items;
        activity = act;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_company_item_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        final CompanyModel companyModel = companyModels.get(position);
        holder.titleTxt.setText(companyModel.getTitle());
        holder.managerTxt.setText(companyModel.getManager());
        holder.addressTxt.setText(companyModel.getAddress());
        holder.phoneTxt.setText(companyModel.getPhone());
        new DownloadImage(holder.thumbnailImg).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,Urls.getBaseURL()+"/"+companyModel.getLogo());
        holder.phoneTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "tel:" + companyModel.getPhone() ;
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return companyModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView titleTxt;
        public final TextView managerTxt;
        public final TextView addressTxt;
        public final TextView phoneTxt;
        public final ImageView thumbnailImg;

        public ViewHolder(View view) {
            super(view);
            titleTxt = (TextView) view.findViewById(R.id.CompanieItemAdapterTitleTxt);
            managerTxt = (TextView) view.findViewById(R.id.CompanieItemAdapterManagerTxt);
            addressTxt = (TextView)view.findViewById(R.id.CompanieItemAdapterAddressTxt);
            phoneTxt = (TextView)view.findViewById(R.id.CompanieItemAdapterPhoneTxt);
            thumbnailImg = (ImageView)view.findViewById(R.id.CompaniesItemAdapterThumbnailImg);
        }


    }
}