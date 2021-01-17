package ir.hamedanmelk.hamedanmelk.ui.lawyer;

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
import ir.hamedanmelk.hamedanmelk.models.LawyerModel;
import ir.hamedanmelk.hamedanmelk.tools.CheckConnectivity;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.HTTPRequestHandlre;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LawyersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LawyersFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "LawyersFragment";
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

    public LawyersFragment() {
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
    public static LawyersFragment newInstance(String param1, String param2) {
        LawyersFragment fragment = new LawyersFragment();
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
        View view = inflater.inflate(R.layout.fragment_lawyers, container, false);
        final CheckConnectivity checkConnectivity = new CheckConnectivity();
        recyclerView = (RecyclerView)view.findViewById(R.id.list_lawyers);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        offlineLyt = (FrameLayout)view.findViewById(R.id.lawyers_listOfflineLyt);
        noInternetBtn = (Button)view.findViewById(R.id.no_internet_fragment_button_retry);

        if(!checkConnectivity.isNetworkAvailable(getActivity())){
            offlineLyt.setVisibility(View.VISIBLE);
        }
        noInternetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkConnectivity.isNetworkAvailable(getActivity())) {
                    offlineLyt.setVisibility(View.GONE);
                    GetLawyersRequest(getContext());
                }

            }
        });
        GetLawyersRequest(getContext());
        return view;
    }

    public void GetLawyersRequest(final Context context){
        class GetLawyersRequestAsync extends AsyncTask<Void ,Void, String> {
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

                    JSONArray lawyers = new JSONArray(reader.getString(Constants.JSON_RESPONSE_DATA));
                    JSONObject lawyerItem;
                    if (reader.getInt(Constants.JSON_RESPONSE_STATE)==1)
                    {
                        ArrayList<LawyerModel> lawyerModels=new ArrayList<LawyerModel>();
                        for(int i=0; i < lawyers.length();i++)
                        {
                            lawyerItem = lawyers.getJSONObject(i);
                            LawyerModel lawyerModel = new LawyerModel(
                                    lawyerItem.getString(Constants.LAWYER_ID),
                                    lawyerItem.getString(Constants.LAWYER_FULL_NAME),
                                    lawyerItem.getString(Constants.LAWYER_IMAGE),
                                    lawyerItem.getString(Constants.LAWYER_DESCRIPTION),
                                    lawyerItem.getString(Constants.LAWYER_DISABLED),
                                    lawyerItem.getString(Constants.LAWYER_PHONE),
                                    lawyerItem.getString(Constants.LAWYER_USER_ID),
                                    lawyerItem.getString(Constants.LAWYER_CREATED_AT)
                            );

                            lawyerModels.add(lawyerModel);
                        }
                        recyclerView.setAdapter(new LawyersRecyclerViewAdapter(lawyerModels,getActivity()));
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
                return httpRequestHandlre.sendGetRequest(Urls.getBaseURL()+Urls.getLawyers(),params);
            }
        }
        GetLawyersRequestAsync getLawyersRequestAsync = new GetLawyersRequestAsync();
        getLawyersRequestAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR , null);
    }

}