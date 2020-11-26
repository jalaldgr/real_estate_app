package ir.hamedanmelk.hamedanmelk.ui.home;

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
import ir.hamedanmelk.hamedanmelk.models.LandModel;
import ir.hamedanmelk.hamedanmelk.tools.DownloadImage;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

/**
 * {@link RecyclerView.Adapter} that can display a {@link LandModel}.
 *
 */
public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder> {

    private final List<LandModel> landModels;
    Activity act;
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
        holder.mIdView.setText(landModel.getId());
        holder.mContentView.setText(landModel.getTitle());
        new DownloadImage(holder.thumbnailImg).execute(Urls.getBaseURL()+landModel.getImages());
        holder.mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final NavController controller= Navigation.findNavController(Objects.requireNonNull(act),R.id.nav_host_fragment);
                Bundle args=new Bundle();
                args.putString("id",landModels.get(position).getId());
                switch (landModels.get(position).getLand_state_id()){
                    case "1" :
                        controller.navigate(R.id.singleLandFragment,args);
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
        public final TextView mIdView;
        public final TextView mContentView;
        public final ImageView thumbnailImg;

        public ViewHolder(View view) {
            super(view);
            mIdView = (TextView) view.findViewById(R.id.home_frgmnt_horizontal_recycler_layout_info_titletxt);
            mContentView = (TextView) view.findViewById(R.id.home_frgmnt_horizontal_recycler_layout_info_addresstxt);
            thumbnailImg = (ImageView)view.findViewById(R.id.home_frgmnt_horizontal_recycler_layout_pic_imageview);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}