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

import java.util.ArrayList;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.micro.EnergyTypeModel;
import ir.hamedanmelk.hamedanmelk.models.micro.EquipmentModel;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

public class LandEnergyAdapter extends BaseAdapter {
    private final ArrayList<EnergyTypeModel> energyTypeModels;
    private final Context context;
    Activity act;
    public LandEnergyAdapter(ArrayList<EnergyTypeModel>items, Context c, Activity activity) {
        energyTypeModels = items;
        context = c;
        act = activity;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = ViewGroup.inflate(parent.getContext(), R.layout.singles_land_energy_type_items, null);
        }
        TextView stateTitleTxt = (TextView) view.findViewById(R.id.SinglesEnergyStateTitleTxt);
        TextView  typeTitleTxt= (TextView)view.findViewById(R.id.SinglesEnergyTypeTitleTxt);
        stateTitleTxt.setText(energyTypeModels.get(position).getEnergyStateTitle());
        typeTitleTxt.setText(energyTypeModels.get(position).getEnergyTypeTitle());
        return view;
    }

    @Override
    public int getCount() {
        return energyTypeModels.size();
    }

    @Override
    public Object getItem(int position) {
        return energyTypeModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}