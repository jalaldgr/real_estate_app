package ir.hamedanmelk.hamedanmelk.ui;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.LandModel;
import ir.hamedanmelk.hamedanmelk.recyclers.HomeRecyclerViewAdapter;
import ir.hamedanmelk.hamedanmelk.recyclers.HomeVerticalRecyclerViewAdapter;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.HTTPRequestHandlre;
import ir.hamedanmelk.hamedanmelk.tools.MYSQlDBHelper;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

public class Splash_Ads extends AppCompatActivity {
    Boolean clicked;
    static final String TAG = "SplashAds";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__ads);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        clicked=false;
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.SplashAdsMainLeanerLayout);
        TotalLandRequest(getApplicationContext());

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent i =new Intent(Splash_Ads.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        },750);

    }


    public void TotalLandRequest(final Context context){
        class TotalLandRequestAsync extends AsyncTask<Void ,Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                MYSQlDBHelper qlDBHelper = new MYSQlDBHelper(getApplicationContext());
                try {
                    JSONObject reader = new JSONObject(s);
                    JSONObject ResponseData = new JSONObject(reader.getString(Constants.JSON_RESPONSE_DATA));
                    JSONArray LandList = new JSONArray(ResponseData.getString("data"));
                    JSONObject LandItem;
                    JSONArray imagesArray;
                    if (reader.getInt(Constants.JSON_RESPONSE_STATE)==1)
                    {
                        qlDBHelper.DeleteLand();
                        for(int i=0; i < LandList.length();i++)
                        {
                            LandItem = LandList.getJSONObject(i);
                            imagesArray =new JSONArray( LandItem.getString(Constants.SALE_MODEL_IMAGES));
                            ContentValues itemCV = new ContentValues();

                            itemCV.put(Constants.LAND_MODEL_ID,LandItem.getString(Constants.LAND_MODEL_ID));
                            itemCV.put(Constants.LAND_MODEL_TOTAL_PRICE,LandItem.getString(Constants.LAND_MODEL_TOTAL_PRICE));
                            itemCV.put(Constants.LAND_MODEL_TOTAL_MORTGAGE_PRICE,LandItem.getString(Constants.LAND_MODEL_TOTAL_MORTGAGE_PRICE));
                            itemCV.put(Constants.LAND_MODEL_TOTAL_RENT_PRICE,LandItem.getString(Constants.LAND_MODEL_TOTAL_RENT_PRICE));
                            itemCV.put(Constants.LAND_MODEL_TITLE,LandItem.getString(Constants.LAND_MODEL_TITLE));
                            itemCV.put(Constants.LAND_MODEL_LAND_STATE_ID,LandItem.getString(Constants.LAND_MODEL_LAND_STATE_ID));
                            itemCV.put(Constants.LAND_MODEL_CREATED_AT,LandItem.getString(Constants.LAND_MODEL_CREATED_AT));
                            itemCV.put(Constants.LAND_MODEL_LAND_SITUATION_ID,LandItem.getString(Constants.LAND_MODEL_LAND_SITUATION_ID));
                            itemCV.put("LView",LandItem.getString(Constants.LAND_MODEL_VIEW));
                            itemCV.put(Constants.LAND_MODEL_IMAGES,imagesArray.get(0).toString());
                            itemCV.put(Constants.LAND_MODEL_LANDSTATETITLE,LandItem.getString(Constants.LAND_MODEL_LANDSTATETITLE));
                            itemCV.put(Constants.USER_LAND_MODEL_DISTRICT_ID,LandItem.getString(Constants.USER_LAND_MODEL_DISTRICT_ID));
                            itemCV.put(Constants.LAND_MODEL_LANDSITUATIONTITLE,LandItem.getString(Constants.LAND_MODEL_LANDSITUATIONTITLE));
                            itemCV.put(Constants.LAND_MODEL_LANDSITUATIONCOLOR,LandItem.getString(Constants.LAND_MODEL_LANDSITUATIONCOLOR));
                            itemCV.put(Constants.LAND_MODEL_FIRST_NAME,LandItem.getString(Constants.LAND_MODEL_FIRST_NAME));
                            itemCV.put(Constants.LAND_MODEL_LAST_NAME,LandItem.getString(Constants.LAND_MODEL_LAST_NAME));
                            itemCV.put(Constants.LAND_MODEL_LAND_CASE_ID,LandItem.getString(Constants.LAND_MODEL_LAND_CASE_ID));

                            qlDBHelper.InsertLand(itemCV);
                        }


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
                return httpRequestHandlre.sendGetRequest(Urls.getBaseURL()+Urls.getTotalLands(),params);
            }
        }
        TotalLandRequestAsync totalRentRequestAsync = new TotalLandRequestAsync();
        totalRentRequestAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR , null);
    }



}