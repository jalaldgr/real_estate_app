package ir.hamedanmelk.hamedanmelk.ui.category;

import android.app.ProgressDialog;
import android.content.Context;
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

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.LandModel;
import ir.hamedanmelk.hamedanmelk.models.PreSaleModel;
import ir.hamedanmelk.hamedanmelk.recyclers.HomeRecyclerViewAdapter;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.HTTPRequestHandlre;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeaturedLandFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeaturedLandFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "FeaturedFragment";
    ArrayList<PreSaleModel> preSaleModels;
    ArrayList<LandModel> landModels;
    ArrayList<LandModel> featuredLandModels;
    RecyclerView recyclerView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FeaturedLandFragment() {
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
    public static FeaturedLandFragment newInstance(String param1, String param2) {
        FeaturedLandFragment fragment = new FeaturedLandFragment();
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
        PreSaleRequest(context);
        View view = inflater.inflate(R.layout.fragment_featured, container, false);

            recyclerView = (RecyclerView)view.findViewById(R.id.featured_list);
            RecyclerView.LayoutManager laymngr =  new LinearLayoutManager(this.getContext());
            recyclerView.setLayoutManager(laymngr);

        return view;
    }
    public void PreSaleRequest(final Context context){
        class PreSaleRequestAsync extends AsyncTask<Void, Void, String>{
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
                    JSONObject ResponseData = new JSONObject(reader.getString(Constants.JSON_RESPONSE_DATA));
                    JSONArray LandList = new JSONArray(ResponseData.getString("data"));
                    JSONObject LandItem;
                    JSONArray imagesArray;

                    if (reader.getInt(Constants.JSON_RESPONSE_STATE)==1)
                    {
                        ArrayList<LandModel> landtemp=new ArrayList<LandModel>();
                        ArrayList<LandModel> featuredlandtemp=new ArrayList<LandModel>();
                        Log.d(TAG, "onPostExecute land list: "+LandList.length());
                        for(int i=0; i < LandList.length();i++)
                        {
                            LandItem = LandList.getJSONObject(i);
                            imagesArray =new JSONArray( LandItem.getString(Constants.SALE_MODEL_IMAGES));
                            LandModel landModel = new LandModel(
                                    LandItem.getString(Constants.LAND_MODEL_ID),
                                    LandItem.getString(Constants.LAND_MODEL_TOTAL_PRICE),
                                    LandItem.getString(Constants.LAND_MODEL_TOTAL_MORTGAGE_PRICE),
                                    LandItem.getString(Constants.LAND_MODEL_TOTAL_RENT_PRICE),
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
                                    LandItem.getString(Constants.LAND_MODEL_LAND_CASE_ID)
                            );

                            landtemp.add(landModel);

                        if(Integer.parseInt(landModel.getLand_case_id())>1){
                            featuredlandtemp.add(landModel);
                        };
                    }

                    featuredLandModels = featuredlandtemp;
                        recyclerView.setAdapter(new HomeRecyclerViewAdapter(featuredLandModels,getActivity()));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(TAG, "onPostExecute exception:"+e.toString());
                }

            }

            @Override
            protected String doInBackground(Void... voids) {
                HTTPRequestHandlre httpRequestHandlre = new HTTPRequestHandlre();
                HashMap<String, String>params = new HashMap<>();
                params.put(Constants.CONTENT_TYPE,Constants.APPLICATION_JSON);
                return httpRequestHandlre.sendGetRequest(Urls.getBaseURL()+Urls.getTotalLands(),params);
            }
        }
        PreSaleRequestAsync preSaleRequestAsync = new PreSaleRequestAsync();
        preSaleRequestAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR , null);

    }
}