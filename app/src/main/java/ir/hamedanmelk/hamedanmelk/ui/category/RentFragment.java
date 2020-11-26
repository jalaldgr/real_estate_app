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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.RentModel;
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
        View view =  inflater.inflate(R.layout.fragment_rent, container, false);
        if(view instanceof RecyclerView){
            Context context = getContext();
            recyclerView = (RecyclerView)view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        }
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
                    JSONArray RentList = new JSONArray(ResponseData.getString("data"));
                    JSONObject RentItem;
                    JSONArray imagesArray;
                    if (reader.getInt(Constants.JSON_RESPONSE_STATE)==1)
                    {
                        ArrayList<RentModel> renttemp=new ArrayList<RentModel>();
                        for(int i=0; i < RentList.length();i++)
                        {
                            RentItem = RentList.getJSONObject(i);
                            imagesArray =new JSONArray( RentItem.getString(Constants.SALE_MODEL_IMAGES));
                            RentModel rentModel = new RentModel(
                                    RentItem.getString(Constants.RENT_MODEL_ID),
                                    RentItem.getString(Constants.RENT_MODEL_TITLE),
                                    RentItem.getString(Constants.RENT_MODEL_LAND_STATE_ID),
                                    RentItem.getString(Constants.RENT_MODEL_LAND_SITUATION_ID),
                                    RentItem.getString(Constants.RENT_MODEL_VIEW),
                                    imagesArray.get(0).toString(),
                                    RentItem.getString(Constants.RENT_MODEL_LAND_STATE_TITLE),
                                    RentItem.getString(Constants.RENT_MODEL_LAND_SITUATION_TITLE),
                                    RentItem.getString(Constants.RENT_MODEL_LAND_SITUATION_COLOR),
                                    RentItem.getString(Constants.RENT_MODEL_FIRST_NAME),
                                    RentItem.getString(Constants.RENT_MODEL_LAST_NAME)
                                    );

                            renttemp.add(rentModel);
                        }
                        rentModels=renttemp;
                        Log.d(TAG, "onPostExecute rentModels: "+rentModels.toString());
                        recyclerView.setAdapter(new RentRecyclerViewAdapter(rentModels,getActivity()));

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
        totalRentRequestAsync.execute();
    }
}