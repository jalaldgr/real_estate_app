package ir.hamedanmelk.hamedanmelk.ui.company;

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
import ir.hamedanmelk.hamedanmelk.models.CompanyModel;
import ir.hamedanmelk.hamedanmelk.models.RentModel;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.HTTPRequestHandlre;
import ir.hamedanmelk.hamedanmelk.tools.Urls;
import ir.hamedanmelk.hamedanmelk.ui.category.RentRecyclerViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CompanyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CompanyFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "CompanyFragment";
    private String companyID;
    RecyclerView recyclerView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CompanyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SingleCompanyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CompanyFragment newInstance(String param1, String param2) {
        CompanyFragment fragment = new CompanyFragment();
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
            companyID  = getArguments().getString(Constants.COMPANY_ID);
            Log.d(TAG, "onCreate: "+companyID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_company, container, false);
        if(view instanceof RecyclerView){
            Context context = getContext();
            recyclerView = (RecyclerView)view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        }
         GetCompaniesRequest(getContext());

        return view;
    }

    public void GetCompaniesRequest(final Context context){
        class GetCompaniesRequestAsync extends AsyncTask<Void ,Void, String> {
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
                    JSONArray companiesList = new JSONArray(reader.getString(Constants.JSON_RESPONSE_DATA));
                    JSONObject companyItem;
                    if (reader.getInt(Constants.JSON_RESPONSE_STATE)==1)
                    {
                        ArrayList<CompanyModel> companyModels=new ArrayList<CompanyModel>();
                        for(int i=0; i < companiesList.length();i++)
                        {
                            companyItem = companiesList.getJSONObject(i);
                            CompanyModel companyModel = new CompanyModel(
                                companyItem.getString(Constants.COMPANY_ID),
                                companyItem.getString(Constants.COMPANY_TITLE),
                                companyItem.getString(Constants.COMPANY_MANAGER),
                                companyItem.getString(Constants.COMPANY_PHONE),
                                companyItem.getString(Constants.COMPANY_ADDRESS),
                                companyItem.getString(Constants.COMPANY_COMPANY_TYPE_ID),
                                companyItem.getString(Constants.COMPANY_USER_ID),
                                companyItem.getString(Constants.COMPANY_DISABLED),
                                companyItem.getString(Constants.COMPANY_LOGO),
                                companyItem.getString(Constants.COMPANY_CREATED_AT)
                            );

                            companyModels.add(companyModel);
                        }
//                        Log.d(TAG, "onPostExecute: "+companyModels.get(0).getTitle());
                        recyclerView.setAdapter(new CompaniesRecyclerViewAdapter(companyModels,getActivity()));
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
                params.put(Constants.COMPANY_CID,companyID);
                return httpRequestHandlre.sendPostRequest(Urls.getBaseURL()+Urls.getCompanies(),params);
            }
        }
        GetCompaniesRequestAsync getCompaniesRequestAsync = new GetCompaniesRequestAsync();
        getCompaniesRequestAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR , null);
    }

}