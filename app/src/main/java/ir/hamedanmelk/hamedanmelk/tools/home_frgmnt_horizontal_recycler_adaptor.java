package ir.hamedanmelk.hamedanmelk.tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.Estate;

public class home_frgmnt_horizontal_recycler_adaptor extends RecyclerView.Adapter<home_frgmnt_horizontal_recycler_adaptor.MyViewHolder>{
    List<Estate> EstateList;
    Context Cntx;

    public home_frgmnt_horizontal_recycler_adaptor(List<Estate> estateList, Context cntx) {
        EstateList = estateList;
        Cntx = cntx;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myview = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_frgmnt_horizontal_recycler_layout,parent,false);
        return new MyViewHolder(myview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Estate estateItem = EstateList.get(position);

        //holder.img.setImageResource(estateItem.getEstatePictures());
        holder.TitleTxt.setText(estateItem.getEstateTitle());
        holder.AddressTxt.setText(estateItem.getEstateAddress());
        holder.PriceTxt.setText(estateItem.getEstatePrice());

    }

    @Override
    public int getItemCount() {
        return EstateList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView img;
        public TextView TitleTxt;
        public TextView AddressTxt;
        public TextView PriceTxt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img             = itemView.findViewById(R.id.home_frgmnt_horizontal_recycler_layout_pic_imageview);
            TitleTxt        = itemView.findViewById(R.id.home_frgmnt_horizontal_recycler_layout_info_titletxt);
            AddressTxt      = itemView.findViewById(R.id.home_frgmnt_horizontal_recycler_layout_info_addresstxt);
            PriceTxt        = itemView.findViewById(R.id.home_frgmnt_horizontal_recycler_layout_info_pricetxt);
        }
    }
}
