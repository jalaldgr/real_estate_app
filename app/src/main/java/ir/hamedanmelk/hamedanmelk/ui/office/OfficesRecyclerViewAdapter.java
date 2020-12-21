package ir.hamedanmelk.hamedanmelk.ui.office;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
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
import ir.hamedanmelk.hamedanmelk.models.OfficeModel;
import ir.hamedanmelk.hamedanmelk.models.micro.CityModel;
import ir.hamedanmelk.hamedanmelk.models.micro.DistrictModel;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.DownloadImage;
import ir.hamedanmelk.hamedanmelk.tools.MYSQlDBHelper;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

/**
 * {@link RecyclerView.Adapter} that can display a {@link OfficeModel}.
 *
 */
public class OfficesRecyclerViewAdapter extends RecyclerView.Adapter<OfficesRecyclerViewAdapter.ViewHolder> {

    private final List<OfficeModel> officeModels;
    Activity activity;
    private static final String TAG = "OfficesRecycler";
    MYSQlDBHelper dbHelper;
    public OfficesRecyclerViewAdapter(List<OfficeModel> items, Activity act) {

        officeModels = items;
        activity = act;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_office_item_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        MYSQlDBHelper qlDBHelper = new MYSQlDBHelper(activity.getApplicationContext());
        final OfficeModel officeModel = officeModels.get(position);
        CityModel cityModel = qlDBHelper.GetCityByID(officeModel.getDistrict_id());
        holder.titleTxt.setText(officeModel.getTitle());
        holder.managerTxt.setText(officeModel.getManager());
        holder.addressTxt.setText(officeModel.getAddress());
        holder.phoneTxt.setText(officeModel.getPhone());
        holder.noTxt.setText(officeModel.getNo());
        holder.cityxt.setText(officeModel.getTitle());
        holder.faxTxt.setText(officeModel.getFax());

        if(!officeModel.getLogo().equals("null")) {
            new DownloadImage(holder.thumbnailImg).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Urls.getBaseURL() + "/" + officeModel.getLogo());
        }
        holder.phoneTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "tel:" + officeModel.getPhone() ;
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                activity.startActivity(intent);
            }
        });


        holder.shareTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "اشتراک گذاری");
                i.putExtra(Intent.EXTRA_TEXT, "https://hamedanmelk.ir/OfficeList");
                activity.startActivity(Intent.createChooser(i, "اشتراک گذاری"));
            }
        });

    }

    @Override
    public int getItemCount() {
        return officeModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView titleTxt;
        public final TextView managerTxt;
        public final TextView addressTxt;
        public final TextView phoneTxt;
        public final TextView noTxt;
        public final TextView cityxt;
        public final ImageView thumbnailImg;
        public final TextView shareTxt;
        public final TextView faxTxt;

        public ViewHolder(View view) {
            super(view);
            titleTxt = (TextView) view.findViewById(R.id.OfficeItemAdapterTitleTxt);
            managerTxt = (TextView) view.findViewById(R.id.OfficeItemAdapterManagerTxt);
            addressTxt = (TextView)view.findViewById(R.id.OfficeItemAdapterAddressTxt);
            phoneTxt = (TextView)view.findViewById(R.id.OfficeItemAdapterPhoneTxt);
            noTxt = (TextView)view.findViewById(R.id.OfficeItemAdapterNoTxt) ;
            cityxt =(TextView)view.findViewById(R.id.OfficeItemAdapterCityTxt);
            thumbnailImg = (ImageView)view.findViewById(R.id.LawyerItemAdapterThumbnailImg);
            shareTxt = (TextView)view.findViewById(R.id.OfficeItemAdapterShareTxt);
            faxTxt = (TextView)view.findViewById(R.id.OfficeItemAdapterFaxTxt);
        }
    }
}