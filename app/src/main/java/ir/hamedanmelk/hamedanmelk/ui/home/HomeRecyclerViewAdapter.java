package ir.hamedanmelk.hamedanmelk.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.LandModel;

/**
 * {@link RecyclerView.Adapter} that can display a {@link LandModel}.
 *
 */
public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder> {

    private final List<LandModel> landModels;

    public HomeRecyclerViewAdapter(List<LandModel> items) {
        landModels = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_frgmnt_horizontal_recycler_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        LandModel landModel = landModels.get(position);
        holder.mIdView.setText(landModel.getId());
        holder.mContentView.setText(landModel.getTitle());
    }

    @Override
    public int getItemCount() {
        return landModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;

        public ViewHolder(View view) {
            super(view);
            mIdView = (TextView) view.findViewById(R.id.home_frgmnt_horizontal_recycler_layout_info_titletxt);
            mContentView = (TextView) view.findViewById(R.id.home_frgmnt_horizontal_recycler_layout_info_addresstxt);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}