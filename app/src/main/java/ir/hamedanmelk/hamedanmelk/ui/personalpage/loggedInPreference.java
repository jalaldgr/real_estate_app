package ir.hamedanmelk.hamedanmelk.ui.personalpage;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;

import java.util.Objects;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.DownloadImage;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

public class loggedInPreference extends Preference {
    private static final String TAG="LoggedInLayoutPreference";
    String fullNameStr;
    String avatarStr;
    String phoneStr;
    final String[] user_model_fields = Constants.USER_MODEL_FIELDS;

    private ImageView imageView;
    private TextView nameTxt;
    private TextView phoneTxt;
    private Button exitBtn;
    View.OnClickListener exitBtnClickListener;

    public loggedInPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        final SharedPreferences user_pref = Objects.requireNonNull(
                context.getSharedPreferences(context.getString(R.string.user_shared_preference), Context.MODE_PRIVATE)
        );
//        final NavController controller= Navigation.findNavController(Objects.requireNonNull(),R.id.nav_host_fragment);
//        SharedPreferences dddd = this.getContext().getSharedPreferences()
        Log.d(TAG, "ImageViewPreference: "+user_pref.getString("id","0"));
        fullNameStr =user_pref.getString(user_model_fields[2],"")+" "+user_pref.getString(user_model_fields[3],"");
        phoneStr    =user_pref.getString(user_model_fields[1],"");

        avatarStr =Urls.getBaseURL()+"/"+user_pref.getString(user_model_fields[4], Urls.getNoImage());

    }

    //onBindViewHolder() will be called after we call setImageClickListener() from SettingsFragment
    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);


        imageView = (ImageView)holder.findViewById(R.id.preference_image);
        nameTxt   =(TextView)holder.findViewById(R.id.preference_name_text);
        phoneTxt  =(TextView)holder.findViewById(R.id.preference_phone_text);
        exitBtn   =(Button)holder.findViewById(R.id.preference_logout_button);
        new DownloadImage(imageView).execute(avatarStr);
        nameTxt.setText(fullNameStr);
        phoneTxt.setText(phoneStr);
        exitBtn.setOnClickListener(exitBtnClickListener);
    }

    public void setExitButtonClickListener(View.OnClickListener onClickListener)
    {
        exitBtnClickListener = onClickListener;
    }
}