package ir.hamedanmelk.hamedanmelk.ui.personalpage;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;

import java.util.Objects;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.tools.Constants;

public class notLoggedInPreference extends Preference {
    private static final String TAG="notLoggedInLayoutPreference";

    String descStr;
    final String[] user_model_fields = Constants.USER_MODEL_FIELDS;



    private Button loginBtn;
    View.OnClickListener loginBtnClickListener;

    public notLoggedInPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        final SharedPreferences user_pref = Objects.requireNonNull(
                context.getSharedPreferences(context.getString(R.string.user_shared_preference), Context.MODE_PRIVATE)
        );


    }

    //onBindViewHolder() will be called after we call setImageClickListener() from SettingsFragment
    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        loginBtn   =(Button)holder.findViewById(R.id.preference_login_button);
        loginBtn.setOnClickListener(loginBtnClickListener);
    }

    public void setLoginButtonClickListener(View.OnClickListener onClickListener)
    {
        loginBtnClickListener = onClickListener;
    }
}