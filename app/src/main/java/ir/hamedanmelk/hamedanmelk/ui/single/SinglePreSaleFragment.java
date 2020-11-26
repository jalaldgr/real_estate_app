package ir.hamedanmelk.hamedanmelk.ui.single;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.HashMap;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.HTTPRequestHandlre;
import ir.hamedanmelk.hamedanmelk.tools.Urls;
import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SinglePreSaleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SinglePreSaleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG        = "SingleRentFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String landId;
    TextView titleTxt ;
    TextView districtTxt ;
    TextView landTypeTxt ;
    TextView unitInFloorTxt ;
    TextView spaceFoundationTxt ;
    TextView saleTotalPriceTxt  ;
    TextView userDescriptionTxt ;
    TextView deliveryDateTxt;
    PersianDate persianDate;
    public SinglePreSaleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SingleLandFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SinglePreSaleFragment newInstance(String param1, String param2) {
        SinglePreSaleFragment fragment = new SinglePreSaleFragment();
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
            landId  = getArguments().getString(Constants.LAND_INFO_ID);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_single_presale, container, false);
         titleTxt = (TextView)view.findViewById(R.id.SinglePreSaleTitleTxt);
         districtTxt =(TextView)view.findViewById(R.id.SinglePreSaleDistrictTxt);
         landTypeTxt = (TextView)view.findViewById(R.id.SinglePreSaleLandTypeTxt);
         unitInFloorTxt = (TextView)view.findViewById(R.id.SinglePreSaleUnitInFloorTxt);
         spaceFoundationTxt = (TextView)view.findViewById(R.id.SinglePreSaleFoundationSpaceTxt);
         saleTotalPriceTxt = (TextView)view.findViewById(R.id.SinglePreSaleSaleTotalPriceTxt);
         userDescriptionTxt =(TextView)view.findViewById(R.id.SinglePreSaleUserDescriptionMultiTxt);
         deliveryDateTxt = (TextView)view.findViewById(R.id.SinglePreSaleDeliveryDateTxt);
        GetLandInfoRequest(getContext());
        return view;
    }

    public void GetLandInfoRequest(final Context context){
        class GetLandInfoRequestAsync extends AsyncTask<Void, Void, String> {
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
                        JSONArray images = new JSONArray(responseData.getString(Constants.LAND_INFO_IMAGES));
                        titleTxt.setText(responseData.getString(Constants.LAND_INFO_TITLe));
                        districtTxt.setText(responseData.getString(Constants.LAND_INFO_DISTRICT_TITLE));
                        landTypeTxt.setText(responseData.getString(Constants.LAND_INFO_LAND_TYPE_TITLE));
                        spaceFoundationTxt.setText(responseData.getString(Constants.LAND_INFO_FOUNDATION_SPACE));
                        unitInFloorTxt.setText(responseData.getString(Constants.LAND_INFO_UNIT_IN_FLOOR));
                        saleTotalPriceTxt.setText(responseData.getString(Constants.LAND_INFO_SALE_TOTAL_PRICE));
                        userDescriptionTxt.setText(responseData.getString(Constants.LAND_INFO_USER_DESCRIPTION));
                        try {
                            PersianDateFormat persianDateFormat=new PersianDateFormat("yyyy-MM-dd");
                            persianDate = persianDateFormat.parseGrg(responseData.get(Constants.LAND_INFO_DELIVERY_DATE).toString(), "yyyy-MM-dd");
                            deliveryDateTxt.setText(persianDate.toString());
                        }catch (ParseException e){
                            e.printStackTrace();
                        }


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
                HashMap<String, String> params = new HashMap<>();
                params.put(Constants.CONTENT_TYPE,Constants.APPLICATION_JSON);
                return httpRequestHandlre.sendGetRequest(Urls.getBaseURL()+Urls.getGetLandInfo()+"/"+landId,params);
            }
        }
        GetLandInfoRequestAsync getLandInfoRequestAsync = new GetLandInfoRequestAsync();
        getLandInfoRequestAsync.execute();

    }

}