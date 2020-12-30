package ir.hamedanmelk.hamedanmelk.recyclers;

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
import ir.hamedanmelk.hamedanmelk.models.LawyerModel;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.DownloadImage;
import ir.hamedanmelk.hamedanmelk.tools.MYSQlDBHelper;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

/**
 * {@link RecyclerView.Adapter} that can display a {@link LawyerModel}.
 *
 */
public class LawyersRecyclerViewAdapter extends RecyclerView.Adapter<LawyersRecyclerViewAdapter.ViewHolder> {

    private final List<LawyerModel> lawyerModels;
    Activity activity;
    private static final String TAG = "LawyersRecycler";
    MYSQlDBHelper dbHelper;
    private String userID;
    private String lawyerUserID;
    public LawyersRecyclerViewAdapter(List<LawyerModel> items, Activity act) {

        lawyerModels = items;
        activity = act;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_lawyer_item_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        final LawyerModel lawyerModel = lawyerModels.get(position);
        final SharedPreferences user_pref = Objects.requireNonNull(activity).getSharedPreferences(activity.getString(R.string.user_shared_preference), Context.MODE_PRIVATE);
        userID = user_pref.getString(Constants.USER_MODEL_ID,"0");
        lawyerUserID = lawyerModel.getUser_id();
        holder.titleTxt.setText(lawyerModel.getFullName());
        holder.phoneTxt.setText(lawyerModel.getPhone());
        holder.descriptionTxt.setText(lawyerModel.getDescription());
        if(!lawyerModel.getImage().equals("null")) {
            new DownloadImage(holder.thumbnailImg).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Urls.getBaseURL() + "/" + lawyerModel.getImage());
        }
        holder.phoneTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "tel:" + lawyerModel.getPhone() ;
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                activity.startActivity(intent);
            }
        });

        holder.chatTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final NavController controller= Navigation.findNavController(Objects.requireNonNull(activity),R.id.nav_host_fragment);
                SharedPreferences user_pref = Objects.requireNonNull(activity).getSharedPreferences(activity.getString(R.string.user_shared_preference), Context.MODE_PRIVATE);
                if(user_pref.contains("id")) {

                    Bundle args = new Bundle();
                    args.putString(Constants.START_CHAT_UID, userID);
                    args.putString(Constants.START_CHAT_TO, lawyerUserID);
                    controller.navigate(R.id.chatFragment, args);
                }else
                {
                    controller.navigate(R.id.userLogin);
                }

            }
        });
        holder.shareTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "اشتراک گذاری");
                i.putExtra(Intent.EXTRA_TEXT, "https://hamedanmelk.ir/LawyersList");
                activity.startActivity(Intent.createChooser(i, "اشتراک گذاری"));
            }
        });

    }

    @Override
    public int getItemCount() {
        return lawyerModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView titleTxt;
        public final TextView phoneTxt;
        public final ImageView thumbnailImg;
        public final TextView descriptionTxt;
        public final TextView chatTxt;
        public final TextView shareTxt;


        public ViewHolder(View view) {
            super(view);
            titleTxt = (TextView) view.findViewById(R.id.LawyerItemAdapterTitleTxt);
            phoneTxt = (TextView)view.findViewById(R.id.LawyersItemAdapterMobileTxt);
            thumbnailImg = (ImageView)view.findViewById(R.id.LawyerItemAdapterThumbnailImg);
            descriptionTxt = (TextView)view.findViewById(R.id.LawyerItemAdapterDescriptionTxt);
            chatTxt = (TextView)view.findViewById(R.id.LawyersItemAdapterChatTxt);
            shareTxt = (TextView)view.findViewById(R.id.LawyersItemAdapterShareTxt);
        }
    }
}