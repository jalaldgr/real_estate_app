package ir.hamedanmelk.hamedanmelk.recyclers;


import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import org.json.JSONArray;
import org.json.JSONException;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.tools.DownloadImage;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

public class GalleryRecyclerViewAdapter extends PagerAdapter {
    private JSONArray galleryImages;
    private Context context;
    private LayoutInflater layoutInflater;


    public GalleryRecyclerViewAdapter(Context context, JSONArray images) {
        this.context = context;
        galleryImages = images;

    }

    @Override
    public int getCount() {
        return galleryImages.length();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.image_gallery_layout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.GalleryAdapterImg);


        try {
            new DownloadImage(imageView).execute(Urls.getBaseURL()+"/"+galleryImages.getString(position));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}