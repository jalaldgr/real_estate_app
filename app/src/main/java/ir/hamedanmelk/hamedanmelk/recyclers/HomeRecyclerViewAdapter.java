package ir.hamedanmelk.hamedanmelk.recyclers;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.LandModel;
import ir.hamedanmelk.hamedanmelk.models.micro.DistrictModel;
import ir.hamedanmelk.hamedanmelk.tools.DownloadImage;
import ir.hamedanmelk.hamedanmelk.tools.MYSQlDBHelper;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

/**
 * {@link RecyclerView.Adapter} that can display a {@link LandModel}.
 *
 */
public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder> {

    private final List<LandModel> landModels;
    Activity act;
    MYSQlDBHelper dbHelper;
    public HomeRecyclerViewAdapter(List<LandModel> items,Activity activity) {
        landModels = items;
        act = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_frgmnt_horizontal_recycler_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        final LandModel landModel = landModels.get(position);
        String price ="توافقی";
        dbHelper = new MYSQlDBHelper(act.getApplicationContext());
        holder.titleTxt.setText(landModel.getTitle());
        new DownloadImage(holder.thumbnailImg).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,Urls.getBaseURL()+landModel.getImages());
        DistrictModel districtModel = dbHelper.GetDistrictByID(landModel.getDistrict_id());
        holder.districtTxt.setText(districtModel.getTitle());
        switch (landModels.get(position).getLand_state_id()) {
            case "1" :
                holder.totalSaleLayout.setVisibility(View.VISIBLE);
                holder.totalMortgageLayout.setVisibility(View.GONE);
                holder.totalRentLayout.setVisibility(View.GONE);
                if(Long.parseLong(landModel.getSaleTotalPrice())>0)
                        price = new DecimalFormat("###,###,###").format(Long.parseLong(landModel.getSaleTotalPrice()))+" تومان";
                holder.totalSalePriceTxt.setText(price);
                break;
            case "2":
                holder.totalMortgageLayout.setVisibility(View.VISIBLE);
                holder.totalRentLayout.setVisibility(View.VISIBLE);
                holder.totalSaleLayout.setVisibility(View.GONE);
                holder.totalMortgagePriceTxt.setText(new DecimalFormat("###,###,###")
                        .format(Long.parseLong(landModel.getMortgageTotalPrice()))+" تومان");
                String rentPrice="ندارد";
                if(Long.parseLong(landModel.getRentTotalPrice())>0)
                    rentPrice = new DecimalFormat("###,###,###").format(Long.parseLong(landModel.getRentTotalPrice()))+" تومان";
                holder.totalRentPriceTxt.setText(rentPrice);
                break;
            case "3":
                holder.totalSaleLayout.setVisibility(View.VISIBLE);
                holder.totalMortgageLayout.setVisibility(View.GONE);
                holder.totalRentLayout.setVisibility(View.GONE);
                if(Long.parseLong(landModel.getSaleTotalPrice())>0)
                    price = new DecimalFormat("###,###,###").format(Long.parseLong(landModel.getSaleTotalPrice()))+" تومان";
                holder.totalSalePriceTxt.setText(price);
                break;
            case "5":
                holder.totalSaleLayout.setVisibility(View.GONE);
                holder.totalMortgageLayout.setVisibility(View.GONE);
                if(Long.parseLong(landModel.getSaleTotalPrice())>0)
                    price = new DecimalFormat("###,###,###").format(Long.parseLong(landModel.getSaleTotalPrice()))+" تومان";
                holder.totalSalePriceTxt.setText(price);

        }
            holder.mainLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final NavController controller= Navigation.findNavController(Objects.requireNonNull(act),R.id.nav_host_fragment);
                Bundle args=new Bundle();
                args.putString("id",landModels.get(position).getId());
                switch (landModels.get(position).getLand_state_id()){
                    case "1" :
                        controller.navigate(R.id.singleSaleFragment,args);
                        break;
                    case  "2":
                        controller.navigate(R.id.singleRentFragment,args);
                        break;
                    case "3" :
                        controller.navigate(R.id.singlePreSaleFragment,args);
                        break;
                    case "5":
                        controller.navigate(R.id.singleParticipationFragment);

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return landModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView titleTxt;
        public final TextView totalSalePriceTxt;
        public final ImageView thumbnailImg;
        public final TextView  totalMortgagePriceTxt;
        public final TextView totalRentPriceTxt;
        public final LinearLayout totalSaleLayout;
        public final LinearLayout totalMortgageLayout;
        public final LinearLayout totalRentLayout;
        public final TextView districtTxt;
        public final LinearLayout mainLinearLayout;

        public ViewHolder(View view) {
            super(view);
            titleTxt = (TextView) view.findViewById(R.id.HomeFragmentTitleTxt);
            totalSalePriceTxt = (TextView) view.findViewById(R.id.HomeFragmentTotalSaleTxt);
            thumbnailImg = (ImageView)view.findViewById(R.id.HomeFragmentThumbnailImg);
            totalMortgagePriceTxt = (TextView)view.findViewById(R.id.HomeFragmentHomelMortgageTxt);
            totalRentPriceTxt = (TextView)view.findViewById(R.id.HomeFragmentTotalRentTxt);
            totalSaleLayout   =(LinearLayout)view.findViewById(R.id.HomeFragmentTotalSalePriceLayout);
            totalMortgageLayout = (LinearLayout)view.findViewById(R.id.HomeFragmentTotalMortgagePriceLayout);
            totalRentLayout     =(LinearLayout)view.findViewById(R.id.HomeFragmentTotalRentPriceLayout);
            districtTxt         = (TextView)view.findViewById(R.id.HomeFragmentDistrictTxt);
            mainLinearLayout = (LinearLayout)view.findViewById(R.id.HomeFragmentMainLinearLayout);


        }

    }
}