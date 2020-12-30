package ir.hamedanmelk.hamedanmelk.recyclers;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.micro.EquipmentModel;
import ir.hamedanmelk.hamedanmelk.tools.DownloadImage;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

public class LandEquipmentsAdapter extends BaseAdapter {
    private final ArrayList<EquipmentModel> equipmentModels;
    private final Context context;
    Activity act;
    public LandEquipmentsAdapter(ArrayList<EquipmentModel>items, Context c, Activity activity) {
        equipmentModels = items;
        context = c;
        act = activity;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = ViewGroup.inflate(parent.getContext(), R.layout.singles_land_equipments_items, null);
        }
        ImageView logoImg = (ImageView)view.findViewById(R.id.SinglesLandEquipmentLogoImg);
        TextView  titleTxt= (TextView)view.findViewById(R.id.SinglesLandEquipmentTitleTxt);
        titleTxt.setText(equipmentModels.get(position).getTitle());
        if(equipmentModels.get(position).getLogo()!=("null")) {
            Glide.with(act).load(Urls.getBaseURL() + "/" + equipmentModels.get(position).getLogo()).into(logoImg);
        }
        Log.d("hhh", "getView: "+equipmentModels.get(position).getTitle());
        return view;
    }

    @Override
    public int getCount() {
        return equipmentModels.size();
    }

    @Override
    public Object getItem(int position) {
        return equipmentModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}