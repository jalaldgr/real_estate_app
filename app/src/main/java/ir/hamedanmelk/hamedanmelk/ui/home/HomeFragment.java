package ir.hamedanmelk.hamedanmelk.ui.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.micro.AreaModel;
import ir.hamedanmelk.hamedanmelk.models.micro.CityModel;
import ir.hamedanmelk.hamedanmelk.models.micro.DistrictModel;
import ir.hamedanmelk.hamedanmelk.models.micro.LandStateTypeModel;
import ir.hamedanmelk.hamedanmelk.recyclers.HomeRecyclerViewAdapter;
import ir.hamedanmelk.hamedanmelk.recyclers.HomeVerticalRecyclerViewAdapter;
import ir.hamedanmelk.hamedanmelk.tools.CheckConnectivity;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.HTTPRequestHandlre;
import ir.hamedanmelk.hamedanmelk.tools.MYSQlDBHelper;
import ir.hamedanmelk.hamedanmelk.tools.Urls;
import ir.hamedanmelk.hamedanmelk.models.LandModel;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    RecyclerView HorizantalrecyclerView;
    RecyclerView VerticalrecyclerView;
    ArrayList<LandModel> landModels;
    ArrayList<LandModel> featuredLandModels;
    MYSQlDBHelper dbHelper;
    TextView featuredTxt;
    CardView cardView;
     Spinner cityFilterSpnr;
     Spinner districtFilterSpnr;
     Spinner landStateFilterSpnr;
     ImageButton submitFilterBtn;
     Button submitSearchBtn;
    Button submitProSearchBtn;
    Button clearFilterBtn;
    LinearLayout mainLyt;
    FrameLayout noInternetFrame;
    Button noInternetBtn;
    EditText searchTxt;
     ImageView bannerImageView;

    ArrayList<CityModel> cityModels;
    List<String> cityTitles= new ArrayList<String>();
    List<String> cityIDs= new ArrayList<String>();
    ArrayAdapter<String> cityAdapter ;

    ArrayList<DistrictModel> districtModels = new ArrayList<>();
    List<String> districtTitles= new ArrayList<String>();
    List<String> districtIDs= new ArrayList<String>();
    ArrayAdapter<String> districtAdapter ;

    ArrayList<LandStateTypeModel> landStateTypeModels;
    List<String> landStateTypeTitles= new ArrayList<String>();
    List<String> landStateTypeIds= new ArrayList<String>();
    ArrayAdapter<String> landStateAdapter ;

    ArrayList<AreaModel>areaModels;

    String searchCityStr="15";
    String searchDistrictStr;
    String searchLandStateStr;
    String searchStr;
    SharedPreferences.Editor editor;
    SharedPreferences pro_filter_pref;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final NavController controller= Navigation.findNavController(Objects.requireNonNull(getActivity()),R.id.nav_host_fragment);
        final CheckConnectivity checkConnectivity = new CheckConnectivity();
        dbHelper = new MYSQlDBHelper(getContext());
        editor = Objects.requireNonNull(getActivity()).getSharedPreferences(getString(R.string.pro_filter_pref), MODE_PRIVATE).edit();
        pro_filter_pref = Objects.requireNonNull(getActivity()).getSharedPreferences(getString(R.string.pro_filter_pref), Context.MODE_PRIVATE);

        featuredTxt = (TextView)root.findViewById(R.id.HomeFragmentFeaturedTxt);
        bannerImageView = (ImageView) root.findViewById(R.id.HomeFragmentGiffffffffffff);
        cityFilterSpnr = (Spinner)root.findViewById(R.id.ActionbarSearchFilterCitySpnr) ;
        districtFilterSpnr =(Spinner)root.findViewById(R.id.ActionbarSearchFilterDistrictSpnr);
        landStateFilterSpnr = (Spinner)root.findViewById(R.id.ActionbarSearchFilterLandStateSpnr);
        submitFilterBtn = (ImageButton)root.findViewById(R.id.ActionbarSearchFilterFilterImgBtn);
        submitSearchBtn = (Button)root.findViewById(R.id.ActionbarSearchFilterSearchBtn);
        submitProSearchBtn = (Button)root.findViewById(R.id.ActionbarSearchFilterProSearchBtn);
        clearFilterBtn = (Button)root.findViewById(R.id.ActionbarSearchFilterClearBtn);
        HorizantalrecyclerView = (RecyclerView) root.findViewById(R.id.HomeFrgmntHrzntlRcyclVw);
        VerticalrecyclerView  = (RecyclerView) root.findViewById(R.id.HomeFrgmntVerticalRcyclVw);
        cardView = (CardView)root.findViewById(R.id.HomeFragmentWebCardView);
        searchTxt = (EditText)root.findViewById(R.id.ActionBarSearchFilterInputTxt);
        mainLyt = (LinearLayout)root.findViewById(R.id.HomeFragmentMainLyt);
        noInternetFrame = (FrameLayout)root.findViewById(R.id.HomeFragmentNoInternetLyt);
        noInternetBtn = (Button)root.findViewById(R.id.no_internet_fragment_button_retry);
        RecyclerView.LayoutManager laymngr =  new LinearLayoutManager(this.getContext());
        HorizantalrecyclerView.setLayoutManager(laymngr);
        RecyclerView.LayoutManager VRLaymngr = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL,false);
        HorizantalrecyclerView.setLayoutManager(laymngr);
        VerticalrecyclerView.setLayoutManager(VRLaymngr);
        landModels = dbHelper.GetAllLands();
        featuredLandModels = dbHelper.GetAllFeatured20Lands();
        HorizantalrecyclerView.setNestedScrollingEnabled(false);
        featuredTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkConnectivity.isNetworkAvailable(getActivity())){
                    noInternetFrame.setVisibility(View.VISIBLE);
                }else {
                    controller.navigate(R.id.featuredLandFragment);
                }
            }
        });

        cityModels = dbHelper.GetCitiesByProvinceID("2");
        cityTitles.clear();cityIDs.clear();
        for(CityModel item : cityModels){
            cityTitles.add(item.getTitle());
            cityIDs.add(item.getId());
        }
        cityAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getContext()), android.R.layout.simple_spinner_item, cityTitles);
        cityFilterSpnr.setAdapter(cityAdapter);
        cityFilterSpnr.setSelection(8);
        cityFilterSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                searchCityStr = cityIDs.get(i);

//////////////////////////set district adapter //////////////////////////////////////
                areaModels = dbHelper.GetAreasByCityID(searchCityStr);
                ArrayList<DistrictModel> tempDistrictModels;

                districtIDs.clear();districtTitles.clear();districtModels.clear();
                for (AreaModel item:areaModels){
                    tempDistrictModels =( dbHelper.GetDistrictsByAreaID(item.getId()));
                    for (DistrictModel  itemByArea : tempDistrictModels){
                        districtModels.add(itemByArea);
                    }

                }
                for (DistrictModel Item : districtModels) {
                    districtTitles.add(Item.getTitle());
                    districtIDs.add(Item.getId());
                }
                districtAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getContext()), android.R.layout.simple_spinner_item, districtTitles);
                districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                districtFilterSpnr.setAdapter(districtAdapter);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ////////////////////// District Spinner////////////////////////////////
        districtFilterSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                searchDistrictStr = districtIDs.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        landStateTypeModels = dbHelper.GetLandStateList();
        for (LandStateTypeModel Item : landStateTypeModels) {
            landStateTypeTitles.add(Item.getTitle());
            landStateTypeIds.add(Item.getId());
        }
        landStateAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getContext()), android.R.layout.simple_spinner_item, landStateTypeTitles);
        landStateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        landStateFilterSpnr.setAdapter(landStateAdapter);
        landStateFilterSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                searchLandStateStr = landStateTypeIds.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        submitFilterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchFilterRequest(getContext());

            }
        });

        submitSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkConnectivity.isNetworkAvailable(getActivity())){
                    noInternetFrame.setVisibility(View.VISIBLE);
                }
                else {


                    if (searchTxt.getText().length() > 3) {
                        searchStr = searchTxt.getText().toString();
                        SearchRequest(getContext());
                    } else {
                        searchTxt.setError(getResources().getString(R.string.search_min_length_error));
                    }
                }
            }
        });

        submitProSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkConnectivity.isNetworkAvailable(getActivity())){
                    noInternetFrame.setVisibility(View.VISIBLE);
                }else {
                    controller.navigate(R.id.proSearchFragment);
                }
            }
        });
        clearFilterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkConnectivity.isNetworkAvailable(getActivity())){
                    noInternetFrame.setVisibility(View.VISIBLE);
                }else {
                    controller.navigate(R.id.navigation_home);
                }
            }
        });
//        Glide.with(getContext()).load(R.drawable.banner_placeholder).into(bannerImageView);
        Glide.with(getContext()).load("file:///android_asset/webview/banner_ad.gif").into(bannerImageView);
        if(!pro_filter_pref.contains("LandTypeID")) {
            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
//                bannerWebView.loadUrl("file:///android_asset/webview/banner.html");
                    HorizantalrecyclerView.setAdapter(new HomeRecyclerViewAdapter(landModels, getActivity()));
                    VerticalrecyclerView.setAdapter(new HomeVerticalRecyclerViewAdapter(featuredLandModels, getActivity()));

                }
            }, 170);
        }
        else {
            ProSearchFilterRequest();
        }

        //////////check internet connectivity goods////////////////
        if(!checkConnectivity.isNetworkAvailable(getActivity())){
            noInternetFrame.setVisibility(View.VISIBLE);
        }

        noInternetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkConnectivity.isNetworkAvailable(getActivity())) {
                    noInternetFrame.setVisibility(View.GONE);
                }
            }
        });

        return root;
    }


    public void SearchFilterRequest(final Context context){
        class SearchFilterRequestAsync extends AsyncTask<Void ,Void, String> {
            private final ProgressDialog progressDialog=new ProgressDialog(context);
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog.setMessage(getResources().getString(R.string.loading_message));
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                featuredLandModels.clear();
                landModels.clear();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (progressDialog.isShowing())progressDialog.dismiss();
                Log.d(TAG, "onPostExecute: "+s);
                try {
                    JSONObject reader = new JSONObject(s);
                    JSONArray LandList = new JSONArray(reader.getString(Constants.JSON_RESPONSE_DATA));
                    JSONObject LandItem;
                    JSONArray imagesArray;
                    if (LandList.length()==0){
                        Toast.makeText(getContext(),getResources().getString(R.string.search_filter_no_item),Toast.LENGTH_LONG).show();
                    }
                    if (reader.getInt(Constants.JSON_RESPONSE_STATE)==1)
                    {
                        ArrayList<LandModel> landTemp=new ArrayList<LandModel>();
                        ArrayList<LandModel> featuredLandTemp=new ArrayList<LandModel>();

                        for(int i=0; i < LandList.length();i++)
                        {
                            LandItem = LandList.getJSONObject(i);
                            imagesArray =new JSONArray( LandItem.getString(Constants.SALE_MODEL_IMAGES));
                            LandModel landModel = new LandModel(
                                    LandItem.getString(Constants.LAND_MODEL_ID),
                                        LandItem.getString(Constants.LAND_MODEL_TOTAL_PRICE),
                                    LandItem.getString(Constants.LAND_INFO_MORTGAGE_TOTAL_PRICE),
                                    LandItem.getString(Constants.LAND_MODEL_TOTAL_RENT_PRICE),
                                    LandItem.getString(Constants.LAND_MODEL_TITLE),
                                    LandItem.getString(Constants.LAND_MODEL_LAND_STATE_ID),
                                    LandItem.getString(Constants.LAND_MODEL_CREATED_AT),
                                    LandItem.getString(Constants.LAND_MODEL_LAND_SITUATION_ID),
                                    "",
                                    imagesArray.get(0).toString(),
                                    LandItem.getString(Constants.LAND_MODEL_LANDSTATETITLE),
                                    LandItem.getString(Constants.USER_LAND_MODEL_DISTRICT_ID),
                                    "",
                                        "",
                                    LandItem.getString(Constants.LAND_MODEL_FIRST_NAME),
                                    LandItem.getString(Constants.LAND_MODEL_LAST_NAME),
                                    LandItem.getString(Constants.LAND_MODEL_LAND_CASE_ID)
                            );

//                            LandItem.getString(Constants.LAND_MODEL_LANDSITUATIONTITLE),
//                            LandItem.getString(Constants.LAND_MODEL_VIEW),
//                            LandItem.getString(Constants.LAND_MODEL_LAND_STATE_ID),
//                            LandItem.getString(Constants.LAND_MODEL_TOTAL_PRICE),
//                                    LandItem.getString(Constants.LAND_MODEL_TOTAL_MORTGAGE_PRICE),
//                                    LandItem.getString(Constants.LAND_INFO_RENT_TOTAL_PRICE),

                            landTemp.add(landModel);
                            if(Integer.parseInt(landModel.getLand_case_id())>1){
                                featuredLandTemp.add(landModel);
                            };
                        }
                        landModels=landTemp;
                        featuredLandModels=featuredLandTemp;
                        VerticalrecyclerView.setAdapter(new HomeVerticalRecyclerViewAdapter(featuredLandModels,getActivity()));
                        HorizantalrecyclerView.setAdapter(new HomeRecyclerViewAdapter(landModels,getActivity()));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(TAG, "onPostExecute exception: "+e.toString());
                    Toast.makeText(getContext(),getResources().getString(R.string.search_filter_no_item),Toast.LENGTH_LONG).show();

                }

            }

            @Override
            protected String doInBackground(Void... voids) {
                HTTPRequestHandlre httpRequestHandlre = new HTTPRequestHandlre();
                HashMap<String,String> params = new HashMap<>();
                params.put(Constants.CONTENT_TYPE,Constants.APPLICATION_JSON);
                params.put("CityID_way2",searchCityStr);
                params.put("DistrictID_way2",searchDistrictStr);
                params.put("LandStateID",searchLandStateStr);
                Log.d(TAG, "doInBackground: "+params.toString());
                return httpRequestHandlre.sendPostRequest(Urls.getBaseURL()+Urls.getSearchInAds(),params);
            }
        }
        SearchFilterRequestAsync searchFilterRequestAsync = new SearchFilterRequestAsync();
        searchFilterRequestAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR , null);
    }

    public void ProSearchFilterRequest( ){
        class ProSearchFilterRequestAsync extends AsyncTask<Void ,Void, String> {
            private final ProgressDialog progressDialog=new ProgressDialog(getContext());
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog.setMessage(getResources().getString(R.string.loading_message));
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                featuredLandModels.clear();
                landModels.clear();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (progressDialog.isShowing())progressDialog.dismiss();
                Log.d(TAG, "onPostExecute: "+s);
                try {
                    JSONObject reader = new JSONObject(s);
                    JSONArray LandList = new JSONArray(reader.getString(Constants.JSON_RESPONSE_DATA));
                    JSONObject LandItem;
                    JSONArray imagesArray;
                    if (LandList.length()==0){
                        Toast.makeText(getContext(),getResources().getString(R.string.search_filter_no_item),Toast.LENGTH_LONG).show();
                    }
                    if (reader.getInt(Constants.JSON_RESPONSE_STATE)==1)
                    {
                        ArrayList<LandModel> landTemp=new ArrayList<LandModel>();
                        ArrayList<LandModel> featuredLandTemp=new ArrayList<LandModel>();

                        for(int i=0; i < LandList.length();i++)
                        {
                            LandItem = LandList.getJSONObject(i);
                            imagesArray =new JSONArray( LandItem.getString(Constants.SALE_MODEL_IMAGES));
                            LandModel landModel = new LandModel(
                                    LandItem.getString(Constants.LAND_MODEL_ID),
                                    LandItem.getString(Constants.LAND_MODEL_TOTAL_PRICE),
                                    LandItem.getString(Constants.LAND_INFO_MORTGAGE_TOTAL_PRICE),
                                    LandItem.getString(Constants.LAND_MODEL_TOTAL_RENT_PRICE),
                                    LandItem.getString(Constants.LAND_MODEL_TITLE),
                                    LandItem.getString(Constants.LAND_MODEL_LAND_STATE_ID),
                                    LandItem.getString(Constants.LAND_MODEL_CREATED_AT),
                                    LandItem.getString(Constants.LAND_MODEL_LAND_SITUATION_ID),
                                    "",
                                    imagesArray.get(0).toString(),
                                    LandItem.getString(Constants.LAND_MODEL_LANDSTATETITLE),
                                    LandItem.getString(Constants.USER_LAND_MODEL_DISTRICT_ID),
                                    "",
                                    "",
                                    LandItem.getString(Constants.LAND_MODEL_FIRST_NAME),
                                    LandItem.getString(Constants.LAND_MODEL_LAST_NAME),
                                    LandItem.getString(Constants.LAND_MODEL_LAND_CASE_ID)
                            );

//                            LandItem.getString(Constants.LAND_MODEL_LANDSITUATIONTITLE),
//                            LandItem.getString(Constants.LAND_MODEL_VIEW),
//                            LandItem.getString(Constants.LAND_MODEL_LAND_STATE_ID),
//                            LandItem.getString(Constants.LAND_MODEL_TOTAL_PRICE),
//                                    LandItem.getString(Constants.LAND_MODEL_TOTAL_MORTGAGE_PRICE),
//                                    LandItem.getString(Constants.LAND_INFO_RENT_TOTAL_PRICE),

                            landTemp.add(landModel);
                            if(Integer.parseInt(landModel.getLand_case_id())>1){
                                featuredLandTemp.add(landModel);
                            };
                        }
                        landModels=landTemp;
                        featuredLandModels=featuredLandTemp;
                        VerticalrecyclerView.setAdapter(new HomeVerticalRecyclerViewAdapter(featuredLandModels,getActivity()));
                        HorizantalrecyclerView.setAdapter(new HomeRecyclerViewAdapter(landModels,getActivity()));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(TAG, "onPostExecute exception: "+e.toString());
                    Toast.makeText(getContext(),getResources().getString(R.string.search_filter_no_item),Toast.LENGTH_LONG).show();

                }
                editor.clear().commit();

            }

            @Override
            protected String doInBackground(Void... voids) {
                HTTPRequestHandlre httpRequestHandlre = new HTTPRequestHandlre();
                HashMap<String,String> params = new HashMap<>();
                params.put(Constants.CONTENT_TYPE,Constants.APPLICATION_JSON);


                params.put("LandTypeID",pro_filter_pref.getString("LandTypeID","0"));
                params.put("LandStateID",pro_filter_pref.getString("LandStateID","0"));
                params.put("FromBuildingYear",pro_filter_pref.getString("FromBuildingYear","0"));
                params.put("ToBuildingYear",pro_filter_pref.getString("ToBuildingYear","0"));
                params.put("FromSaleTotalPrice",pro_filter_pref.getString("FromSaleTotalPrice","0"));
                params.put("ToSaleTotalPrice",pro_filter_pref.getString("ToSaleTotalPrice","0"));
                params.put("FromFoundationSpace",pro_filter_pref.getString("FromFoundationSpace","0"));
                params.put("ToFoundationSpace",pro_filter_pref.getString("ToFoundationSpace","0"));
                params.put("FromMortgageTotalPrice",pro_filter_pref.getString("FromMortgageTotalPrice","0"));
                params.put("ToMortgageTotalPrice",pro_filter_pref.getString("ToMortgageTotalPrice","0"));
                params.put("FromRentTotalPrice",pro_filter_pref.getString("FromRentTotalPrice","0"));
                params.put("ToRentTotalPrice",pro_filter_pref.getString("ToRentTotalPrice","0"));


                Log.d(TAG, "doInBackground: "+params.toString());
                return httpRequestHandlre.sendPostRequest(Urls.getBaseURL()+Urls.getProSearch(),params);
            }
        }
        ProSearchFilterRequestAsync proSearchFilterRequestAsync = new ProSearchFilterRequestAsync();
        proSearchFilterRequestAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR , null);
    }


    public void SearchRequest(final Context context){
        class SearchRequestAsync extends AsyncTask<Void ,Void, String> {
            private final ProgressDialog progressDialog=new ProgressDialog(context);
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog.setMessage(getResources().getString(R.string.loading_message));
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                featuredLandModels.clear();
                landModels.clear();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (progressDialog.isShowing())progressDialog.dismiss();
                Log.d(TAG, "onPostExecute: "+s);
                try {
                    JSONObject reader = new JSONObject(s);
                    JSONArray LandList = new JSONArray(reader.getString(Constants.JSON_RESPONSE_DATA));
                    JSONObject LandItem;
                    JSONArray imagesArray;
                    if (LandList.length()==0){
                        Toast.makeText(getContext(),getResources().getString(R.string.search_filter_no_item),Toast.LENGTH_LONG).show();
                    }
                    if (reader.getInt(Constants.JSON_RESPONSE_STATE)==1)
                    {
                        ArrayList<LandModel> landTemp=new ArrayList<LandModel>();
                        ArrayList<LandModel> featuredLandTemp=new ArrayList<LandModel>();

                        for(int i=0; i < LandList.length();i++)
                        {
                            LandItem = LandList.getJSONObject(i);
                            imagesArray =new JSONArray( LandItem.getString(Constants.SALE_MODEL_IMAGES));
                            LandModel landModel = new LandModel(
                                    LandItem.getString(Constants.LAND_MODEL_ID),
                                    LandItem.getString(Constants.PRE_SALE_MODEL_TOTAL_SALE_PRICE),
                                    LandItem.getString(Constants.LAND_INFO_MORTGAGE_TOTAL_PRICE),
                                    LandItem.getString(Constants.LAND_MODEL_TOTAL_RENT_PRICE),
                                    LandItem.getString(Constants.LAND_MODEL_TITLE),
                                    LandItem.getString(Constants.LAND_INFO_LAND_STATE_ID),
                                    LandItem.getString(Constants.LAND_MODEL_CREATED_AT),
                                    LandItem.getString(Constants.LAND_MODEL_LAND_SITUATION_ID),
                                    "",
                                    imagesArray.get(0).toString(),
                                    LandItem.getString(Constants.LAND_MODEL_LANDSTATETITLE),
                                    LandItem.getString(Constants.USER_LAND_MODEL_DISTRICT_ID),
                                    "",
                                    "",
                                    LandItem.getString(Constants.LAND_MODEL_FIRST_NAME),
                                    LandItem.getString(Constants.LAND_MODEL_LAST_NAME),
                                    LandItem.getString(Constants.LAND_MODEL_LAND_CASE_ID)
                            );

//                            LandItem.getString(Constants.LAND_MODEL_LANDSITUATIONTITLE),
//                            LandItem.getString(Constants.LAND_MODEL_VIEW),
//                            LandItem.getString(Constants.LAND_MODEL_LAND_STATE_ID),
//                            LandItem.getString(Constants.LAND_MODEL_TOTAL_PRICE),
//                                    LandItem.getString(Constants.LAND_MODEL_TOTAL_MORTGAGE_PRICE),
//                                    LandItem.getString(Constants.LAND_INFO_RENT_TOTAL_PRICE),

                            landTemp.add(landModel);
                            if(Integer.parseInt(landModel.getLand_case_id())>1){
                                featuredLandTemp.add(landModel);
                            };
                        }
                        landModels=landTemp;
                        featuredLandModels=featuredLandTemp;
                        VerticalrecyclerView.setAdapter(new HomeVerticalRecyclerViewAdapter(featuredLandModels,getActivity()));
                        HorizantalrecyclerView.setAdapter(new HomeRecyclerViewAdapter(landModels,getActivity()));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(TAG, "onPostExecute exception: "+e.toString());
                    Toast.makeText(getContext(),getResources().getString(R.string.search_filter_no_item),Toast.LENGTH_LONG).show();

                }

            }

            @Override
            protected String doInBackground(Void... voids) {
                HTTPRequestHandlre httpRequestHandlre = new HTTPRequestHandlre();
                HashMap<String,String> params = new HashMap<>();
                params.put(Constants.CONTENT_TYPE,Constants.APPLICATION_JSON);
                params.put("Text",searchStr);

                Log.d(TAG, "doInBackground: "+params.toString());
                return httpRequestHandlre.sendPostRequest(Urls.getBaseURL()+Urls.getSearchInAdsWithText(),params);
            }
        }
        SearchRequestAsync searchRequestAsync = new SearchRequestAsync();
        searchRequestAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR , null);
    }




}