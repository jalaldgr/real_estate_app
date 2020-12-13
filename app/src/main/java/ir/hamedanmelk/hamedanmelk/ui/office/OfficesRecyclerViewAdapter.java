package ir.hamedanmelk.hamedanmelk.ui.office;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.OfficeModel;
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
        final OfficeModel officeModel = officeModels.get(position);
        holder.titleTxt.setText(officeModel.getTitle());
        holder.managerTxt.setText(officeModel.getManager());
        holder.addressTxt.setText(officeModel.getAddress());
        holder.phoneTxt.setText(officeModel.getPhone());
        holder.faxTxt.setText(officeModel.getFax());
        new DownloadImage(holder.thumbnailImg).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,Urls.getBaseURL()+"/"+officeModel.getLogo());
        holder.phoneTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "tel:" + officeModel.getPhone() ;
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                activity.startActivity(intent);
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
        public final TextView faxTxt;
        public final ImageView thumbnailImg;

        public ViewHolder(View view) {
            super(view);
            titleTxt = (TextView) view.findViewById(R.id.OfficeItemAdapterTitleTxt);
            managerTxt = (TextView) view.findViewById(R.id.OfficeItemAdapterManagerTxt);
            addressTxt = (TextView)view.findViewById(R.id.OfficeItemAdapterAddressTxt);
            phoneTxt = (TextView)view.findViewById(R.id.OfficeItemAdapterPhoneTxt);
            faxTxt = (TextView)view.findViewById(R.id.OfficeItemAdapterFaxTxt) ;
            thumbnailImg = (ImageView)view.findViewById(R.id.OfficesItemAdapterThumbnailImg);
        }
    }
}