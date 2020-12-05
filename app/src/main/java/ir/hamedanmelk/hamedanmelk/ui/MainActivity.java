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
        dbHelper.GetProvinceByID("2");

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {

                if (arguments != null) {
                    if (arguments.getBoolean("hidenavigation")) {
                        navView.setVisibility(View.GONE);
                    }
                } else
                {
                    navView.setVisibility(View.VISIBLE);
                }
                if(destination.getId() == R.id.navigation_home){
                    ActionBar actionBar=getSupportActionBar();
                    actionBar.setDisplayHomeAsUpEnabled(false);
                    actionBar.setDisplayShowTitleEnabled(false);
                }
                else {
                    ActionBar actionBar=getSupportActionBar();
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



    public void GetProvinceRequest (Context context) {

        class GetProvinceRequestAsync extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {
                HTTPRequestHandlre requestHandler = new HTTPRequestHandlre();
                HashMap<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                return requestHandler.sendGetRequest(Urls.getBaseURL()+Urls.getGetProvinces(), params);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONObject rawResult = new JSONObject(s);
                    if (rawResult.getInt("State")>0) {
                        JSONArray dataResult = rawResult.getJSONArray("Data");
                        String[] provinceFields = Constants.PROVINCE_MODEL_FIELDS;
                        dbHelper.DeleteProvinces();
                        for(int i=0; i<dataResult.length(); i++){
                            JSONObject rowItem = dataResult.getJSONObject(i);
                            ContentValues itemCV=new ContentValues();
                            for (String columnItem : provinceFields) {
                                itemCV.put(columnItem, rowItem.getString(columnItem));
                            }
                            dbHelper.InsertProvinces(itemCV);
                        }
                    }
                } catch (JSONException e) {
                    Log.d(TAG, "onPostExecute: "+e.toString());
                }
            }
        }
        GetProvinceRequestAsync getProvinceRequestAsync = new GetProvinceRequestAsync();
        getProvinceRequestAsync.execute();
    }

}