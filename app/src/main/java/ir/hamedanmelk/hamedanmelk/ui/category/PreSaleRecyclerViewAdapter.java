package ir.hamedanmelk.hamedanmelk.ui.category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.PreSaleModel;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PreSaleModel}.
 *
 */
public class PreSaleRecyclerViewAdapter extends RecyclerView.Adapter<PreSaleRecyclerViewAdapter.ViewHolder> {

    private final List<PreSaleModel> preSaleModels;

    public PreSaleRecyclerViewAdapter(List<PreSaleModel> items) {
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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        PreSaleModel preSaleModel = preSaleModels.get(position);
        holder.mIdView.setText(preSaleModel.getId());
        holder.mContentView.setText(preSaleModel.getTitle());
    }

    @Override
    public int getItemCount() {
        return preSaleModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;

        public ViewHolder(View view) {
            super(view);
            mIdView = (TextView) view.findViewById(R.id.presale_item_number);
            mContentView = (TextView) view.findViewById(R.id.presale_content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}