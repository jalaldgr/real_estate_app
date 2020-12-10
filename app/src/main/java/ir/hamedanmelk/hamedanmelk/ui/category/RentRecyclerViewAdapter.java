package ir.hamedanmelk.hamedanmelk.ui.category;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.RentModel;
import ir.hamedanmelk.hamedanmelk.models.micro.DistrictModel;
import ir.hamedanmelk.hamedanmelk.tools.DownloadImage;
import ir.hamedanmelk.hamedanmelk.tools.MYSQlDBHelper;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

import java.util.List;
import java.util.Objects;

/**
 * {@link RecyclerView.Adapter} that can display a {@link RentModel}.
 *
 */
public class RentRecyclerViewAdapter extends RecyclerView.Adapter<RentRecyclerViewAdapter.ViewHolder> {

    private final List<RentModel> rentModels;
    Activity act;
    MYSQlDBHelper dbHelper;
    public RentRecyclerViewAdapter(List<RentModel> items,Activity activity) {
        rentModels= items;
        act=activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_rent_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        dbHelper = new MYSQlDBHelper(act.getApplicationContext());
        RentModel rentModel = rentModels.get(position);
        holder.titleTxt.setText(rentModel.getTitle());
        holder.totalMotgagePriceTxt.setText(rentModel.getMortgageTotalPrice());
        holder.totalRentPriceTxt.setText(rentModel.getRentTotalPrice());
        DistrictModel districtModel = dbHelper.GetDistrictByID(rentModel.getDistrict_id());
        holder.districtTxt.setText(districtModel.getTitle());
        new DownloadImage(holder.thumbnailImg).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,Urls.getBaseURL()+rentModel.getImages());
        holder.titleTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final NavController controller= Navigation.findNavController(Objects.requireNonNull(act),R.id.nav_host_fragment);
                Bundle args=new Bundle();
                args.putString("id",rentModels.get(position).getId());
                controller.navigate(R.id.singleRentFragment,args);
                Log.d("hhh", "onClick from adapter: "+ Integer.toString(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return rentModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView titleTxt;
        public final TextView totalMotgagePriceTxt;
        public final TextView totalRentPriceTxt;
        public final TextView districtTxt;
        public final ImageView thumbnailImg;

        public ViewHolder(View view) {
            super(view);
            titleTxt = (TextView) view.findViewById(R.id.CompanieItemAdapterTitleTxt);
            totalMotgagePriceTxt = (TextView) view.findViewById(R.id.CompanieItemAdapterManagerTxt);
            thumbnailImg = (ImageView)view.findViewById(R.id.RentFragmentThumbnailImg);
            totalRentPriceTxt = (TextView)view.findViewById(R.id.CompanieItemAdapterAddressTxt);
            districtTxt       = (TextView)view.findViewById(R.id.RentFragmentDistrictTxt);
        }

    }
}