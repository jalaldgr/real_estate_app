package ir.hamedanmelk.hamedanmelk.recyclers;

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
import ir.hamedanmelk.hamedanmelk.models.ExchangeModel;
import ir.hamedanmelk.hamedanmelk.tools.DownloadImage;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

/**
 * {@link RecyclerView.Adapter} that can display a {@link ExchangeModel}.
 *
 */
public class ExchangeRecyclerViewAdapter extends RecyclerView.Adapter<ExchangeRecyclerViewAdapter.ViewHolder> {

    private final List<ExchangeModel> exchangeModels;
    Activity act;

    public ExchangeRecyclerViewAdapter(List<ExchangeModel> items, Activity activity) {
        exchangeModels= items;
        act=activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_exchange_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        ExchangeModel exchangeModel = exchangeModels.get(position);
        holder.mIdView.setText(exchangeModel.getId());
        holder.mContentView.setText(exchangeModel.getTitle());
        new DownloadImage(holder.thumbnailImg).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,Urls.getBaseURL()+exchangeModel.getImages());
        holder.mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final NavController controller= Navigation.findNavController(Objects.requireNonNull(act),R.id.nav_host_fragment);
                Bundle args=new Bundle();
                args.putString("id",exchangeModels.get(position).getId());
                controller.navigate(R.id.singleExchangeFragment,args);
                Log.d("hhh", "onClick from adapter: "+ Integer.toString(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return exchangeModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public final ImageView thumbnailImg;

        public ViewHolder(View view) {
            super(view);
            mIdView = (TextView) view.findViewById(R.id.exchange_item_number);
            mContentView = (TextView) view.findViewById(R.id.exchange_item_number);
            thumbnailImg = (ImageView)view.findViewById(R.id.ExchangeThumbnailImg);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}