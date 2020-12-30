package ir.hamedanmelk.hamedanmelk.ui.personalpage;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Objects;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.UserLandModel;
import ir.hamedanmelk.hamedanmelk.tools.DownloadImage;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

/**
 * {@link RecyclerView.Adapter} that can display a {@link UserLandModel}.
 *
 */
public class UserLandRecyclerViewAdapter extends RecyclerView.Adapter<UserLandRecyclerViewAdapter.ViewHolder> {

    private final List<UserLandModel> userLandModels;
    Activity act;
    public UserLandRecyclerViewAdapter(List<UserLandModel> items, Activity activity) {
        act=activity;
        userLandModels= items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_user_land_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        UserLandModel userLandModel = userLandModels.get(position);
        holder.titleTxt.setText(userLandModel.getTitle());

        Glide.with(act).load(Urls.getBaseURL()+userLandModel.getImages()).into(holder.thumbnailImg);
        holder.titleTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final NavController controller = Navigation.findNavController(Objects.requireNonNull(act), R.id.nav_host_fragment);
                Bundle args = new Bundle();
                    args.putString("id", userLandModels.get(position).getId());
                Log.d("kkkk", "onClick: "+userLandModels.get(position).getLand_situation_id());
                if (userLandModels.get(position).getLand_situation_id().equals("3")) {

                    switch (userLandModels.get(position).getLand_state_id()) {
                        case "1":
                            controller.navigate(R.id.singleSaleFragment, args);
                            break;
                        case "2":
                            controller.navigate(R.id.singleRentFragment, args);
                            break;
                        case "3":
                            controller.navigate(R.id.singlePreSaleFragment, args);
                            break;
                        case "5":
                            controller.navigate(R.id.singleParticipationFragment, args);
                            break;

                    }
                }
                else{
                    Toast.makeText(act.getApplicationContext(),"آگهی فعال نیست و یا تایید نشده",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return userLandModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView titleTxt;
        public final ImageView thumbnailImg;

        public ViewHolder(View view) {
            super(view);
            titleTxt = (TextView) view.findViewById(R.id.UserLandFragmentTitleTxt);
            thumbnailImg = (ImageView)view.findViewById(R.id.UserLandFragmentThumbnailImg);

        }

    }
}