package ir.hamedanmelk.hamedanmelk.ui.personalpage;

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
import ir.hamedanmelk.hamedanmelk.models.UserFavoriteModel;
import ir.hamedanmelk.hamedanmelk.models.UserLandModel;
import ir.hamedanmelk.hamedanmelk.tools.DownloadImage;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

/**
 * {@link RecyclerView.Adapter} that can display a {@link UserLandModel}.
 *
 */
public class UserFavoritesRecyclerViewAdapter extends RecyclerView.Adapter<UserFavoritesRecyclerViewAdapter.ViewHolder> {

    private final List<UserFavoriteModel> userFavoriteModels;
    Activity act;
    public UserFavoritesRecyclerViewAdapter(List<UserFavoriteModel> items, Activity activity) {
        act=activity;
        userFavoriteModels= items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_user_favorites_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        final UserFavoriteModel userFavoriteModel = userFavoriteModels.get(position);
        holder.titleTxt.setText(userFavoriteModel.getTitle());

        new DownloadImage(holder.thumbnailImg).execute(Urls.getBaseURL()+userFavoriteModel.getImages());

        holder.titleTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final NavController controller= Navigation.findNavController(Objects.requireNonNull(act),R.id.nav_host_fragment);
                Bundle args=new Bundle();
                args.putString("id",userFavoriteModels.get(position).getId());
                controller.navigate(R.id.singleUserLandFragment,args);
                Log.d("hhh", "onClick from adapter: "+ Integer.toString(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return userFavoriteModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView titleTxt;
        public final ImageView thumbnailImg;

        public ViewHolder(View view) {
            super(view);
            titleTxt = (TextView) view.findViewById(R.id.UserFavoriteFragmentTitleTxt);
            thumbnailImg = (ImageView)view.findViewById(R.id.UserFavoriteFragmentThumbnailImg);

        }

    }
}