package ir.hamedanmelk.hamedanmelk.ui.user;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Objects;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.HTTPRequestHandlre;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PublicUserRegister#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PublicUserRegister extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "PublicUserRegisterFragment";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PublicUserRegister() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserRegister.
     */
    // TODO: Rename and change types and number of parameters
    public static PublicUserRegister newInstance(String param1, String param2) {
        PublicUserRegister fragment = new PublicUserRegister();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final NavController controller= Navigation.findNavController(Objects.requireNonNull(getActivity()),R.id.nav_host_fragment);
        View v = inflater.inflate(R.layout.fragment_user_register, container, false);
        Button registerbtn = (Button)v.findViewById(R.id.PublicUserRegisterRegisterationBtn);
        final TextView firstnametxt = (TextView)v.findViewById(R.id.PublicUserRegistereFirstNametxt);
        final TextView lastnametxt = (TextView)v.findViewById(R.id.PublicUserRegistereLastNametxt);
        final TextView phonetxt = (TextView)v.findViewById(R.id.PublicUserRegisterePhonetxt);
        final RadioGroup gendertypegrp= (RadioGroup)v.findViewById(R.id.PublicUserRegisterGenderTypeRadioGrp);


        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String selectedgender="";
            if(gendertypegrp.getCheckedRadioButtonId()==R.id.PublicUserRegisterGenderTypeMaleRadioBtn)selectedgender="1";
            else if(gendertypegrp.getCheckedRadioButtonId()==R.id.PublicUserRegisterGenderTypeFemaleRadioBtn)selectedgender="2";
            final String finalselectedgender=selectedgender;
            PublicUserRegisterRequest(
                    firstnametxt.getText().toString(),
                    lastnametxt.getText().toString(),
                    finalselectedgender,
                    phonetxt.getText().toString(),
                    getContext()
            );
            }
        });
        return v;
    }

    public void PublicUserRegisterRequest(final String firstname, final String lastname, final String gendertype ,final String phone , final Context context){
        class PublicUserRegisteraAsync extends AsyncTask<Void,Void,String>{
            private final ProgressDialog progressDialog = new ProgressDialog(context);
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog.setMessage(getResources().getString(R.string.loading_message));
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (progressDialog.isShowing()) progressDialog.dismiss();
                final NavController controller= Navigation.findNavController(Objects.requireNonNull(getActivity()),R.id.nav_host_fragment);
                try {
                    JSONObject reader = new JSONObject(s);
                    if(reader.getInt("State")==0){
                        if(reader.getString("Data").contains("قبلا"))
                            Toast.makeText(context,getResources().getString(R.string.duplicate_username_error_msg),Toast.LENGTH_LONG).show();
                    }
                    else if(reader.getInt("State")==1){
                        Toast.makeText(context,getResources().getString(R.string.register_public_user_sucess_msg),Toast.LENGTH_LONG).show();
                        controller.navigate(R.id.userLogin);
                        Log.d(TAG, "onPostExecute: "+reader.getString("Data"));
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d(TAG, "onPostExecute: "+s);

            }

            @Override
            protected String doInBackground(Void... voids) {
                HTTPRequestHandlre httpRequestHandlre = new HTTPRequestHandlre();
                HashMap<String,String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                params.put(Constants.REGISTER_PUBLIC_USER_FIRST_NAME,firstname);
                params.put(Constants.REGISTER_PUBLIC_USER_LAST_NAME,lastname);
                params.put(Constants.REGISTER_PUBLIC_USER_GENDER_TYPE,gendertype);
                params.put(Constants.REGISTER_PUBLIC_USER_MOBILE,phone);
                return httpRequestHandlre.sendPostRequest(Urls.getBaseURL()+Urls.getRegisterPublicUser(),params);
            }
        }
        PublicUserRegisteraAsync publicUserRegisteraAsync=new PublicUserRegisteraAsync();
        publicUserRegisteraAsync.execute();
    }
}