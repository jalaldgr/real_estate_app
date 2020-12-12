package ir.hamedanmelk.hamedanmelk.ui.category;

import android.app.Activity;
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
import ir.hamedanmelk.hamedanmelk.models.SaleModel;
import ir.hamedanmelk.hamedanmelk.models.micro.DistrictModel;
import ir.hamedanmelk.hamedanmelk.tools.DownloadImage;
import ir.hamedanmelk.hamedanmelk.tools.MYSQlDBHelper;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

/**
 * {@link RecyclerView.Adapter} that can display a {@link SaleModel}.
 *
 */
public class SaleRecyclerViewAdapter extends RecyclerView.Adapter<SaleRecyclerViewAdapter.ViewHolder> {
    private final List<SaleModel> SaleModels;
    Activity act;
    MYSQlDBHelper dbHelper;
    String TAG = "SaleRecyclerView";
    public SaleRecyclerViewAdapter(List<SaleModel> items, Activity activity) {
        SaleModels= items;act=activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_sale_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        SaleModel SaleModel = SaleModels.get(position);
        dbHelper = new MYSQlDBHelper(act.getApplicationContext());
        holder.titleTxt.setText(SaleModel.getTitle());
        holder.totalSaleTxt.setText(SaleModel.getSaleTotalPrice());
        DistrictModel districtModel = dbHelper.GetDistrictByID(SaleModel.getDistrict_id());
        holder.districtTxt.setText(districtModel.getTitle());
        new DownloadImage(holder.thumbnailImg).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,Urls.getBaseURL()+SaleModel.getImages());
        Log.d(TAG, "onBindViewHolder: "+SaleModel.getTitle() +"   >>>   "+ SaleModel.getImages());
        holder.totalSaleTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final NavController controller= Navigation.findNavController(Objects.requireNonNull(act),R.id.nav_host_fragment);
                Bundle args=new Bundle();
                args.putString("id",SaleModels.get(position).getId());
                controller.navigate(R.id.singleSaleFragment,args);
                Log.d("hhh", "onClick from adapter: "+ Integer.toString(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return SaleModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView titleTxt;
        public final TextView totalSaleTxt;
        public final TextView districtTxt;
        public final ImageView thumbnailImg;

        public ViewHolder(View view) {
            super(view);
            titleTxt = (TextView) view.findViewById(R.id.SaleFragmentTitleTxt);
            totalSaleTxt = (TextView) view.findViewById(R.id.SaleFragmentTotalSaleTxt);
            thumbnailImg = (ImageView)view.findViewById(R.id.SaleFragmentThumbnailImg);
            districtTxt  = (TextView)view.findViewById(R.id.SaleFragmentDistrictTxt);

        }

    }
}