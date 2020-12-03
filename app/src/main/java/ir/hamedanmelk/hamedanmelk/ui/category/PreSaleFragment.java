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
import ir.hamedanmelk.hamedanmelk.models.PreSaleModel;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.HTTPRequestHandlre;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PreSaleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PreSaleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "PreSaleFragment";
    ArrayList<PreSaleModel> preSaleModels;
    RecyclerView recyclerView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PreSaleFragment() {
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
    public static PreSaleFragment newInstance(String param1, String param2) {
        PreSaleFragment fragment = new PreSaleFragment();
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
        View view = inflater.inflate(R.layout.fragment_presale, container, false);
        if(view instanceof RecyclerView){
            recyclerView = (RecyclerView)view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        }
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
                try {
                    JSONObject reader = new JSONObject(s);
                    if(reader.getInt(Constants.JSON_RESPONSE_STATE)==1){
                        JSONObject responseData = new JSONObject(reader.getString(Constants.JSON_RESPONSE_DATA));
                        JSONArray responseList = new JSONArray(responseData.getString("data"));
                        JSONArray imagesArray;
                        ArrayList<PreSaleModel>tempPreSaleModels = new ArrayList<PreSaleModel>();
                        JSONObject preSaleItem;
                        for(int i=0; i<responseList.length();i++){
                            preSaleItem = responseList.getJSONObject(i);
                            imagesArray =new JSONArray( preSaleItem.getString(Constants.SALE_MODEL_IMAGES));

                            PreSaleModel preSaleModel = new PreSaleModel(
                                    preSaleItem.getString(Constants.PRE_SALE_MODEL_ID),
                                    preSaleItem.getString(Constants.PRE_SALE_MODEL_TOTAL_SALE_PRICE),
                                    preSaleItem.getString(Constants.PRE_SALE_MODEL_TITLE),
                                    preSaleItem.getString(Constants.PRE_SALE_MODEL_LAND_STATE_ID),
                                    preSaleItem.getString(Constants.PRE_SALE_MODEL_CREATED_AT),
                                    preSaleItem.getString(Constants.PRE_SALE_MODEL_LAND_SITUATION_ID),
                                    preSaleItem.getString(Constants.PRE_SALE_MODEL_VIEW),
                                    imagesArray.get(0).toString(),
                                    preSaleItem.getString(Constants.PRE_SALE_MODEL_LANDSTATETITLE),
                                    preSaleItem.getString(Constants.PRE_SALE_MODEL_LAND_SITUATIONTITLE),
                                    preSaleItem.getString(Constants.PRE_SALE_MODEL_LANDSITUATIONCOLOR),
                                    preSaleItem.getString(Constants.PRE_SALE_MODEL_FIRST_NAME),
                                    preSaleItem.getString(Constants.PRE_SALE_MODEL_LAST_NAME)
                            );
                            tempPreSaleModels.add(preSaleModel);
                        }
                        preSaleModels=tempPreSaleModels;
                        Log.d(TAG, "onPostExecute preSales: "+preSaleModels.toString());
                        recyclerView.setAdapter(new PreSaleRecyclerViewAdapter(preSaleModels,getActivity()));

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(TAG, "onPostExecute exception:"+e.toString());
                }

                if (progressDialog.isShowing())progressDialog.dismiss();
            }

            @Override
            protected String doInBackground(Void... voids) {
                HTTPRequestHandlre httpRequestHandlre = new HTTPRequestHandlre();
                HashMap<String, String>params = new HashMap<>();
                params.put(Constants.CONTENT_TYPE,Constants.APPLICATION_JSON);
                return httpRequestHandlre.sendGetRequest(Urls.getBaseURL()+Urls.getTotalPreSaleLands(),params);
            }
        }
        PreSaleRequestAsync preSaleRequestAsync = new PreSaleRequestAsync();
        preSaleRequestAsync.execute();

    }
}