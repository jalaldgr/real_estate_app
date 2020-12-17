package ir.hamedanmelk.hamedanmelk.recyclers;

import android.app.Activity;
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
import ir.hamedanmelk.hamedanmelk.models.LandModel;
import ir.hamedanmelk.hamedanmelk.models.micro.DistrictModel;
import ir.hamedanmelk.hamedanmelk.tools.DownloadImage;
import ir.hamedanmelk.hamedanmelk.tools.MYSQlDBHelper;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

public class HomeVerticalRecyclerViewAdapter extends RecyclerView.Adapter<HomeVerticalRecyclerViewAdapter.ViewHolder>{

    private final List<LandModel> landModels;
    Activity act;
    MYSQlDBHelper dbHelper;
    public HomeVerticalRecyclerViewAdapter(List<LandModel> items, Activity activity) {
        landModels = items;
        act = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_frgmnt_vertical_recycler_layout, parent, false);
        return new ViewHolder(view);
    }

//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//
//    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final LandModel landModel = landModels.get(position);
        dbHelper = new MYSQlDBHelper(act.getApplicationContext());
        holder.titleTxt.setText(landModel.getTitle());
        new DownloadImage(holder.thumbnailImg).execute(Urls.getBaseURL()+landModel.getImages());
        DistrictModel districtModel = dbHelper.GetDistrictByID(landModel.getDistrict_id());
        //holder.districtTxt.setText(districtModel.getTitle());
        switch (landModels.get(position).getLand_state_id()) {
            case "1" :
                //holder.totalSaleLayout.setVisibility(View.VISIBLE);
                break;
            case "2":
                //holder.totalMortgageLayout.setVisibility(View.VISIBLE);
                //holder.totalRentLayout.setVisibility(View.VISIBLE);
                break;
            case "3":
                //holder.totalSaleLayout.setVisibility(View.VISIBLE);
        }
        holder.titleTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final NavController controller= Navigation.findNavController(Objects.requireNonNull(act), R.id.nav_host_fragment);
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
        public final ImageView thumbnailImg;

        public ViewHolder(View view) {
            super(view);
            titleTxt = (TextView) view.findViewById(R.id.HomeFragmentVerticalTitleTxt);
            thumbnailImg = (ImageView)view.findViewById(R.id.HomeFragmentVerticalThumbnailImg);
        }

    }

}

