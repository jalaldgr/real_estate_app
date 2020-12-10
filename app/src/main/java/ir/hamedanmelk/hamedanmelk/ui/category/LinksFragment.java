package ir.hamedanmelk.hamedanmelk.ui.category;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.constraintlayout.widget.Constraints;
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
import ir.hamedanmelk.hamedanmelk.models.micro.LinksModel;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.HTTPRequestHandlre;
import ir.hamedanmelk.hamedanmelk.tools.Urls;
import ir.hamedanmelk.hamedanmelk.ui.category.LinksItemAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class LinksFragment extends Fragment {
private static final String TAG = "LinksFragment";
GridView linksGridView;
    public LinksFragment() {
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
        View view = inflater.inflate(R.layout.fragment_links, container, false);
        linksGridView = (GridView)view.findViewById(R.id.LinksFragmentGridView);
        GetLinksRequest(getContext());
        return view;
    }

    public void GetLinksRequest(final Context context){
        class GetLinksRequestAsync extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                ArrayList<LinksModel> linksModels = new ArrayList<LinksModel>() ;
                try {
                    JSONObject reader = new JSONObject(s);
                    Log.d(TAG, "onPostExecute response: "+s.toString());
                    if(reader.getInt(Constants.JSON_RESPONSE_STATE)==1){
                        Log.d(TAG, "onPostExecute: enter");
                        JSONArray responseList = new JSONArray(reader.getString(Constants.JSON_RESPONSE_DATA));
                        LinksModel linksModel ;
                        JSONObject responseItem;
                        for(int i =0; i<responseList.length();i++){
                            responseItem = responseList.getJSONObject(i);
                            linksModel = new LinksModel(
                                    responseItem.getString(Constants.LINKS_ID),
                                    responseItem.getString(Constants.LINKS_TITLE),
                                    responseItem.getString(Constants.LINKS_lINK),
                                    responseItem.getString(Constants.LINKS_LOGO),
                                    responseItem.getString(Constants.LINKS_ORDER),
                                    responseItem.getString(Constants.LINKS_DISABLED)
                            );
                            linksModels.add(linksModel);
                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(TAG, "onPostExecute exception:"+e.toString());
                }
                LinksItemAdapter linksItemAdapter = new LinksItemAdapter(linksModels,getContext());
                linksGridView.setAdapter(linksItemAdapter);
            }
            @Override
            protected String doInBackground(Void... voids) {
                HTTPRequestHandlre httpRequestHandlre = new HTTPRequestHandlre();
                HashMap<String, String> params = new HashMap<>();
                params.put(Constants.CONTENT_TYPE,Constants.APPLICATION_JSON);
                return httpRequestHandlre.sendGetRequest(Urls.getBaseURL()+Urls.getGetLinks(),params);
            }
        }
        GetLinksRequestAsync getLinksRequestAsync = new GetLinksRequestAsync();
        getLinksRequestAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR , null);
    }
}