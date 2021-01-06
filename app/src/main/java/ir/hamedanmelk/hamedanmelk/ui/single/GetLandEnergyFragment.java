package ir.hamedanmelk.hamedanmelk.ui.single;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.micro.EnergyTypeModel;
import ir.hamedanmelk.hamedanmelk.recyclers.LandEnergyAdapter;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.HTTPRequestHandlre;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GetLandEnergyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GetLandEnergyFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "GetLandEnergy";
    public String landID;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    GridView landEnergyGridView;

    public GetLandEnergyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GetLandEnergyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GetLandEnergyFragment newInstance(String param1, String param2) {
        GetLandEnergyFragment fragment = new GetLandEnergyFragment();
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
        View view =inflater.inflate(R.layout.fragment_get_land_energy, container, false);
        landEnergyGridView = (GridView)view.findViewById(R.id.SinglesLandEnergyGridView);
        GetLandEnergyRequest(getContext(),landID);
        return view;
    }

    public void GetLandEnergyRequest(final Context context, final String landId){
        class GetLandEnergyRequestAsync extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                ArrayList<EnergyTypeModel> energyTypeModels = new ArrayList<EnergyTypeModel>() ;
                try {
                    JSONObject reader = new JSONObject(s);
                    Log.d(TAG, "onPostExecute: response: "+s.toString());
                    if(reader.getInt(Constants.JSON_RESPONSE_STATE)==1){
                        JSONArray responseList = new JSONArray(reader.getString(Constants.JSON_RESPONSE_DATA));
                        EnergyTypeModel energyTypeModel ;
                        JSONObject responseItem;
                        for(int i =0; i<responseList.length();i++){
                            responseItem = responseList.getJSONObject(i);
                            energyTypeModel = new EnergyTypeModel(
                                    responseItem.getString(Constants.ENERGY_LAND_ID),
                                    responseItem.getString(Constants.ENERGY_TYPE_ID),
                                    responseItem.getString(Constants.ENERGY_STATE_ID),
                                    responseItem.getString(Constants.ENERGY_TYPE_TITLE),
                                    responseItem.getString(Constants.ENERGY_STATE_TITLE)
                            );
                            energyTypeModels.add(energyTypeModel);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(TAG, "onPostExecute exception:"+e.toString());
                }
                LandEnergyAdapter landEnergyAdapter = new LandEnergyAdapter(energyTypeModels,context,getActivity());
                landEnergyGridView.setAdapter(landEnergyAdapter);
            }
            @Override
            protected String doInBackground(Void... voids) {
                HTTPRequestHandlre httpRequestHandlre = new HTTPRequestHandlre();
                HashMap<String, String> params = new HashMap<>();
                params.put(Constants.CONTENT_TYPE,Constants.APPLICATION_JSON);
                params.put("LID",landId);
                return httpRequestHandlre.sendPostRequest(Urls.getBaseURL()+Urls.getGetLandEnergy(),params);
            }
        }
        GetLandEnergyRequestAsync getLandEnergyRequest = new GetLandEnergyRequestAsync();
        getLandEnergyRequest.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR , null);

    }

}