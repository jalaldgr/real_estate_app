package ir.hamedanmelk.hamedanmelk.ui.company;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.micro.CompanyTypeModel;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.HTTPRequestHandlre;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesFragment extends Fragment {
    private static final String TAG = "ServicesFragment";
    GridView servicesGridView;
    GridView allServicesGridView;
    Button buildingBtn;
    Button decorationBtn;
    Button structuralBtn;
    Button servicesBtn;
    public ServicesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: enter");
        View view = inflater.inflate(R.layout.fragment_services, container, false);

        allServicesGridView = (GridView)view.findViewById(R.id.ServicesFragmentAllGridView);
        buildingBtn = (Button)view.findViewById(R.id.ServicesFragmentBuildingBtn);
        decorationBtn=(Button)view.findViewById(R.id.ServicesFragmentDecorationBtn);
        structuralBtn=(Button)view.findViewById(R.id.ServicesFragmentStructurallBtn);
        servicesBtn = (Button)view.findViewById(R.id.ServicesFragmentServicesBtn);
        buildingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetCompanyTypesRequest(getContext(),"10");
            }
        });
        decorationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetCompanyTypesRequest(getContext(),"12");

            }
        });
        structuralBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetCompanyTypesRequest(getContext(),"11");

            }
        });
        servicesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetCompanyTypesRequest(getContext(),"50");

            }
        });

        return view;
    }

    public void GetCompanyTypesRequest(final Context context, final String parent_id){
        class GetcompanyTypesRequestAsync extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.d(TAG, "onPostExecute: "+s.toString());
                ArrayList<CompanyTypeModel> companyTypeModels = new ArrayList<CompanyTypeModel>() ;
                ArrayList<CompanyTypeModel> allCompanyByparent_idType = new ArrayList<CompanyTypeModel>();

                try {
                    JSONObject reader = new JSONObject(s);
                    Log.d(TAG, "onPostExecute response: "+s.toString());
                    if(reader.getInt(Constants.JSON_RESPONSE_STATE)==1){
                        JSONArray responseList = new JSONArray(reader.getString(Constants.JSON_RESPONSE_DATA));
                        CompanyTypeModel companyTypeModel ;
                        JSONObject responseItem;
                        for(int i =0; i<responseList.length();i++){
                            responseItem = responseList.getJSONObject(i);
                            companyTypeModel = new CompanyTypeModel(
                                    responseItem.getString(Constants.COMPANY_TYPES_ID),
                                    responseItem.getString(Constants.COMPANY_TYPES_TITLE),
                                    responseItem.getString(Constants.COMPANY_TYPES_ORDER),
                                    responseItem.getString(Constants.COMPANY_TYPES_PARENT_ID)
                            );
                            if (companyTypeModel.getParent_id().equals("null")) {
                                companyTypeModels.add(companyTypeModel);
                            }
                            if (companyTypeModel.getParent_id().equals(parent_id)){
                                allCompanyByparent_idType.add(companyTypeModel);
                            }
                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(TAG, "onPostExecute exception:"+e.toString());
                }
//                Log.d(TAG, "onPostExecute: "+companyTypeModels.get(0).getTitle());
                ServicesItemAdapter servicesAllItemAdapter = new ServicesItemAdapter(allCompanyByparent_idType,getContext(),getActivity());
                allServicesGridView.setAdapter(servicesAllItemAdapter);

            }
            @Override
            protected String doInBackground(Void... voids) {
                Log.d(TAG, "doInBackground: started");

                HTTPRequestHandlre httpRequestHandlre = new HTTPRequestHandlre();
                HashMap<String, String> params = new HashMap<>();
                params.put(Constants.CONTENT_TYPE,Constants.APPLICATION_JSON);

                return httpRequestHandlre.sendGetRequest(Urls.getBaseURL()+Urls.getCompanyTypes(),params);
            }
        }
        GetcompanyTypesRequestAsync getcompanyTypesRequestAsync = new GetcompanyTypesRequestAsync();
        getcompanyTypesRequestAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR , null);


    }
}