package ir.hamedanmelk.hamedanmelk.ui.newland;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.ImageModel;
import ir.hamedanmelk.hamedanmelk.tools.MYSQlDBHelper;

/**
 * {@link RecyclerView.Adapter} that can display a {@link ImageModel}.
 *
 */
public class SelectedImageRecyclerViewAdapter extends RecyclerView.Adapter<SelectedImageRecyclerViewAdapter.ViewHolder> {

    private final List<ImageModel> imageModels;
    Activity activity;
    private static final String TAG = "imageRecycler";
    MYSQlDBHelper dbHelper;
    public SelectedImageRecyclerViewAdapter(List<ImageModel> items, Activity act) {

        imageModels = items;
        activity = act;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.selected_image_gallery, parent, false);
        Log.d(TAG, "onCreateViewHolder: ");
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        holder.galleryItemImg.setImageURI(imageModels.get(position).getImageUri());
        Log.d(TAG, "onBindViewHolder: ");
    }

    @Override
    public int getItemCount() {
        return imageModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView galleryItemImg;

        public ViewHolder(View view) {
            super(view);
            galleryItemImg = (ImageView)view.findViewById(R.id.GalleryAdapterImg);
        }


    }
}