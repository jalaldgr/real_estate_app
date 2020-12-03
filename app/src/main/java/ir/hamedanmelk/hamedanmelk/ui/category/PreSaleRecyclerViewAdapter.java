package ir.hamedanmelk.hamedanmelk.ui.category;
import android.app.Activity;
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
import ir.hamedanmelk.hamedanmelk.models.PreSaleModel;
import ir.hamedanmelk.hamedanmelk.tools.DownloadImage;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PreSaleModel}.
 *
 */
public class PreSaleRecyclerViewAdapter extends RecyclerView.Adapter<PreSaleRecyclerViewAdapter.ViewHolder> {

    private final List<PreSaleModel> preSaleModels;
    Activity act;
    private static final String TAG = "PreSaleFragment";
    public PreSaleRecyclerViewAdapter(List<PreSaleModel> items, Activity activity) {
        act=activity;
        preSaleModels= items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_presale_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        PreSaleModel preSaleModel = preSaleModels.get(position);
        holder.titleTxt.setText(preSaleModel.getTitle());
        holder.totalSaleTxt.setText(preSaleModel.getSaleTotalPrice());
//        holder.districtTxt.setText(preSaleModel.getDistrict());
        new DownloadImage(holder.thumbnailImg).execute(Urls.getBaseURL()+preSaleModel.getImages());
        Log.d(TAG, "onBindViewHolder: "+preSaleModel.getTitle() +"   >>>   "+ preSaleModel.getImages());

        holder.titleTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final NavController controller= Navigation.findNavController(Objects.requireNonNull(act),R.id.nav_host_fragment);
                Bundle args=new Bundle();
                args.putString("id",preSaleModels.get(position).getId());
                controller.navigate(R.id.singlePreSaleFragment,args);
                Log.d("hhh", "onClick from adapter: "+ Integer.toString(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return preSaleModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView titleTxt;
        public final TextView totalSaleTxt;
        public final TextView districtTxt;
        public final ImageView thumbnailImg;

        public ViewHolder(View view) {
            super(view);
            titleTxt = (TextView) view.findViewById(R.id.PreSaleFragmentTitleTxt);
            totalSaleTxt = (TextView) view.findViewById(R.id.PreSaleFragmentTotalSaleTxt);
            districtTxt = (TextView)view.findViewById(R.id.PreSaleFragmentDistrictTxt);
            thumbnailImg = (ImageView)view.findViewById(R.id.PreSaleFragmentThumbnailImg);
        }


    }
}