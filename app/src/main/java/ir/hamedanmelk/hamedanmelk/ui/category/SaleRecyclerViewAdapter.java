package ir.hamedanmelk.hamedanmelk.ui.category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.SaleModel;

/**
 * {@link RecyclerView.Adapter} that can display a {@link SaleModel}.
 *
 */
public class SaleRecyclerViewAdapter extends RecyclerView.Adapter<SaleRecyclerViewAdapter.ViewHolder> {

    private final List<SaleModel> SaleModels;

    public SaleRecyclerViewAdapter(List<SaleModel> items) {
        SaleModels= items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_sale_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        SaleModel SaleModel = SaleModels.get(position);
        holder.mIdView.setText(SaleModel.getId());
        holder.mContentView.setText(SaleModel.getTitle());
    }

    @Override
    public int getItemCount() {
        return SaleModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;

        public ViewHolder(View view) {
            super(view);
            mIdView = (TextView) view.findViewById(R.id.sale_item_number);
            mContentView = (TextView) view.findViewById(R.id.sale_content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}