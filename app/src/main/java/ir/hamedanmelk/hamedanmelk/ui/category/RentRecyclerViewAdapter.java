package ir.hamedanmelk.hamedanmelk.ui.category;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.RentModel;

import java.util.List;
import java.util.Objects;

/**
 * {@link RecyclerView.Adapter} that can display a {@link RentModel}.
 *
 */
public class RentRecyclerViewAdapter extends RecyclerView.Adapter<RentRecyclerViewAdapter.ViewHolder> {

    private final List<RentModel> rentModels;
    Activity act;

    public RentRecyclerViewAdapter(List<RentModel> items,Activity activity) {
        rentModels= items;
        act=activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_rent_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        RentModel rentModel = rentModels.get(position);
        holder.mIdView.setText(rentModel.getId());
        holder.mContentView.setText(rentModel.getTitle());
        holder.mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final NavController controller= Navigation.findNavController(Objects.requireNonNull(act),R.id.nav_host_fragment);
                Bundle args=new Bundle();
                args.putString("id",rentModels.get(position).getId());
                controller.navigate(R.id.singleRentFragment,args);
                Log.d("hhh", "onClick from adapter: "+ Integer.toString(position));
            }
        });
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