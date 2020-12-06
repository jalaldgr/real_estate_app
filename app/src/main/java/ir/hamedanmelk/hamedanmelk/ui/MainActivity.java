package ir.hamedanmelk.hamedanmelk.ui;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavAction;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.HTTPRequestHandlre;
import ir.hamedanmelk.hamedanmelk.tools.MYSQlDBHelper;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    MYSQlDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        final BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        final AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder()
                .build();
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        dbHelper = new MYSQlDBHelper(getApplicationContext());
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        GetProvinceRequest(getApplicationContext());
        GetDistrictRequest(getApplicationContext());
        GetCitiesRequest(getApplicationContext());
        GetAreasRequest(getApplicationContext());
        GetLandTypeRequest(getApplicationContext());
        GetBuildingConditionsRequest(getApplicationContext());
        GetLandStatesRequest(getApplicationContext());
        GetRentalPreferenceRequest(getApplicationContext());
        GetDensityTypesRequest(getApplicationContext());
        GetFloorCoveringsRequest(getApplicationContext());
        GetKitchenServicesRequest(getApplicationContext());
        GetLandCaseRequest(getApplicationContext());
        GetLoanTypesRequest(getApplicationContext());
        GetLandSituationsRequest(getApplicationContext());
        GetLandViewsRequest(getApplicationContext());
        GetLandDirectionsRequest(getApplicationContext());

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {

                if (arguments != null) {
                    if (arguments.getBoolean("hidenavigation")) {
                        navView.setVisibility(View.GONE);
                    }
                } else {
                    navView.setVisibility(View.VISIBLE);
                }
                if (destination.getId() == R.id.navigation_home) {
                    ActionBar actionBar = getSupportActionBar();
                    actionBar.setDisplayHomeAsUpEnabled(false);
                    actionBar.setDisplayShowTitleEnabled(false);
                } else {
                    ActionBar actionBar = getSupportActionBar();
                    actionBar.setDisplayHomeAsUpEnabled(true);
                    actionBar.setDisplayShowTitleEnabled(true);
                }
            }
        });
        navController.removeOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }


    public void GetProvinceRequest(Context context) {

        class GetProvinceRequestAsync extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {
                HTTPRequestHandlre requestHandler = new HTTPRequestHandlre();
                HashMap<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                return requestHandler.sendGetRequest(Urls.getBaseURL() + Urls.getGetProvinces(), params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONObject rawResult = new JSONObject(s);
                    if (rawResult.getInt("State") > 0) {
                        JSONArray dataResult = rawResult.getJSONArray("Data");
                        String[] provinceFields = Constants.PROVINCE_MODEL_FIELDS;
                        dbHelper.DeleteProvinces();
                        for (int i = 0; i < dataResult.length(); i++) {
                            JSONObject rowItem = dataResult.getJSONObject(i);
                            ContentValues itemCV = new ContentValues();
                            for (String columnItem : provinceFields) {
                                itemCV.put(columnItem, rowItem.getString(columnItem));
                            }
                            dbHelper.InsertProvinces(itemCV);
                        }
                    }
                } catch (JSONException e) {
                    Log.d(TAG, "onPostExecute: " + e.toString());
                }
            }
        }
        GetProvinceRequestAsync getProvinceRequestAsync = new GetProvinceRequestAsync();
        getProvinceRequestAsync.execute();
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
        getDistrictRequestAsync.execute();
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
        getCitiesRequestAsync.execute();
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
            }
        }
        GetAreasRequestAsync getAreasRequestAsync = new GetAreasRequestAsync();
        getAreasRequestAsync.execute();
    }

    public void GetLandTypeRequest(Context context) {

        class GetLandTypeRequestAsync extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {
                HTTPRequestHandlre requestHandler = new HTTPRequestHandlre();
                HashMap<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                return requestHandler.sendGetRequest(Urls.getBaseURL() + Urls.getGetLandTypes(), params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONObject rawResult = new JSONObject(s);
                    if (rawResult.getInt("State") > 0) {
                        JSONArray dataResult = rawResult.getJSONArray("Data");
                        String[] modelFields = Constants.LAND_TYPE_MODEL_FIELDS;
                        dbHelper.DeleteLandType();
                        for (int i = 0; i < dataResult.length(); i++) {
                            JSONObject rowItem = dataResult.getJSONObject(i);
                            ContentValues itemCV = new ContentValues();
                            for (String columnItem : modelFields) {
                                itemCV.put(columnItem, rowItem.getString(columnItem));
                            }
                            dbHelper.InsertLandType(itemCV);
                        }
                    }
                } catch (JSONException e) {
                    Log.d(TAG, "onPostExecuteLandType: " + e.toString());
                }
            }
        }
        GetLandTypeRequestAsync getLandTypeRequestAsync = new GetLandTypeRequestAsync();
        getLandTypeRequestAsync.execute();
    }

    public void GetBuildingConditionsRequest(Context context) {

        class GetBuildingConditionsRequestAsync extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {
                HTTPRequestHandlre requestHandler = new HTTPRequestHandlre();
                HashMap<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                return requestHandler.sendGetRequest(Urls.getBaseURL() + Urls.getGetBuildingConditions(), params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONObject rawResult = new JSONObject(s);
                    if (rawResult.getInt("State") > 0) {
                        JSONArray dataResult = rawResult.getJSONArray("Data");
                        String[] modelFields = Constants.BUILDING_CONDITIONS_MODEL_FIELDS;
                        dbHelper.DeleteBuildingConditions();
                        for (int i = 0; i < dataResult.length(); i++) {
                            JSONObject rowItem = dataResult.getJSONObject(i);
                            ContentValues itemCV = new ContentValues();
                            for (String columnItem : modelFields) {
                                itemCV.put(columnItem, rowItem.getString(columnItem));
                            }
                            dbHelper.InsertBuildingCondition(itemCV);
                        }
                    }
                } catch (JSONException e) {
                    Log.d(TAG, "onPostExecute BuildingConds: " + e.toString());
                }
            }
        }
        GetBuildingConditionsRequestAsync getBuildingConditionsRequestAsync = new GetBuildingConditionsRequestAsync();
        getBuildingConditionsRequestAsync.execute();
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
        getLandStateRequestAsync.execute();
    }

    public void GetRentalPreferenceRequest(Context context) {

        class GetRentalPreferenceRequestAsync extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {
                HTTPRequestHandlre requestHandler = new HTTPRequestHandlre();
                HashMap<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                return requestHandler.sendGetRequest(Urls.getBaseURL() + Urls.getGetRentalPreferences(), params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONObject rawResult = new JSONObject(s);
                    if (rawResult.getInt("State") > 0) {
                        JSONArray dataResult = rawResult.getJSONArray("Data");
                        String[] modelFields = Constants.RENTAL_PREFERENCES_MODEL_FIELDS;
                        dbHelper.DeleteRentalPreference();
                        for (int i = 0; i < dataResult.length(); i++) {
                            JSONObject rowItem = dataResult.getJSONObject(i);
                            ContentValues itemCV = new ContentValues();
                            for (String columnItem : modelFields) {
                                itemCV.put(columnItem, rowItem.getString(columnItem));
                            }
                            dbHelper.InsertRentalPreference(itemCV);
                        }
                    }
                } catch (JSONException e) {
                    Log.d(TAG, "onPostExecute RentalPref: " + e.toString());
                }
            }
        }
        GetRentalPreferenceRequestAsync getRentalPreferenceRequestAsync = new GetRentalPreferenceRequestAsync();
        getRentalPreferenceRequestAsync.execute();
    }

    public void GetDensityTypesRequest(Context context) {

        class GetDensityTypeRequestAsync extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {
                HTTPRequestHandlre requestHandler = new HTTPRequestHandlre();
                HashMap<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                return requestHandler.sendGetRequest(Urls.getBaseURL() + Urls.getGetDensityTypes(), params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONObject rawResult = new JSONObject(s);
                    if (rawResult.getInt("State") > 0) {
                        JSONArray dataResult = rawResult.getJSONArray("Data");
                        String[] modelFields = Constants.DENSITY_TYPES_MODEL_FIELDS;
                        dbHelper.DeleteDensityTypes();
                        for (int i = 0; i < dataResult.length(); i++) {
                            JSONObject rowItem = dataResult.getJSONObject(i);
                            ContentValues itemCV = new ContentValues();
                            for (String columnItem : modelFields) {
                                itemCV.put(columnItem, rowItem.getString(columnItem));
                            }
                            dbHelper.InsertDensityType(itemCV);
                        }
                    }
                } catch (JSONException e) {
                    Log.d(TAG, "onPostExecute Density: " + e.toString());
                }
            }
        }
        GetDensityTypeRequestAsync getDensityTypeRequestAsync = new GetDensityTypeRequestAsync();
        getDensityTypeRequestAsync.execute();
    }

    public void GetFloorCoveringsRequest(Context context) {

        class GetFloorCoveringRequestAsync extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {
                HTTPRequestHandlre requestHandler = new HTTPRequestHandlre();
                HashMap<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                return requestHandler.sendGetRequest(Urls.getBaseURL() + Urls.getGetFloorCoverings(), params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONObject rawResult = new JSONObject(s);
                    if (rawResult.getInt("State") > 0) {
                        JSONArray dataResult = rawResult.getJSONArray("Data");
                        String[] modelFields = Constants.FLOOR_COVERINGS_MODEL_FIELDS;
                        dbHelper.DeleteFloorCoverings();
                        for (int i = 0; i < dataResult.length(); i++) {
                            JSONObject rowItem = dataResult.getJSONObject(i);
                            ContentValues itemCV = new ContentValues();
                            for (String columnItem : modelFields) {
                                itemCV.put(columnItem, rowItem.getString(columnItem));
                            }
                            dbHelper.InsertFloorCovering(itemCV);
                        }
                    }
                } catch (JSONException e) {
                    Log.d(TAG, "onPostExecute FloorCoverings: " + e.toString());
                }
            }
        }
        GetFloorCoveringRequestAsync getDistrictRequestAsync = new GetFloorCoveringRequestAsync();
        getDistrictRequestAsync.execute();
    }

    public void GetKitchenServicesRequest(Context context) {

        class GetKitchenServicesRequestAsync extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {
                HTTPRequestHandlre requestHandler = new HTTPRequestHandlre();
                HashMap<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                return requestHandler.sendGetRequest(Urls.getBaseURL() + Urls.getGetKitchenServices(), params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONObject rawResult = new JSONObject(s);
                    if (rawResult.getInt("State") > 0) {
                        JSONArray dataResult = rawResult.getJSONArray("Data");
                        String[] modelFields = Constants.KITCHEN_SERVICES_MODEL_FIELDS;
                        dbHelper.DeleteKitchenServices();
                        for (int i = 0; i < dataResult.length(); i++) {
                            JSONObject rowItem = dataResult.getJSONObject(i);
                            ContentValues itemCV = new ContentValues();
                            for (String columnItem : modelFields) {
                                itemCV.put(columnItem, rowItem.getString(columnItem));
                            }
                            dbHelper.InsertKitchenServices(itemCV);
                        }
                    }
                } catch (JSONException e) {
                    Log.d(TAG, "onPostExecute KitchenServices: " + e.toString());
                }
            }
        }
        GetKitchenServicesRequestAsync getDistrictRequestAsync = new GetKitchenServicesRequestAsync();
        getDistrictRequestAsync.execute();
    }

    public void GetLandCaseRequest(Context context) {

        class GetLandCaseRequestAsync extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {
                HTTPRequestHandlre requestHandler = new HTTPRequestHandlre();
                HashMap<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                return requestHandler.sendGetRequest(Urls.getBaseURL() + Urls.getGetLandCases(), params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONObject rawResult = new JSONObject(s);
                    if (rawResult.getInt("State") > 0) {
                        JSONArray dataResult = rawResult.getJSONArray("Data");
                        String[] modelFields = Constants.LAND_CASE_MODEL_FIELDS;
                        dbHelper.DeleteLandCaseTypes();
                        for (int i = 0; i < dataResult.length(); i++) {
                            JSONObject rowItem = dataResult.getJSONObject(i);
                            ContentValues itemCV = new ContentValues();
                            for (String columnItem : modelFields) {
                                itemCV.put(columnItem, rowItem.getString(columnItem));
                            }
                            dbHelper.InsertLandCaseType(itemCV);
                        }
                    }
                } catch (JSONException e) {
                    Log.d(TAG, "onPostExecute LandCase: " + e.toString());
                }
            }
        }
        GetLandCaseRequestAsync getLandCaseRequestAsync = new GetLandCaseRequestAsync();
        getLandCaseRequestAsync.execute();
    }

    public void GetLoanTypesRequest(Context context) {

        class GetLoanTypesRequestAsync extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {
                HTTPRequestHandlre requestHandler = new HTTPRequestHandlre();
                HashMap<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                return requestHandler.sendGetRequest(Urls.getBaseURL() + Urls.getGetLoanTypes(), params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONObject rawResult = new JSONObject(s);
                    if (rawResult.getInt("State") > 0) {
                        JSONArray dataResult = rawResult.getJSONArray("Data");
                        String[] modelFields = Constants.LOAN_TYPES_MODEL_FIELDS;
                        dbHelper.DeleteLoanTypes();
                        for (int i = 0; i < dataResult.length(); i++) {
                            JSONObject rowItem = dataResult.getJSONObject(i);
                            ContentValues itemCV = new ContentValues();
                            for (String columnItem : modelFields) {
                                itemCV.put(columnItem, rowItem.getString(columnItem));
                            }
                            dbHelper.InsertLoanType(itemCV);
                        }
                    }
                } catch (JSONException e) {
                    Log.d(TAG, "onPostExecute LoanType: " + e.toString());
                }
            }
        }
        GetLoanTypesRequestAsync getDistrictRequestAsync = new GetLoanTypesRequestAsync();
        getDistrictRequestAsync.execute();
    }

    public void GetLandSituationsRequest(Context context) {

        class GetLandSituationsRequestAsync extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {
                HTTPRequestHandlre requestHandler = new HTTPRequestHandlre();
                HashMap<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                return requestHandler.sendGetRequest(Urls.getBaseURL() + Urls.getGetLandSituations(), params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONObject rawResult = new JSONObject(s);
                    if (rawResult.getInt("State") > 0) {
                        JSONArray dataResult = rawResult.getJSONArray("Data");
                        String[] modelFields = Constants.LAND_SITUATIONS_MODEL_FIELDS;
                        dbHelper.DeleteLandSituations();
                        for (int i = 0; i < dataResult.length(); i++) {
                            JSONObject rowItem = dataResult.getJSONObject(i);
                            ContentValues itemCV = new ContentValues();
                            for (String columnItem : modelFields) {
                                itemCV.put(columnItem, rowItem.getString(columnItem));
                            }
                            dbHelper.InsertLandSituation(itemCV);
                        }
                    }
                } catch (JSONException e) {
                    Log.d(TAG, "onPostExecute Land Situation: " + e.toString());
                }
            }
        }
        GetLandSituationsRequestAsync getLandSituationsRequestAsync = new GetLandSituationsRequestAsync();
        getLandSituationsRequestAsync.execute();
    }

    public void GetLandViewsRequest(Context context) {

        class GetLandViewsRequestAsync extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {
                HTTPRequestHandlre requestHandler = new HTTPRequestHandlre();
                HashMap<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                return requestHandler.sendGetRequest(Urls.getBaseURL() + Urls.getGetLandViews(), params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONObject rawResult = new JSONObject(s);
                    if (rawResult.getInt("State") > 0) {
                        JSONArray dataResult = rawResult.getJSONArray("Data");
                        String[] modelFields = Constants.LAND_VIEWS_MODEL_FIELDS;
                        dbHelper.DeleteLandViews();
                        for (int i = 0; i < dataResult.length(); i++) {
                            JSONObject rowItem = dataResult.getJSONObject(i);
                            ContentValues itemCV = new ContentValues();
                            for (String columnItem : modelFields) {
                                itemCV.put(columnItem, rowItem.getString(columnItem));
                            }
                            dbHelper.InsertLandView(itemCV);
                        }
                    }
                } catch (JSONException e) {
                    Log.d(TAG, "onPostExecute LandViews: " + e.toString());
                }
            }
        }
        GetLandViewsRequestAsync getDistrictRequestAsync = new GetLandViewsRequestAsync();
        getDistrictRequestAsync.execute();
    }

    public void GetLandDirectionsRequest(Context context) {

        class GetLandDirectionsRequestAsync extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {
                HTTPRequestHandlre requestHandler = new HTTPRequestHandlre();
                HashMap<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                return requestHandler.sendGetRequest(Urls.getBaseURL() + Urls.getGetLandDirections(), params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONObject rawResult = new JSONObject(s);
                    if (rawResult.getInt("State") > 0) {
                        JSONArray dataResult = rawResult.getJSONArray("Data");
                        String[] modelFields = Constants.DISTRICT_MODEL_FIELDS;
                        dbHelper.DeleteLadDirections();
                        for (int i = 0; i < dataResult.length(); i++) {
                            JSONObject rowItem = dataResult.getJSONObject(i);
                            ContentValues itemCV = new ContentValues();
                            for (String columnItem : modelFields) {
                                itemCV.put(columnItem, rowItem.getString(columnItem));
                            }
                            dbHelper.InsertLandDirection(itemCV);
                        }
                    }
                } catch (JSONException e) {
                    Log.d(TAG, "onPostExecute Land Directions: " + e.toString());
                }
            }
        }
        GetLandDirectionsRequestAsync getLandDirectionsRequestAsync = new GetLandDirectionsRequestAsync();
        getLandDirectionsRequestAsync.execute();
    }
}