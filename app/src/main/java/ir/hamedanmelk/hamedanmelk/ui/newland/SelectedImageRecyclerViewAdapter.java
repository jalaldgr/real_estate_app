package ir.hamedanmelk.hamedanmelk.ui.newland;

import android.app.Activity;
import android.text.NoCopySpan;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Objects;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.ImageModel;
import ir.hamedanmelk.hamedanmelk.tools.MYSQlDBHelper;

/**
 * {@link BaseAdapter} that can display a {@link ImageModel}.
 *
 */
public class SelectedImageRecyclerViewAdapter extends BaseAdapter {

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
    public View getView(final int position, View view, ViewGroup parent) {
        if (view == null) {
            view = ViewGroup.inflate(parent.getContext(), R.layout.selected_image_gallery, null);
        }
        final ImageView  galleryItemImg = (ImageView)view.findViewById(R.id.GalleryAdapterImg);
        ImageView clearImage = (ImageView) view.findViewById(R.id.GalleryAdapterClearImg);
        Glide.with(activity).load(imageModels.get(position).getImageUri()).into(galleryItemImg);

        clearImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageModels.remove(position);
                notifyDataSetChanged();

            }
        });

        return view;
    }


    @Override
    public int getCount() {
        return imageModels.size();
    }

    @Override
    public Object getItem(int position) {
        return imageModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}
