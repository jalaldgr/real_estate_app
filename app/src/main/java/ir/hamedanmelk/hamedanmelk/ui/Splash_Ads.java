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
    static final String TAG = "SplashAds";
    MYSQlDBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_app_info_fullscreen);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        dbHelper = new MYSQlDBHelper(getApplicationContext());


        //Get all lands
        TotalLandRequest(getApplicationContext());
        //Get required goods for Search Filter
        GetLandStatesRequest(getApplicationContext());
        GetDistrictRequest(getApplicationContext());
        GetCitiesRequest(getApplicationContext());
        GetAreasRequest(getApplicationContext());



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

    public void GetLandStatesRequest(Context context) {

        class GetLandStateRequestAsync extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {
                HTTPRequestHandlre requestHandler = new HTTPRequestHandlre();
                HashMap<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                return requestHandler.sendGetRequest(Urls.getBaseURL() + Urls.getGetLandStates(), params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONObject rawResult = new JSONObject(s);
                    if (rawResult.getInt("State") > 0) {
                        JSONArray dataResult = rawResult.getJSONArray("Data");
                        String[] modelFields = Constants.LAND_STATES_MODEL_FIELDS;
                        dbHelper.DeleteLandStates();
                        for (int i = 0; i < dataResult.length(); i++) {
                            JSONObject rowItem = dataResult.getJSONObject(i);
                            ContentValues itemCV = new ContentValues();
                            for (String columnItem : modelFields) {
                                itemCV.put(columnItem, rowItem.getString(columnItem));
                            }
                            dbHelper.InsertLandState(itemCV);
                        }
                    }
                } catch (JSONException e) {
                    Log.d(TAG, "onPostExecuteLandState: " + e.toString());
                }
            }
        }
        GetLandStateRequestAsync getLandStateRequestAsync = new GetLandStateRequestAsync();
        getLandStateRequestAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR , null);
    }


    public void GetDistrictRequest(Context context) {

        class GetDistrictRequestAsync extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {
                HTTPRequestHandlre requestHandler = new HTTPRequestHandlre();
                HashMap<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                return requestHandler.sendGetRequest(Urls.getBaseURL() + Urls.getGetDistricts(), params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONObject rawResult = new JSONObject(s);
                    if (rawResult.getInt("State") > 0) {
                        JSONArray dataResult = rawResult.getJSONArray("Data");
                        String[] modelFields = Constants.DISTRICT_MODEL_FIELDS;
                        dbHelper.DeleteDistrict();
                        for (int i = 0; i < dataResult.length(); i++) {
                            JSONObject rowItem = dataResult.getJSONObject(i);
                            ContentValues itemCV = new ContentValues();
                            for (String columnItem : modelFields) {
                                itemCV.put(columnItem, rowItem.getString(columnItem));
                            }
                            dbHelper.InsertDistrict(itemCV);
                        }
                    }
                } catch (JSONException e) {
                    Log.d(TAG, "onPostExecute: " + e.toString());
                }
            }
        }
        GetDistrictRequestAsync getDistrictRequestAsync = new GetDistrictRequestAsync();
        getDistrictRequestAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR , null);
    }

    public void GetCitiesRequest(Context context) {

        class GetCitiesRequestAsync extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {
                HTTPRequestHandlre requestHandler = new HTTPRequestHandlre();
                HashMap<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                return requestHandler.sendGetRequest(Urls.getBaseURL() + Urls.getGetCities(), params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONObject rawResult = new JSONObject(s);
                    if (rawResult.getInt("State") > 0) {
                        JSONArray dataResult = rawResult.getJSONArray("Data");
                        String[] modelFields = Constants.CITIES_MODEL_FIELDS;
                        dbHelper.DeleteCities();
                        for (int i = 0; i < dataResult.length(); i++) {
                            JSONObject rowItem = dataResult.getJSONObject(i);
                            ContentValues itemCV = new ContentValues();
                            for (String columnItem : modelFields) {
                                itemCV.put(columnItem, rowItem.getString(columnItem));
                            }
                            dbHelper.InsertCity(itemCV);
                        }
                    }
                } catch (JSONException e) {
                    Log.d(TAG, "onPostExecute cities: " + e.toString());
                }
            }
        }
        GetCitiesRequestAsync getCitiesRequestAsync = new GetCitiesRequestAsync();
        getCitiesRequestAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR , null);
    }

    public void GetAreasRequest(Context context) {

        class GetAreasRequestAsync extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {
                HTTPRequestHandlre requestHandler = new HTTPRequestHandlre();
                HashMap<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                return requestHandler.sendGetRequest(Urls.getBaseURL() + Urls.getGetAreas(), params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONObject rawResult = new JSONObject(s);
                    if (rawResult.getInt("State") > 0) {
                        JSONArray dataResult = rawResult.getJSONArray("Data");
                        String[] modelFields = Constants.AREAS_MODEL_FIELDS;
                        dbHelper.DeleteAreas();
                        for (int i = 0; i < dataResult.length(); i++) {
                            JSONObject rowItem = dataResult.getJSONObject(i);
                            ContentValues itemCV = new ContentValues();
                            for (String columnItem : modelFields) {
                                itemCV.put(columnItem, rowItem.getString(columnItem));
                            }
                            dbHelper.InsertArea(itemCV);
                        }
                    }
                } catch (JSONException e) {
                    Log.d(TAG, "onPostExecuteArea: " + e.toString());
                }

                //All Async Finished Here
                Intent i =new Intent(Splash_Ads.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }
        GetAreasRequestAsync getAreasRequestAsync = new GetAreasRequestAsync();
        getAreasRequestAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR , null);
    }
    //All Async Finished GetAreasRequestAsync and start next activity

}