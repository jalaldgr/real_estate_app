package ir.hamedanmelk.hamedanmelk.ui.category.agencis;

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
import ir.hamedanmelk.hamedanmelk.models.AgencyModel;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.HTTPRequestHandlre;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AgenciesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgenciesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "AgenciesFragment";
    private String provinceID;
    private String cityID;
    private String areaID;
    private String districtID;
    RecyclerView recyclerView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AgenciesFragment() {
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
    public static AgenciesFragment newInstance(String param1, String param2) {
        AgenciesFragment fragment = new AgenciesFragment();
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
        View view = inflater.inflate(R.layout.fragment_agency, container, false);
        if(view instanceof RecyclerView){
            Context context = getContext();
            recyclerView = (RecyclerView)view.findViewById(R.id.agency_fragment_list);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        }
        GetAgenciesRequest(getContext());

        return view;
    }

    public void GetAgenciesRequest(final Context context){
        class GetAgenciesRequestAsync extends AsyncTask<Void ,Void, String> {
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
                    Log.d(TAG,"response   ::   "+ s.toString());

                    JSONArray agencies = new JSONArray(reader.getString(Constants.JSON_RESPONSE_DATA));
                    JSONObject agencyItem;
                    if (reader.getInt(Constants.JSON_RESPONSE_STATE)==1)
                    {
                        ArrayList<AgencyModel> agencyModels=new ArrayList<AgencyModel>();
                        for(int i=0; i < agencies.length();i++)
                        {
                            agencyItem = agencies.getJSONObject(i);
                            AgencyModel agencyModel = new AgencyModel(
                                    agencyItem.getString(Constants.AGENCY_AREA_ID),
                                    agencyItem.getString(Constants.AGENCY_TITLE),
                                    agencyItem.getString(Constants.AGENCY_OWNER),
                                    agencyItem.getString(Constants.AGENCY_MANAGER),
                                    agencyItem.getString(Constants.AGENCY_ADDRESS),
                                    agencyItem.getString(Constants.AGENCY_PROVINCE_ID),
                                    agencyItem.getString(Constants.AGENCY_CITY_ID),
                                    agencyItem.getString(Constants.AGENCY_AREA_ID),
                                    agencyItem.getString(Constants.AGENCY_DISTRICT_ID),
                                    agencyItem.getString(Constants.AGENCY_USER_ID),
                                    agencyItem.getString(Constants.AGENCY_MOBILE),
                                    agencyItem.getString(Constants.AGENCY_PHONE),
                                    agencyItem.getString(Constants.AGENCY_LOGO),
                                    agencyItem.getString(Constants.AGENCY_DISABLED),
                                    agencyItem.getString(Constants.AGENCY_CREATE_AT),
                                    agencyItem.getString(Constants.AGENCY_USER_IMAGE)
                            );

                            agencyModels.add(agencyModel);
                        }
                        recyclerView.setAdapter(new AgenciesRecyclerViewAdapter(agencyModels,getActivity()));
                    }
                } catch (JSONException e) {
                    Log.d(TAG, "onPostExecute: "+e.toString());
                    e.printStackTrace();
                }

            }

            @Override
            protected String doInBackground(Void... voids) {
                HTTPRequestHandlre httpRequestHandlre = new HTTPRequestHandlre();
                HashMap<String,String> params = new HashMap<>();
                params.put(Constants.CONTENT_TYPE,Constants.APPLICATION_JSON);
                return httpRequestHandlre.sendGetRequest(Urls.getBaseURL()+Urls.getAgencies(),params);
            }
        }
        GetAgenciesRequestAsync getAgenciesRequestAsync = new GetAgenciesRequestAsync();
        getAgenciesRequestAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR , null);
    }

}