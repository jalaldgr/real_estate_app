package ir.hamedanmelk.hamedanmelk.ui.newland;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.text.NoCopySpan;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
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

    List<ImageModel> imageModels=new ArrayList<>();
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
        TextView featuredTxt = (TextView)view.findViewById(R.id.GalleryAdapterFeaturedTxt);
        Glide.with(activity).load(imageModels.get(position).getImageUri()).into(galleryItemImg);

        if(position==0){
            featuredTxt.setText(activity.getResources().getString(R.string.vocabulary_featured_image));
            Drawable img = activity.getResources().getDrawable(R.drawable.ic_baseline_featured_video_24);
            img.setBounds(0, 0, 60, 60);
            featuredTxt.setCompoundDrawables(img, null, null, null);
        }
        featuredTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageModel temp = imageModels.get(position);
                imageModels.remove(position);
                imageModels.add(0,temp);
                notifyDataSetChanged();

            }
        });
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
