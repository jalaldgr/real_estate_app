package ir.hamedanmelk.hamedanmelk.ui.category;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.LandModel;
import ir.hamedanmelk.hamedanmelk.models.RentModel;
import ir.hamedanmelk.hamedanmelk.recyclers.HomeRecyclerViewAdapter;
import ir.hamedanmelk.hamedanmelk.recyclers.RentRecyclerViewAdapter;
import ir.hamedanmelk.hamedanmelk.tools.CheckConnectivity;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.HTTPRequestHandlre;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "RentFragment";
    ArrayList<RentModel> rentModels;
    ArrayList<LandModel> landModels;
    FrameLayout offlineLyt;
    Button noInternetBtn;
    RecyclerView recyclerView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RentFragment newInstance(String param1, String param2) {
        RentFragment fragment = new RentFragment();
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
        TotalRentRequest(getContext());
        final CheckConnectivity checkConnectivity = new CheckConnectivity();
        View view =  inflater.inflate(R.layout.fragment_rent, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.FragmentRentlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        offlineLyt = (FrameLayout)view.findViewById(R.id.rent_listOfflineLyt);
        noInternetBtn = (Button)view.findViewById(R.id.no_internet_fragment_button_retry);
        if(!checkConnectivity.isNetworkAvailable(getActivity())){
            offlineLyt.setVisibility(View.VISIBLE);
        }
        noInternetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkConnectivity.isNetworkAvailable(getActivity())) {
                    offlineLyt.setVisibility(View.GONE);
                    TotalRentRequest(getContext());
                }

            }
        });
        return view;
    }

    public void TotalRentRequest(final Context context){
        class TotalRentRequestAsync extends AsyncTask<Void ,Void, String> {
            private final ProgressDialog progressDialog=new ProgressDialog(context);
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
                if (progressDialog.isShowing())progressDialog.dismiss();
                try {
                    JSONObject reader = new JSONObject(s);
                    JSONObject ResponseData = new JSONObject(reader.getString(Constants.JSON_RESPONSE_DATA));
                    JSONArray LandList = new JSONArray(ResponseData.getString("data"));
                    JSONObject LandItem;
                    JSONArray imagesArray;
                    if (reader.getInt(Constants.JSON_RESPONSE_STATE)==1)
                    {
                        ArrayList<LandModel> renttemp=new ArrayList<LandModel>();
                        for(int i=0; i < LandList.length();i++)
                        {
                            LandItem = LandList.getJSONObject(i);
                            imagesArray =new JSONArray( LandItem.getString(Constants.SALE_MODEL_IMAGES));
                            LandModel landModel = new LandModel(
                                    LandItem.getString(Constants.LAND_MODEL_ID),
                                    "",
                                    LandItem.getString(Constants.LAND_MODEL_TOTAL_MORTGAGE_PRICE),
                                    LandItem.getString(Constants.LAND_INFO_RENT_TOTAL_PRICE),
                                    LandItem.getString(Constants.LAND_MODEL_TITLE),
                                    LandItem.getString(Constants.LAND_MODEL_LAND_STATE_ID),
                                    LandItem.getString(Constants.LAND_MODEL_CREATED_AT),
                                    LandItem.getString(Constants.LAND_MODEL_LAND_SITUATION_ID),
                                    LandItem.getString(Constants.LAND_MODEL_VIEW),
                                    imagesArray.get(0).toString(),
                                    LandItem.getString(Constants.LAND_MODEL_LANDSTATETITLE),
                                    LandItem.getString(Constants.USER_LAND_MODEL_DISTRICT_ID),
                                    LandItem.getString(Constants.LAND_MODEL_LANDSITUATIONTITLE),
                                    LandItem.getString(Constants.LAND_MODEL_LANDSITUATIONCOLOR),
                                    LandItem.getString(Constants.LAND_MODEL_FIRST_NAME),
                                    LandItem.getString(Constants.LAND_MODEL_LAST_NAME),
                                    ""
                            );

                            renttemp.add(landModel);
                        }
                        landModels=renttemp;
                        recyclerView.setAdapter(new HomeRecyclerViewAdapter(landModels,getActivity()));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(TAG, "onPostExecute exception: "+e.toString());
                }

            }

            @Override
            protected String doInBackground(Void... voids) {
                HTTPRequestHandlre httpRequestHandlre = new HTTPRequestHandlre();
                HashMap<String,String> params = new HashMap<>();
                params.put(Constants.CONTENT_TYPE,Constants.APPLICATION_JSON);
                return httpRequestHandlre.sendGetRequest(Urls.getBaseURL()+Urls.getTotalRentLands(),params);
            }
        }
        TotalRentRequestAsync totalRentRequestAsync = new TotalRentRequestAsync();
        totalRentRequestAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR , null);
    }
}