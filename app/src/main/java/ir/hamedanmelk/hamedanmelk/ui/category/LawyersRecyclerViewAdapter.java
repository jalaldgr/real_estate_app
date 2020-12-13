package ir.hamedanmelk.hamedanmelk.ui.category;

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
import ir.hamedanmelk.hamedanmelk.models.LawyerModel;
import ir.hamedanmelk.hamedanmelk.tools.DownloadImage;
import ir.hamedanmelk.hamedanmelk.tools.MYSQlDBHelper;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

/**
 * {@link RecyclerView.Adapter} that can display a {@link LawyerModel}.
 *
 */
public class LawyersRecyclerViewAdapter extends RecyclerView.Adapter<LawyersRecyclerViewAdapter.ViewHolder> {

    private final List<LawyerModel> lawyerModels;
    Activity activity;
    private static final String TAG = "LawyersRecycler";
    MYSQlDBHelper dbHelper;
    public LawyersRecyclerViewAdapter(List<LawyerModel> items, Activity act) {

        lawyerModels = items;
        activity = act;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_lawyer_item_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        final LawyerModel lawyerModel = lawyerModels.get(position);
        holder.titleTxt.setText(lawyerModel.getFullName());
        holder.addressTxt.setText(lawyerModel.getDescription());
        holder.phoneTxt.setText(lawyerModel.getPhone());
        new DownloadImage(holder.thumbnailImg).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,Urls.getBaseURL()+"/"+lawyerModel.getImage());
        holder.phoneTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "tel:" + lawyerModel.getPhone() ;
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return lawyerModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView titleTxt;
        public final TextView addressTxt;
        public final TextView phoneTxt;
        public final ImageView thumbnailImg;

        public ViewHolder(View view) {
            super(view);
            titleTxt = (TextView) view.findViewById(R.id.LawyerItemAdapterTitleTxt);
            addressTxt = (TextView)view.findViewById(R.id.LawyerItemAdapterAddressTxt);
            phoneTxt = (TextView)view.findViewById(R.id.LawyerItemAdapterPhoneTxt);
            thumbnailImg = (ImageView)view.findViewById(R.id.LawyerItemAdapterThumbnailImg);
        }
    }
}