package ir.hamedanmelk.hamedanmelk.ui.category;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.micro.LinksModel;
import ir.hamedanmelk.hamedanmelk.models.micro.NewsModel;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.HTTPRequestHandlre;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

/**
 * A simple {@News Fragment} subclass.
 */
public class NewsFragment extends Fragment {
private static final String TAG = "NewsFragment";
GridView newsGridView;
    public NewsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        newsGridView = (GridView)view.findViewById(R.id.NewsFragmentGridView);
        GetNewsRequest(getContext());
        return view;
    }

    public void GetNewsRequest(final Context context){
        class GetNewsRequestAsync extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                ArrayList<NewsModel> newsModels = new ArrayList<NewsModel>() ;
                try {
                    JSONObject reader = new JSONObject(s);
                    Log.d(TAG, "onPostExecute response: "+s.toString());
                    if(reader.getInt(Constants.JSON_RESPONSE_STATE)==1){
                        Log.d(TAG, "onPostExecute: enter");
                        JSONObject responseList = new JSONObject(reader.getString(Constants.JSON_RESPONSE_DATA));
                        JSONArray newsList = new JSONArray(responseList.getString("data"));

                        NewsModel newsModel ;
                        JSONObject responseItem;
                        for(int i =0; i<responseList.length();i++){
                            responseItem = newsList.getJSONObject(i);
                            newsModel = new NewsModel(
                                    responseItem.getString("id"),
                                    responseItem.getString("Title"),
                                    responseItem.getString("Image")
                            );
                            newsModels.add(newsModel);
                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(TAG, "onPostExecute exception:"+e.toString());
                }
                NewsItemAdapter newsItemAdapter = new NewsItemAdapter(newsModels,getContext());
                newsGridView.setAdapter(newsItemAdapter);
            }
            @Override
            protected String doInBackground(Void... voids) {
                HTTPRequestHandlre httpRequestHandlre = new HTTPRequestHandlre();
                HashMap<String, String> params = new HashMap<>();
                params.put(Constants.CONTENT_TYPE,Constants.APPLICATION_JSON);
                return httpRequestHandlre.sendGetRequest(Urls.getBaseURL()+Urls.getGetNewsLists(),params);
            }
        }
        GetNewsRequestAsync getLinksRequestAsync = new GetNewsRequestAsync();
        getLinksRequestAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR , null);
    }
}