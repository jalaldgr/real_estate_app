package ir.hamedanmelk.hamedanmelk.ui.single;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
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
import ir.hamedanmelk.hamedanmelk.models.micro.VoucherModel;
import ir.hamedanmelk.hamedanmelk.recyclers.LandEquipmentsAdapter;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.DownloadImage;
import ir.hamedanmelk.hamedanmelk.tools.HTTPRequestHandlre;
import ir.hamedanmelk.hamedanmelk.tools.MYSQlDBHelper;
import ir.hamedanmelk.hamedanmelk.tools.Urls;
import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

import static com.daimajia.slider.library.SliderLayout.PresetIndicators.Center_Bottom;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SinglePreSaleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SinglePreSaleFragment extends Fragment implements OnMapReadyCallback {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG        = "SinglePreSaleFragment";
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
    TextView unitInFloor;
    TextView preSaleTotalPriceTxt;
    TextView landCaseTxt;
    TextView voucherTypeTxt;
    TextView landStateTxt;
    TextView deliveryDateTxt;
    TextView districtTxt ;
    TextView provinceTxt;
    TextView cityTxt;
    TextView areaTxt;
    TextView addressTxt;
    TextView createAtTxt;
    TextView userNameTxt;
    TextView userPhoneTxt;
    EditText descriptionTxt;
    CardView descCardView;
    ImageView userAvatarImg;
    GridView equipmentsGridView;
    ViewPager viewPager;
    CheckBox bookmarkChckbx;
    TextView startChatTxt;
    TextView shareTxt;
    TextView mobileTxt;
    SliderLayout mySliderLayout;
    PagerIndicator myIndicator;
    public SinglePreSaleFragment() {
        // Required empty public constructor
    }

    public static SinglePreSaleFragment newInstance(String param1, String param2) {
        SinglePreSaleFragment fragment = new SinglePreSaleFragment();
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
        View view = inflater.inflate(R.layout.fragment_single_presale, container, false);
        mapView = (MapView) view.findViewById(R.id.SinglePreSaleMapView);
        qlDBHelper = new MYSQlDBHelper(getContext());
        titleTxt = (TextView)view.findViewById(R.id.SinglePreSaleTitleTxt);
        landTypeTxt = (TextView)view.findViewById(R.id.SinglePreSaleLandTypeTxt);
        roomCountTxt = (TextView)view.findViewById(R.id.SinglePreSaleRoomCountTxt);
        spaceFoundationTxt = (TextView)view.findViewById(R.id.SinglePreSaleFoundationSpaceTxt);
        districtTxt =(TextView)view.findViewById(R.id.SinglePreSaleDistrictTxt);
        preSaleTotalPriceTxt = (TextView)view.findViewById(R.id.SinglePreSaleTotalPriceTxt);
        unitInFloor = (TextView)view.findViewById(R.id.SinglePreSaleUnitInfloorTxt);
        addressTxt = (TextView)view.findViewById(R.id.SinglePreSaleAddressTxt);
        provinceTxt = (TextView)view.findViewById(R.id.SinglePreSaleProvinceTxt);
        cityTxt = (TextView)view.findViewById(R.id.SinglePreSaleCityTxt);
        areaTxt = (TextView)view.findViewById(R.id.SinglePreSaleAreaTxt);
        floorCountTxt = (TextView)view.findViewById(R.id.SinglePreSaleFloorCountTxt);
        landCaseTxt = (TextView)view.findViewById(R.id.SinglePreSaleLandCaseTxt);
        voucherTypeTxt=(TextView)view.findViewById(R.id.SinglePreSaleVoucherTxt);
        landStateTxt = (TextView)view.findViewById(R.id.SinglePreSaleLandStateTxt);
        deliveryDateTxt = (TextView)view.findViewById(R.id.SinglePreSaleDeliveryDateTxt);
        createAtTxt = (TextView)view.findViewById(R.id.SinglePreSaleCreatedAtTxt);
        userNameTxt = (TextView)view.findViewById(R.id.SinglePreSaleUserNameTxt);
        userPhoneTxt = (TextView)view.findViewById(R.id.SinglePreSaleMobileTxt);
        descriptionTxt = (EditText)view.findViewById(R.id.SinglePreSaleDescriptionTxt);
        descCardView = (CardView)view.findViewById(R.id.SinglePreSaleDescriptionCardView);
        userAvatarImg = (ImageView)view.findViewById(R.id.SinglePreSaleUserAvatarImg);
        equipmentsGridView = (GridView)view.findViewById(R.id.SinglePreSaleLandEquipmentsGridView);
        bookmarkChckbx = (CheckBox)view.findViewById(R.id.SinglePreSaleFragmentBookmarkChckbx);
        startChatTxt = (TextView) view.findViewById(R.id.SinglePreSaleStartChatTxt);
        shareTxt = (TextView) view.findViewById(R.id.SinglePreSaleShareTxt);
        mobileTxt = (TextView) view.findViewById(R .id.SinglePreSaleMobileTxt);

        mySliderLayout = (SliderLayout)view.findViewById(R.id.single_PreSale_slider);
        myIndicator = (PagerIndicator) view.findViewById(R.id.custom_indicator);
        mySliderLayout.setPresetTransformer(SliderLayout.Transformer.Tablet);

        mySliderLayout.setPresetIndicator(Center_Bottom);
        myIndicator.setGravity(0x11);
        mySliderLayout.setCustomIndicator(myIndicator);
        mySliderLayout.setCustomAnimation(new DescriptionAnimation());

//                        mySliderLayout.setDuration(3000);
//                        mySliderLayout.addOnPageChangeListener((ViewPagerEx.OnPageChangeListener) this);

        if(qlDBHelper.isBookmarkedByLandID(landId)){
            bookmarkChckbx.setChecked(true);
            bookmarkChckbx.setBackground(getResources().getDrawable(R.drawable.ic_baseline_favorite_br_36));
        }else{
            bookmarkChckbx.setChecked(false);
            bookmarkChckbx.setBackground(getResources().getDrawable(R.drawable.iic_baseline_favorite_disabled_border_on_36));
        }
        bookmarkChckbx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    AddFavoriteRequest(getContext());
                    qlDBHelper.InsertBookmark(landId);
                    bookmarkChckbx.setBackground(getResources().getDrawable(R.drawable.ic_baseline_favorite_br_36));
                }
                else {
                    RemoveFavoriteRequest(getContext());
                    qlDBHelper.DeleteBookmarkByLandID(landId);
                    bookmarkChckbx.setBackground(getResources().getDrawable(R.drawable.iic_baseline_favorite_disabled_border_on_36));

                }
            }
        });

        startChatTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences user_pref = Objects.requireNonNull(getActivity()).getSharedPreferences(getString(R.string.user_shared_preference), Context.MODE_PRIVATE);
                final NavController controller = Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.nav_host_fragment);
                if(user_pref.contains("id")) {

                    Bundle args = new Bundle();
                    args.putString(Constants.START_CHAT_UID, UID);
                    args.putString(Constants.START_CHAT_TO, landUserID);
                    controller.navigate(R.id.chatFragment, args);
                }else
                {
                    controller.navigate(R.id.userLogin);
                }
            }
        });

        shareTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "اشتراک گذاری");
                i.putExtra(Intent.EXTRA_TEXT, "https://hamedanmelk.ir/AdsRentDetail/"+landId);
                startActivity(Intent.createChooser(i, "اشتراک گذاری"));
            }
        });

        mobileTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "tel:" + mobileTxt.getText().toString() ;
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                startActivity(intent);
            }
        });

        GetLandInfoRequest(getContext());
        GetLandEquipmentsRequest(getContext(),landId);


        return view;
    }

    @Override
    public void onStop() {
        mySliderLayout.stopAutoCycle();
        super.onStop();
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
                        try {
                            PersianDateFormat persianDateFormat=new PersianDateFormat("yyyy-MM-dd");
                            PersianDate persianDate = persianDateFormat.parseGrg(responseData.get(Constants.LAND_INFO_DELIVERY_DATE).toString(), "yyyy-MM-dd");
                            String withoutTime = persianDate.toString().replace("00:00:00","");
                            deliveryDateTxt.setText(withoutTime);
                        }catch (ParseException e){
                            e.printStackTrace();
                        }
                        roomCountTxt.setText(responseData.getString(Constants.LAND_INFO_ROOM_COUNT));
                        titleTxt.setText(responseData.getString(Constants.LAND_INFO_TITLe));
                        landTypeTxt.setText(responseData.getString(Constants.LAND_INFO_LAND_TYPE_TITLE));
                        floorCountTxt.setText(responseData.getString(Constants.LAND_INFO_FLOOR_COUNT));
                        spaceFoundationTxt.setText(responseData.getString(Constants.LAND_INFO_FOUNDATION_SPACE)+ "  متر مربع");
                        preSaleTotalPriceTxt.setText(new DecimalFormat("###,###,###").format(Integer.parseInt(responseData.getString(Constants.LAND_INFO_SALE_TOTAL_PRICE)))+ "  تومان");
                        LandCaseTypeModel landCaseTypeModel = qlDBHelper.GetLandCaseTypeByID(responseData.getString(Constants.LAND_INFO_LAND_CASE_ID));
                        landCaseTxt.setText(landCaseTypeModel.getTitle());
                        VoucherModel voucherModel = qlDBHelper.GetVoucherByID(responseData.getString(Constants.LAND_INFO_VOUCHER_TYPE_ID));
                        voucherTypeTxt.setText(voucherModel.getTitle());
                        districtTxt.setText(responseData.getString(Constants.LAND_INFO_DISTRICT_TITLE));
                        provinceTxt.setText(responseData.getString(Constants.LAND_INFO_PROVINCE_TITLE));
                        cityTxt.setText(responseData.getString(Constants.LAND_INFO_CITY_TITLE));
                        areaTxt.setText(responseData.getString(Constants.LAND_INFO_AREA_TITLE));
                        addressTxt.setText(responseData.getString(Constants.LAND_INFO_ADDRESS));
                        unitInFloor.setText(responseData.getString(Constants.LAND_INFO_UNIT_IN_FLOOR));
                        floorCountTxt.setText(responseData.getString(Constants.LAND_INFO_FLOOR_COUNT));
                        try {
                            PersianDateFormat persianDateFormat=new PersianDateFormat("yyyy-MM-dd");
                            PersianDate persianDate = persianDateFormat.parseGrg(responseData.get(Constants.LAND_INFO_CREATED_AT).toString(), "yyyy-MM-dd");
                            createAtTxt.setText(persianDate.toString().replace("00:00:00",""));
                        }catch (ParseException e){
                            e.printStackTrace();
                        }
                        descriptionTxt.setText(Html.fromHtml(responseData.getString(Constants.LAND_INFO_DESCRIPTION)));
                        if (responseData.getString(Constants.LAND_INFO_DESCRIPTION)=="null")descCardView.setVisibility(View.GONE);
                        userNameTxt.setText(responseData.getString(Constants.LAND_INFO_FIRST_NAME)+
                                " "+responseData.getString(Constants.LAND_INFO_LAST_NAME));
                        userPhoneTxt.setText(responseData.getString(Constants.LAND_INFO_USER_PHONE));
                        mobileTxt.setText(responseData.getString(Constants.LAND_INFO_USER_PHONE));
//                        userDescriptionTxt.setText(Html.fromHtml(responseData.getString(Constants.LAND_INFO_USER_DESCRIPTION)));
                        //GalleryRecyclerViewAdapter galleryRecyclerViewAdapter = new GalleryRecyclerViewAdapter(getContext(),images);
                        //viewPager.setAdapter(galleryRecyclerViewAdapter);
                        new DownloadImage(userAvatarImg).execute(Urls.getBaseURL()+"/"+responseData.getString(Constants.LAND_INFO_USER_IMAGE));
                        for (int i=0; i < images.length(); i++){
                            DefaultSliderView t1 = new DefaultSliderView(getActivity().getApplicationContext());
                            t1.image(Urls.getBaseURL()+"/"+images.getString(i));//t1.description("shearch");
                            mySliderLayout.addSlider(t1);
                        }
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
                LandEquipmentsAdapter equipmentsAdapter = new LandEquipmentsAdapter(equipmentModels,context, getActivity());
                equipmentsGridView.setAdapter(equipmentsAdapter);
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