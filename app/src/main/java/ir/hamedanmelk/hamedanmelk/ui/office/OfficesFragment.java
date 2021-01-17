package ir.hamedanmelk.hamedanmelk.ui.office;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.CompanyModel;
import ir.hamedanmelk.hamedanmelk.models.OfficeModel;
import ir.hamedanmelk.hamedanmelk.tools.CheckConnectivity;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.HTTPRequestHandlre;
import ir.hamedanmelk.hamedanmelk.tools.Urls;
import ir.hamedanmelk.hamedanmelk.ui.company.CompaniesRecyclerViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OfficesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OfficesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "OfficeFragment";
    private String provinceID;
    private String cityID;
    private String areaID;
    private String districtID;
    RecyclerView recyclerView;
    FrameLayout offlineLyt;
    Button noInternetBtn;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OfficesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OfficeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OfficesFragment newInstance(String param1, String param2) {
        OfficesFragment fragment = new OfficesFragment();
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
        View view = inflater.inflate(R.layout.fragment_offices, container, false);
        final CheckConnectivity checkConnectivity = new CheckConnectivity();

        recyclerView = (RecyclerView)view.findViewById(R.id.list_Office);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        offlineLyt = (FrameLayout)view.findViewById(R.id.office_listOfflineLyt);
        noInternetBtn = (Button)view.findViewById(R.id.no_internet_fragment_button_retry);

        if(!checkConnectivity.isNetworkAvailable(getActivity())){
            offlineLyt.setVisibility(View.VISIBLE);
        }
        noInternetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkConnectivity.isNetworkAvailable(getActivity())) {
                    offlineLyt.setVisibility(View.GONE);
                    GetOfficesRequest(getContext());
                }

            }
        });
        GetOfficesRequest(getContext());
        return view;
    }

    public void GetOfficesRequest(final Context context){
        class GetOfficesRequestAsync extends AsyncTask<Void ,Void, String> {
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
                    Log.d(TAG, s.toString());

                    JSONArray offices = new JSONArray(reader.getString(Constants.JSON_RESPONSE_DATA));
                    JSONObject officeItem;
                    if (reader.getInt(Constants.JSON_RESPONSE_STATE)==1)
                    {
                        ArrayList<OfficeModel> officeModels=new ArrayList<OfficeModel>();
                        for(int i=0; i < offices.length();i++)
                        {
                            officeItem = offices.getJSONObject(i);
                            OfficeModel officeModel = new OfficeModel(
                                officeItem.getString(Constants.OFFICE_ID),
                                officeItem.getString(Constants.OFFICE_TITLE),
                                officeItem.getString(Constants.OFFICE_MANAGER),
                                officeItem.getString(Constants.OFFICE_NO),
                                officeItem.getString(Constants.OFFICE_ADDRESS),
                                officeItem.getString(Constants.OFFICE_LOGO),
                                officeItem.getString(Constants.OFFICE_PHONE),
                                officeItem.getString(Constants.OFFICE_FAX),
                                officeItem.getString(Constants.OFFICE_DISABLED),
                                officeItem.getString(Constants.OFFICE_PROVINCE_ID),
                                officeItem.getString(Constants.OFFICE_CITY_ID),
                                officeItem.getString(Constants.OFFICE_AREA_ID),
                                officeItem.getString(Constants.OFFICE_DISTRICT_ID),
                                officeItem.getString(Constants.OFFICE_USER_ID),
                                officeItem.getString(Constants.OFFICE_CREATED_AT)
                            );

                            officeModels.add(officeModel);
                        }
                        recyclerView.setAdapter(new OfficesRecyclerViewAdapter(officeModels,getActivity()));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            protected String doInBackground(Void... voids) {
                HTTPRequestHandlre httpRequestHandlre = new HTTPRequestHandlre();
                HashMap<String,String> params = new HashMap<>();
                params.put(Constants.CONTENT_TYPE,Constants.APPLICATION_JSON);
                return httpRequestHandlre.sendGetRequest(Urls.getBaseURL()+Urls.getOffices(),params);
            }
        }
        GetOfficesRequestAsync getOfficesRequestAsync = new GetOfficesRequestAsync();
        getOfficesRequestAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR , null);
    }

}