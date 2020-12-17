package ir.hamedanmelk.hamedanmelk.ui.user;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.UserModel;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.HTTPRequestHandlre;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserLogin#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserLogin extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG = "UserLoginFragment";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserLogin() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserLogin.
     */
    // TODO: Rename and change types and number of parameters
    public static UserLogin newInstance(String param1, String param2) {
        UserLogin fragment = new UserLogin();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Define shared preference __user_model_pref for storing current user

        final NavController controller= Navigation.findNavController(Objects.requireNonNull(getActivity()),R.id.nav_host_fragment);
        View v=inflater.inflate(R.layout.fragment_user_login, container, false);
        TextView register = v.findViewById(R.id.UserLoginRegisterText);
        Button login = v.findViewById(R.id.UserLoginFragmentLoginButton);
        final TextView usernametxt = v.findViewById(R.id.UserLogineditTextPhone);
        final TextView passwordtxt = v.findViewById(R.id.UserLogineditTextTextPassword);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validating inputs
                if (TextUtils.isEmpty(usernametxt.getText().toString())) {
                    usernametxt.setError(getResources().getString(R.string.username_input_error_msg));
                    usernametxt.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(passwordtxt.getText().toString())) {
                    passwordtxt.setError(getResources().getString(R.string.password_input_error_msg));
                    passwordtxt.requestFocus();
                    return;
                }
                UserLoginRequest(usernametxt.getText().toString(),passwordtxt.getText().toString(),getContext());
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.userRegister);
            }
        });
        return v;
    }



    public void UserLoginRequest (final String username , final String password , Context context) {

        class UserLoginRequestAsync extends AsyncTask<Void, Void, String> {
            private final ProgressDialog dialog = new ProgressDialog(getContext());
            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
            HTTPRequestHandlre requestHandler = new HTTPRequestHandlre();
                HashMap<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                params.put(getString(R.string.authentication_username),username);
                params.put(getString(R.string.authentication_password),password);
                //returing the response
                return requestHandler.sendPostRequest(Urls.getBaseURL()+Urls.getLoginURL(), params);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();//Log.d("hhh: " , "onPreExecute");
                this.dialog.setMessage(getResources().getString(R.string.loading_message));
                this.dialog.setIndeterminate(true);
                this.dialog.setCanceledOnTouchOutside(false);
                this.dialog.show();
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (this.dialog.isShowing()) this.dialog.dismiss();
                final NavController controller= Navigation.findNavController(Objects.requireNonNull(getActivity()),R.id.nav_host_fragment);

                try {
                    JSONObject obj = new JSONObject(s);
                    if (obj.getInt("State")>0) {
                        //getting the user from the response
                        JSONObject user = obj.getJSONObject("Data");
                        SharedPreferences.Editor editor = Objects.requireNonNull(getActivity()).getSharedPreferences(getString(R.string.user_shared_preference), MODE_PRIVATE).edit();
                        String[] user_model_fields = Constants.USER_MODEL_FIELDS;

                        for (String item : user_model_fields) {
                            editor.putString(item, user.getString(item));
                        }
                        editor.apply();
                        controller.navigate(R.id.navigation_aboutus);
                    }
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
            }
        }
        UserLoginRequestAsync userLoginRequestAsync = new UserLoginRequestAsync();
        userLoginRequestAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR , null);
    }



}