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
        holder.mIdView.setText(preSaleModel.getId());
        holder.mContentView.setText(preSaleModel.getTitle());

            new DownloadImage(holder.thumbnailImg).execute(Urls.getBaseURL()+preSaleModel.getImages());

        holder.mContentView.setOnClickListener(new View.OnClickListener() {
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
        public final TextView mIdView;
        public final TextView mContentView;
        public final ImageView thumbnailImg;

        public ViewHolder(View view) {
            super(view);
            mIdView = (TextView) view.findViewById(R.id.presale_item_number);
            mContentView = (TextView) view.findViewById(R.id.presale_content);
            thumbnailImg = (ImageView)view.findViewById(R.id.PreSaleThumbnailImg);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}