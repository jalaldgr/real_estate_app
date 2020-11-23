package ir.hamedanmelk.hamedanmelk.ui.category;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.RentModel;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link RentModel}.
 *
 */
public class RentRecyclerViewAdapter extends RecyclerView.Adapter<RentRecyclerViewAdapter.ViewHolder> {

    private final List<RentModel> rentModels;

    public RentRecyclerViewAdapter(List<RentModel> items) {
        rentModels= items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_rent_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        RentModel rentModel = rentModels.get(position);
        holder.mIdView.setText(rentModel.getId());
        holder.mContentView.setText(rentModel.getTitle());
    }

    @Override
    public int getItemCount() {
        return rentModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;

        public ViewHolder(View view) {
            super(view);
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}