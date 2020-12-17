package ir.hamedanmelk.hamedanmelk.ui.single;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.micro.EquipmentModel;
import ir.hamedanmelk.hamedanmelk.models.micro.LandCaseTypeModel;
import ir.hamedanmelk.hamedanmelk.recyclers.GalleryRecyclerViewAdapter;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.DownloadImage;
import ir.hamedanmelk.hamedanmelk.tools.ExpandableHeightGridView;
import ir.hamedanmelk.hamedanmelk.tools.HTTPRequestHandlre;
import ir.hamedanmelk.hamedanmelk.tools.MYSQlDBHelper;
import ir.hamedanmelk.hamedanmelk.tools.Urls;
import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SingleSaleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SingleSaleFragment extends Fragment implements OnMapReadyCallback {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG        = "SingleSaleFragment";
    MYSQlDBHelper qlDBHelper;
    GoogleMap mgoogleMap;
    MapView mapView;
    private JSONArray galleryImageArrayList;
    private Marker _marker;
    private LatLng mapLatLng = new LatLng(Constants.MAP_MEYDAN_LAT,Constants.MAP_MEYDAN_LNG);
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String landId;
    String  landUserID;
    String  UID;
    TextView titleTxt ;
    TextView roomCountTxt;
    TextView landTypeTxt ;
    TextView floorCountTxt ;
    TextView spaceFoundationTxt ;
    TextView saleTotalPriceTxt ;
    TextView landCaseTxt;
    TextView userDescriptionTxt ;
    TextView landStateTxt;
    TextView buildingYearTxt;
    TextView districtTxt ;
    TextView addressTxt;
    TextView createAtTxt;
    TextView userNameTxt;
    TextView userPhoneTxt;
    EditText descriptionTxt;
    ImageView userAvatarImg;
    ExpandableHeightGridView equipmentsGridView;
    ViewPager viewPager;
    CheckBox bookmarkChckbx;
    Button  startChatBtn;
    public SingleSaleFragment() {
        // Required empty public constructor
    }

    public static SingleSaleFragment newInstance(String param1, String param2) {
        SingleSaleFragment fragment = new SingleSaleFragment();
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
            landId  = getArguments().getString(Constants.LAND_INFO_ID);
        }
        final SharedPreferences user_pref = Objects.requireNonNull(getActivity()).getSharedPreferences(getString(R.string.user_shared_preference), Context.MODE_PRIVATE);
        UID = user_pref.getString(Constants.USER_MODEL_ID,"0");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_single_sale, container, false);
        mapView = (MapView) view.findViewById(R.id.mapView);
        qlDBHelper = new MYSQlDBHelper(getContext());
        titleTxt = (TextView)view.findViewById(R.id.SingleSaleTitleTxt);
        landTypeTxt = (TextView)view.findViewById(R.id.SingleSaleLandTypeTxt);
        roomCountTxt = (TextView)view.findViewById(R.id.SingleSaleRoomCountTxt);
        spaceFoundationTxt = (TextView)view.findViewById(R.id.SingleSaleFoundationSpaceTxt);
        saleTotalPriceTxt  =(TextView)view.findViewById(R.id.SingleSaleTotalPriceTxt);
        districtTxt =(TextView)view.findViewById(R.id.SingleSaleDistrictTxtTxt);
        addressTxt = (TextView)view.findViewById(R.id.SingleSaleAddressTxt);
        floorCountTxt = (TextView)view.findViewById(R.id.SingleSaleFloorCountTxt);
        landCaseTxt = (TextView)view.findViewById(R.id.SingleSaleLandCaseTxt);
        userDescriptionTxt =(TextView)view.findViewById(R.id.SingleSaleUserDescriptionMultiTxt);
        landStateTxt = (TextView)view.findViewById(R.id.SingleSaleLandStateTxt);
        buildingYearTxt = (TextView)view.findViewById(R.id.SingleSaleBuildingYearTxt);
        createAtTxt = (TextView)view.findViewById(R.id.SingleSaleCreatedAtTxt);
        userNameTxt = (TextView)view.findViewById(R.id.SingleSaleUserNameTxt);
        userPhoneTxt = (TextView)view.findViewById(R.id.SingleSaleUserPhoneTxt);
        descriptionTxt = (EditText)view.findViewById(R.id.SingleSaleDescriptionTxt);
        userAvatarImg = (ImageView)view.findViewById(R.id.SingleSaleUserAvatarImg);
        equipmentsGridView = (ExpandableHeightGridView) view.findViewById(R.id.SingleSaleLandEquipmentsGridView);
        viewPager = (ViewPager) view.findViewById(R.id.SingleSaleGalleryViewpager);
        bookmarkChckbx = (CheckBox)view.findViewById(R.id.SingleSaleFragmentBookmarkChckbx);
        startChatBtn = (Button)view.findViewById(R.id.SingleSaleStartChatBtn);

        bookmarkChckbx.setChecked(qlDBHelper.isBookmarkedByLandID(landId));
        bookmarkChckbx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    AddFavoriteRequest(getContext());
                    qlDBHelper.InsertBookmark(landId);
                }
                else {
                    RemoveFavoriteRequest(getContext());
                    qlDBHelper.DeleteBookmarkByLandID(landId);
                }
            }
        });

        startChatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final NavController controller= Navigation.findNavController(Objects.requireNonNull(getActivity()),R.id.nav_host_fragment);
                Bundle args=new Bundle();
                args.putString(Constants.START_CHAT_UID,UID);
                args.putString(Constants.START_CHAT_TO,landUserID);
                controller.navigate(R.id.chatFragment,args);
            }
        });

        GetLandInfoRequest(getContext());
        GetLandEquipmentsRequest(getContext(),landId);


        return view;
    }

    public void GetLandInfoRequest(final Context context){
        class GetLandInfoRequestAsync extends AsyncTask<Void, Void, String> {
            private final ProgressDialog progressDialog = new ProgressDialog(context);
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setMessage(getResources().getString(R.string.loading_message));
                progressDialog.show();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (progressDialog.isShowing())progressDialog.dismiss();
                try {
                    JSONObject reader = new JSONObject(s);
                    Log.d(TAG, "onPostExecute: response: "+s.toString());
                    if(reader.getInt(Constants.JSON_RESPONSE_STATE)==1){
                        JSONObject responseData = new JSONObject(reader.getString(Constants.JSON_RESPONSE_DATA));
                        JSONArray images = new JSONArray(responseData.getString(Constants.LAND_INFO_IMAGES));
                        galleryImageArrayList = images;
                        String[] mapStr = responseData.getString(Constants.LAND_INFO_MAP).split(",");
                        mapLatLng = new LatLng(Double.parseDouble(mapStr[0]),Double.parseDouble(mapStr[1]));
                        landUserID=responseData.getString(Constants.LAND_INFO_USER_ID);
                        loadMap();
                        landStateTxt.setText(responseData.getString(Constants.LAND_INFO_LAND_STATE_TITLE));
                        buildingYearTxt.setText(responseData.getString(Constants.LAND_INFO_BUILDING_YEAR));
                        roomCountTxt.setText(responseData.getString(Constants.LAND_INFO_ROOM_COUNT));
                        titleTxt.setText(responseData.getString(Constants.LAND_INFO_TITLe));
                        landTypeTxt.setText(responseData.getString(Constants.LAND_INFO_LAND_TYPE_TITLE));
                        floorCountTxt.setText(responseData.getString(Constants.LAND_INFO_FLOOR_COUNT));
                        spaceFoundationTxt.setText(responseData.getString(Constants.LAND_INFO_FOUNDATION_SPACE));
                        saleTotalPriceTxt.setText(new DecimalFormat("###,###,###").format(Integer.parseInt(responseData.getString(Constants.LAND_INFO_RENT_TOTAL_PRICE))));
                        LandCaseTypeModel landCaseTypeModel = qlDBHelper.GetLandCaseTypeByID(responseData.getString(Constants.LAND_INFO_LAND_CASE_ID));
                        landCaseTxt.setText(landCaseTypeModel.getTitle());
                        districtTxt.setText(responseData.getString(Constants.LAND_INFO_DISTRICT_TITLE));
                        addressTxt.setText(responseData.getString(Constants.LAND_INFO_ADDRESS));
                        floorCountTxt.setText(responseData.getString(Constants.LAND_INFO_FLOOR_COUNT));
                        try {
                            PersianDateFormat persianDateFormat=new PersianDateFormat("yyyy-MM-dd");
                            PersianDate persianDate = persianDateFormat.parseGrg(responseData.get(Constants.LAND_INFO_CREATED_AT).toString(), "yyyy-MM-dd");
                            createAtTxt.setText(persianDate.toString());
                        }catch (ParseException e){
                            e.printStackTrace();
                        }
                        descriptionTxt.setText(Html.fromHtml(responseData.getString(Constants.LAND_INFO_DESCRIPTION)));
                        userNameTxt.setText(responseData.getString(Constants.LAND_INFO_FIRST_NAME)+
                                " "+responseData.getString(Constants.LAND_INFO_LAST_NAME));
                        userPhoneTxt.setText(responseData.getString(Constants.LAND_INFO_USER_PHONE));
                        userDescriptionTxt.setText(Html.fromHtml(responseData.getString(Constants.LAND_INFO_USER_DESCRIPTION)));
                        GalleryRecyclerViewAdapter galleryRecyclerViewAdapter = new GalleryRecyclerViewAdapter(getContext(),images);
                        viewPager.setAdapter(galleryRecyclerViewAdapter);
                        new DownloadImage(userAvatarImg).execute(Urls.getBaseURL()+"/"+responseData.getString(Constants.LAND_INFO_USER_IMAGE));

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(TAG, "onPostExecute exception:"+e.toString());
                }

            }

            @Override
            protected String doInBackground(Void... voids) {
                HTTPRequestHandlre httpRequestHandlre = new HTTPRequestHandlre();
                HashMap<String, String> params = new HashMap<>();
                params.put(Constants.CONTENT_TYPE,Constants.APPLICATION_JSON);
                return httpRequestHandlre.sendGetRequest(Urls.getBaseURL()+Urls.getGetLandInfo()+"/"+landId,params);
            }
        }
        GetLandInfoRequestAsync getLandInfoRequestAsync = new GetLandInfoRequestAsync();
        getLandInfoRequestAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR , null);

    }

    public void GetLandEquipmentsRequest(final Context context, final String landId){
        class GetEquipmentsRequestAsync extends AsyncTask<Void, Void, String> {


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                ArrayList<EquipmentModel> equipmentModels = new ArrayList<EquipmentModel>() ;
                try {
                    JSONObject reader = new JSONObject(s);
                    Log.d(TAG, "onPostExecute: response: "+s.toString());
                    if(reader.getInt(Constants.JSON_RESPONSE_STATE)==1){
                        JSONArray responseList = new JSONArray(reader.getString(Constants.JSON_RESPONSE_DATA));
                        EquipmentModel equipmentModel ;
                        JSONObject responseItem;
                        for(int i =0; i<responseList.length();i++){
                            responseItem = responseList.getJSONObject(i);
                            equipmentModel = new EquipmentModel(
                                    responseItem.getString(Constants.LAND_EQUIPMENTS_ID),
                                    responseItem.getString(Constants.LAND_EQUIPMENTS_TITLE),
                                    responseItem.getString(Constants.LAND_EQUIPMENTS_LOGO)
                            );
                            equipmentModels.add(equipmentModel);
                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(TAG, "onPostExecute exception:"+e.toString());
                }
                LandEquipmentsAdapter equipmentsAdapter = new LandEquipmentsAdapter(equipmentModels,context);
                equipmentsGridView.setAdapter(equipmentsAdapter);
                equipmentsGridView.setExpanded(true);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HTTPRequestHandlre httpRequestHandlre = new HTTPRequestHandlre();
                HashMap<String, String> params = new HashMap<>();
                params.put(Constants.CONTENT_TYPE,Constants.APPLICATION_JSON);
                params.put("LID",landId);
                return httpRequestHandlre.sendPostRequest(Urls.getBaseURL()+Urls.getGetLandEquipments(),params);
            }
        }
        GetEquipmentsRequestAsync getEquipmentsRequestAsync = new GetEquipmentsRequestAsync();
        getEquipmentsRequestAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR , null);

    }

    public void AddFavoriteRequest(final Context context){
        class AddFavoriteRequestAsync extends AsyncTask<Void ,Void, String> {
            @Override
            protected void onPostExecute(String s) {
                Log.d(TAG, "onPostExecute: "+s.toString());
                super.onPostExecute(s);
                try {
                    JSONObject reader = new JSONObject(s);
                    JSONObject ResponseData = new JSONObject(reader.getString(Constants.JSON_RESPONSE_DATA));
                    if (reader.getInt(Constants.JSON_RESPONSE_STATE)==1)
                    {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(TAG, "onPostExecute exception: "+e.toString());
                }

            }

            @Override
            protected String doInBackground(Void... voids) {
                SharedPreferences user_pref = Objects.requireNonNull(getActivity()).getSharedPreferences(getString(R.string.user_shared_preference), Context.MODE_PRIVATE);
                HTTPRequestHandlre httpRequestHandlre = new HTTPRequestHandlre();
                HashMap<String,String> params = new HashMap<>();
                params.put(Constants.CONTENT_TYPE,Constants.APPLICATION_JSON);
                params.put(Constants.LAND_INFO_LID,landId);
                params.put(Constants.LAND_INFO_UID,user_pref.getString("id","8"));
                return httpRequestHandlre.sendPostRequest(Urls.getBaseURL()+Urls.getUserLandFavoriteSubmit(),params);
            }
        }
        AddFavoriteRequestAsync AddFavoriteRequestAsync = new AddFavoriteRequestAsync();
        AddFavoriteRequestAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR , null);
    }

    public void RemoveFavoriteRequest(final Context context){
        class AddFavoriteRequestAsync extends AsyncTask<Void ,Void, String> {
            @Override
            protected void onPostExecute(String s) {
                Log.d(TAG, "onPostExecute: "+s.toString());
                super.onPostExecute(s);
                try {
                    JSONObject reader = new JSONObject(s);
                    JSONObject ResponseData = new JSONObject(reader.getString(Constants.JSON_RESPONSE_DATA));
                    if (reader.getInt(Constants.JSON_RESPONSE_STATE)==1)
                    {
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(TAG, "onPostExecute exception: "+e.toString());
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                SharedPreferences user_pref = Objects.requireNonNull(getActivity()).getSharedPreferences(getString(R.string.user_shared_preference), Context.MODE_PRIVATE);
                HTTPRequestHandlre httpRequestHandlre = new HTTPRequestHandlre();
                HashMap<String,String> params = new HashMap<>();
                params.put(Constants.CONTENT_TYPE,Constants.APPLICATION_JSON);
                params.put(Constants.LAND_INFO_LID,landId);
                params.put(Constants.LAND_INFO_UID,user_pref.getString("id","8"));
                return httpRequestHandlre.sendPostRequest(Urls.getBaseURL()+Urls.getUserLandFavoriteRemove(),params);
            }
        }
        AddFavoriteRequestAsync AddFavoriteRequestAsync = new AddFavoriteRequestAsync();
        AddFavoriteRequestAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR , null);
    }

    @Override
    public void onViewCreated(@NonNull View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    void loadMap(){
        if(mapView!=null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(Objects.requireNonNull(getContext()));
        mgoogleMap = googleMap;
//        mgoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation,17f));
        mgoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mapLatLng, 15.5f));
        mgoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
        mgoogleMap.getUiSettings().setAllGesturesEnabled(false);
        mgoogleMap.getUiSettings().setZoomControlsEnabled(true);
        mgoogleMap.getUiSettings().setZoomGesturesEnabled(true);

//        _marker = mgoogleMap.addMarker(new MarkerOptions().position(mapLatLng).draggable(true));
        mgoogleMap.addCircle(new CircleOptions().center(mapLatLng).fillColor(R.color.map_circle_fill_color).radius(200).strokeColor(R.color.map_circle_fill_color));

    }
}