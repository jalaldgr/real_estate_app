package ir.hamedanmelk.hamedanmelk.ui.category;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
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
import java.util.Objects;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.SaleModel;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.HTTPRequestHandlre;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SaleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SaleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "SaleFragment";
    ArrayList<SaleModel> SaleModels;
    RecyclerView recyclerView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SaleFragment() {
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
    public static SaleFragment newInstance(String param1, String param2) {
        SaleFragment fragment = new SaleFragment();
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
        SaleRequest(context);
        View view = inflater.inflate(R.layout.fragment_sale, container, false);
        if(view instanceof RecyclerView){
            recyclerView = (RecyclerView)view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        }


        return view;
    }
        public void SaleRequest(final Context context){
            class SaleRequestAsync extends AsyncTask<Void, Void, String>{
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
                        if(reader.getInt(Constants.JSON_RESPONSE_STATE)==1){
                            JSONObject responseData = new JSONObject(reader.getString(Constants.JSON_RESPONSE_DATA));
                            JSONArray responseList = new JSONArray(responseData.getString("data"));
                            ArrayList<SaleModel>tempSaleModels = new ArrayList<SaleModel>();
                            JSONObject SaleItem;
                            JSONArray imagesArray;
                            for(int i=0; i<responseList.length();i++){
                                SaleItem = responseList.getJSONObject(i);
                                imagesArray =new JSONArray( SaleItem.getString(Constants.SALE_MODEL_IMAGES));

                                        SaleModel SaleModel = new SaleModel(
                                        SaleItem.getString(Constants.SALE_MODEL_ID),
                                        SaleItem.getString(Constants.SALE_MODEL_TOTAL_PRICE),
                                        SaleItem.getString(Constants.SALE_MODEL_TITLE),
                                        SaleItem.getString(Constants.SALE_MODEL_LAND_STATE_ID),
                                        SaleItem.getString(Constants.SALE_MODEL_CREATED_AT),
                                        SaleItem.getString(Constants.SALE_MODEL_LAND_SITUATION_ID),
                                        SaleItem.getString(Constants.SALE_MODEL_VIEW),
                                        imagesArray.get(0).toString(),
                                        SaleItem.getString(Constants.SALE_MODEL_LANDSTATETITLE),
                                        SaleItem.getString(Constants.SALE_MODEL_LAND_SITUATIONTITLE),
                                        SaleItem.getString(Constants.SALE_MODEL_LANDSITUATIONCOLOR),
                                        SaleItem.getString(Constants.SALE_MODEL_FIRST_NAME),
                                        SaleItem.getString(Constants.SALE_MODEL_LAST_NAME)
                                );
                                tempSaleModels.add(SaleModel);
                            }
                            SaleModels=tempSaleModels;
                            Log.d(TAG, "onPostExecute Sales: "+SaleModels.toString());
                            recyclerView.setAdapter(new SaleRecyclerViewAdapter(SaleModels,getActivity()));

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
                    return httpRequestHandlre.sendGetRequest(Urls.getBaseURL()+Urls.getTotalSaleLands(),params);
                }
            }
            SaleRequestAsync SaleRequestAsync = new SaleRequestAsync();
            SaleRequestAsync.execute();

        }
}