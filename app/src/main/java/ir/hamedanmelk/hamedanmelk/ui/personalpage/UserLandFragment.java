package ir.hamedanmelk.hamedanmelk.ui.personalpage;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.UserLandModel;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.HTTPRequestHandlre;
import ir.hamedanmelk.hamedanmelk.tools.Urls;
import ir.hamedanmelk.hamedanmelk.ui.personalpage.UserLandRecyclerViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserLandFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserLandFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "UserLandFragment";
    ArrayList<UserLandModel> userLandModels;
    RecyclerView recyclerView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserLandFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResaleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserLandFragment newInstance(String param1, String param2) {
        UserLandFragment fragment = new UserLandFragment();
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
        Context context=getContext();
        UserLandRequest(context);
        View view = inflater.inflate(R.layout.fragment_user_land, container, false);
        if(view instanceof RecyclerView){
            recyclerView = (RecyclerView)view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        }
        return view;
    }
    public void UserLandRequest(final Context context){
        class UserLandRequestAsync extends AsyncTask<Void, Void, String>{
            private final ProgressDialog progressDialog = new ProgressDialog(context);
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setMessage(getResources().getString(R.string.loading_message));
                progressDialog.show();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (progressDialog.isShowing())progressDialog.dismiss();
                try {
                    JSONObject reader = new JSONObject(s);
                    Log.d(TAG, "onPostExecute: "+s.toString());

                    if(reader.getInt(Constants.JSON_RESPONSE_STATE)==1){
                        JSONObject responseData = new JSONObject(reader.getString(Constants.JSON_RESPONSE_DATA));
                        JSONArray responseList = new JSONArray(responseData.getString("data"));
                        ArrayList<UserLandModel>tempUserLandModels = new ArrayList<UserLandModel>();
                        JSONObject userLandItem;
                        JSONArray imagesArray;
                        for(int i=0; i<responseList.length();i++){
                            userLandItem = responseList.getJSONObject(i);
                            imagesArray =new JSONArray( userLandItem.getString(Constants.SALE_MODEL_IMAGES));

                            UserLandModel userLandModel = new UserLandModel(
                                    userLandItem.getString(Constants.USER_LAND_MODEL_ID),
                                    userLandItem.getString(Constants.USER_LAND_MODEL_TITLE),
                                    userLandItem.getString(Constants.USER_LAND_MODEL_LAND_STATE_ID),
                                    userLandItem.getString(Constants.USER_LAND_MODEL_CREATED_AT),
                                    userLandItem.getString(Constants.USER_LAND_MODEL_LAND_SITUATION_ID),
                                    userLandItem.getString(Constants.USER_LAND_MODEL_VIEW),
                                    imagesArray.get(0).toString(),
                                    userLandItem.getString(Constants.USER_LAND_MODEL_LANDSTATETITLE),
                                    userLandItem.getString(Constants.USER_LAND_MODEL_DISTRICT_ID),
                                    userLandItem.getString(Constants.USER_LAND_MODEL_LAND_SITUATIONTITLE),
                                    userLandItem.getString(Constants.USER_LAND_MODEL_LANDSITUATIONCOLOR),
                                    userLandItem.getString(Constants.USER_LAND_MODEL_FIRST_NAME),
                                    userLandItem.getString(Constants.USER_LAND_MODEL_LAST_NAME)
                            );
                            tempUserLandModels.add(userLandModel);
                        }
                        userLandModels=tempUserLandModels;
                        Log.d(TAG, "onPostExecute userlands: "+userLandModels.toString());
                        recyclerView.setAdapter(new UserLandRecyclerViewAdapter(userLandModels,getActivity()));

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(TAG, "onPostExecute exception:"+e.toString());
                }

            }

            @Override
            protected String doInBackground(Void... voids) {
                SharedPreferences user_pref = Objects.requireNonNull(getActivity()).getSharedPreferences(getString(R.string.user_shared_preference), Context.MODE_PRIVATE);

                HTTPRequestHandlre httpRequestHandlre = new HTTPRequestHandlre();
                HashMap<String, String>params = new HashMap<>();
                params.put(Constants.CONTENT_TYPE,Constants.APPLICATION_JSON);
                params.put("UID",user_pref.getString("id","8"));
                Log.d(TAG, "doInBackground: "+user_pref.getString("id","0"));
                return httpRequestHandlre.sendPostRequest(Urls.getBaseURL()+Urls.getUserLandLists(),params);
            }
        }
        UserLandRequestAsync userLandRequestAsync = new UserLandRequestAsync();
        userLandRequestAsync.execute();

    }
}