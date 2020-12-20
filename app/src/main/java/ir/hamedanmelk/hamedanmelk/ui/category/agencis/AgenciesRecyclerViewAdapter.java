package ir.hamedanmelk.hamedanmelk.ui.category.agencis;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.AgencyModel;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.DownloadImage;
import ir.hamedanmelk.hamedanmelk.tools.MYSQlDBHelper;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

/**
 * {@link RecyclerView.Adapter} that can display a {@link ir.hamedanmelk.hamedanmelk.models.AgencyModel}.
 *
 */
public class AgenciesRecyclerViewAdapter extends RecyclerView.Adapter<AgenciesRecyclerViewAdapter.ViewHolder> {

    private final List<AgencyModel> agencyModels;
    Activity activity;
    private static final String TAG = "AgenciesRecycler";
    MYSQlDBHelper dbHelper;
    private String userID;
    private String agencyUserID;
    public AgenciesRecyclerViewAdapter(List<AgencyModel> items, Activity act) {

        agencyModels = items;
        activity = act;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_agency_item_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        final AgencyModel agencyModel = agencyModels.get(position);
        final SharedPreferences user_pref = Objects.requireNonNull(activity).getSharedPreferences(activity.getString(R.string.user_shared_preference), Context.MODE_PRIVATE);
        userID = user_pref.getString(Constants.USER_MODEL_ID,"0");
        agencyUserID  = agencyModel.getUser_id();
        holder.nameTxt.setText(agencyModel.getTitle());
        holder.addressTxt.setText(agencyModel.getAddress());
        holder.phoneTxt.setText(agencyModel.getPhone());
        holder.mobileTxt.setText(agencyModel.getMobile());
        holder.managerTxt.setText(agencyModel.getManager());
        if(agencyModel.getUserImage()!="null"){
            new DownloadImage(holder.avatarImg).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,Urls.getBaseURL()+"/"+agencyModel.getUserImage());
        }
        if(agencyModel.getLogo() != "null") {
            new DownloadImage(holder.thumbnailImg).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Urls.getBaseURL() + "/" + agencyModel.getLogo());
        }
        holder.phoneTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "tel:" + agencyModel.getPhone() ;
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                activity.startActivity(intent);
            }
        });
        holder.mobileTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "tel:" + agencyModel.getMobile() ;
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                activity.startActivity(intent);
            }
        });
        holder.chatTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final NavController controller= Navigation.findNavController(Objects.requireNonNull(activity),R.id.nav_host_fragment);
                Bundle args=new Bundle();
                args.putString(Constants.START_CHAT_UID,userID);
                args.putString(Constants.START_CHAT_TO,agencyUserID);
                controller.navigate(R.id.chatFragment,args);
            }
        });
        holder.shareTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "اشتراک گذاری");
                i.putExtra(Intent.EXTRA_TEXT, "https://hamedanmelk.ir/AgencyList");
                activity.startActivity(Intent.createChooser(i, "اشتراک گذاری"));
            }
        });



    }

    @Override
    public int getItemCount() {
        return agencyModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView nameTxt;
        public final TextView addressTxt;
        public final TextView mobileTxt;
        public final TextView phoneTxt;
        public final ImageView avatarImg;
        public final TextView managerTxt;
        public final TextView chatTxt;
        public final TextView shareTxt;
public final ImageView thumbnailImg;

        public ViewHolder(View view) {
            super(view);
            nameTxt = (TextView) view.findViewById(R.id.AgencyItemAdapterNameTxt);
            addressTxt = (TextView)view.findViewById(R.id.AgencyItemAdapterAddressTxt);
            phoneTxt = (TextView)view.findViewById(R.id.AgencyItemAdapterPhoneTxt);
            mobileTxt = (TextView)view.findViewById(R.id.AgencyItemAdapterMobileTxt);
            thumbnailImg = (ImageView)view.findViewById(R.id.AgencyItemAdapterThumbnailImg);
            avatarImg = (ImageView)view.findViewById(R.id.AgencyItemAdapterAvatarImg);
            managerTxt = (TextView)view.findViewById(R.id.AgencyItemAdapterManagerTxt);
            chatTxt = (TextView)view.findViewById(R.id.AgencyItemAdapterChatTxt);
            shareTxt = (TextView)view.findViewById(R.id.AgencyItemAdapterShareTxt);
        }
    }
}