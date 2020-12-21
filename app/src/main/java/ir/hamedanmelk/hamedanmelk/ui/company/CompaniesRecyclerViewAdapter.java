package ir.hamedanmelk.hamedanmelk.ui.company;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
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
import ir.hamedanmelk.hamedanmelk.models.CompanyModel;
import ir.hamedanmelk.hamedanmelk.models.PreSaleModel;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.DownloadImage;
import ir.hamedanmelk.hamedanmelk.tools.MYSQlDBHelper;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

/**
 * {@link RecyclerView.Adapter} that can display a {@link CompanyModel}.
 *
 */
public class CompaniesRecyclerViewAdapter extends RecyclerView.Adapter<CompaniesRecyclerViewAdapter.ViewHolder> {

    private final List<CompanyModel> companyModels;
    Activity activity;
    private static final String TAG = "CompaniesRecycler";
    private String userID;
    private String companyUserID;

    MYSQlDBHelper dbHelper;
    public CompaniesRecyclerViewAdapter(List<CompanyModel> items, Activity act) {

        companyModels = items;
        activity = act;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_company_item_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        final CompanyModel companyModel = companyModels.get(position);
        final SharedPreferences user_pref = Objects.requireNonNull(activity).getSharedPreferences(activity.getString(R.string.user_shared_preference), Context.MODE_PRIVATE);
        userID = user_pref.getString(Constants.USER_MODEL_ID,"0");
        companyUserID = companyModel.getUser_id();
        holder.titleTxt.setText(companyModel.getTitle());
        holder.managerTxt.setText(companyModel.getManager());
        holder.addressTxt.setText(companyModel.getAddress());
        holder.mobileTxt.setText(companyModel.getPhone());
        if(!companyModel.getLogo().equals("null")) {
            new DownloadImage(holder.thumbnailImg).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Urls.getBaseURL() + "/" + companyModel.getLogo());
        }
        holder.mobileTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "tel:" + companyModel.getPhone() ;
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
                args.putString(Constants.START_CHAT_TO,companyUserID);
                controller.navigate(R.id.chatFragment,args);
            }
        });
        holder.shareTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "اشتراک گذاری");
                i.putExtra(Intent.EXTRA_TEXT, "https://hamedanmelk.ir/CompaniesList/"+companyModel.getId());
                activity.startActivity(Intent.createChooser(i, "اشتراک گذاری"));
            }
        });

    }

    @Override
    public int getItemCount() {
        return companyModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView titleTxt;
        public final TextView managerTxt;
        public final TextView addressTxt;
        public final TextView mobileTxt;
        public final ImageView thumbnailImg;
        public final TextView chatTxt;
        public final TextView shareTxt;

        public ViewHolder(View view) {
            super(view);
            titleTxt = (TextView) view.findViewById(R.id.CompanieItemAdapterTitleTxt);
            managerTxt = (TextView) view.findViewById(R.id.CompanieItemAdapterManagerTxt);
            addressTxt = (TextView)view.findViewById(R.id.CompanieItemAdapterAddressTxt);
            mobileTxt = (TextView)view.findViewById(R.id.CompaniesItemAdapterMobileTxt);
            thumbnailImg = (ImageView)view.findViewById(R.id.CompaniesItemAdapterThumbnailImg);
            chatTxt = (TextView)view.findViewById(R.id.CompaniesItemAdapterChatTxt);
            shareTxt = (TextView)view.findViewById(R.id.CompaniesItemAdapterShareTxt);
        }


    }
}