package ir.hamedanmelk.hamedanmelk.ui.single;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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
import java.util.HashMap;
import java.util.Objects;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.micro.LandCaseTypeModel;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.DownloadImage;
import ir.hamedanmelk.hamedanmelk.tools.HTTPRequestHandlre;
import ir.hamedanmelk.hamedanmelk.tools.MYSQlDBHelper;
import ir.hamedanmelk.hamedanmelk.tools.Urls;
import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SingleRentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SingleRentFragment extends Fragment implements OnMapReadyCallback {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG        = "SingleRentFragment";
    MYSQlDBHelper qlDBHelper;
    GoogleMap mgoogleMap;
    MapView mapView;
    private Marker _marker;
    private LatLng mapLatLng = new LatLng(Constants.MAP_EYDAN_LAT,Constants.MAP_MEYDAN_LNG);
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String landId;
    TextView titleTxt ;
    TextView roomCountTxt;
    TextView landTypeTxt ;
    TextView floorCountTxt ;
    TextView spaceFoundationTxt ;
    TextView rentTotalPriceTxt ;
    TextView mortgageTotalPriceTxt  ;
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
    public SingleRentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SingleLandFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SingleRentFragment newInstance(String param1, String param2) {
        SingleRentFragment fragment = new SingleRentFragment();
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_single_rent, container, false);
        mapView = (MapView) view.findViewById(R.id.mapView);
        qlDBHelper = new MYSQlDBHelper(getContext());
        titleTxt = (TextView)view.findViewById(R.id.SingleRentTitleTxt);
        landTypeTxt = (TextView)view.findViewById(R.id.SingleRentLandTypeTxt);
        roomCountTxt = (TextView)view.findViewById(R.id.SingleRentRoomCountTxt);
        spaceFoundationTxt = (TextView)view.findViewById(R.id.SingleRentFoundationSpaceTxt);
        mortgageTotalPriceTxt = (TextView)view.findViewById(R.id.SingleRentMortgageTotalPriceTxt);
        rentTotalPriceTxt  =(TextView)view.findViewById(R.id.SingleRentRentTotalPriceTxt);
        districtTxt =(TextView)view.findViewById(R.id.SingleRentDistrictTxtTxt);
        addressTxt = (TextView)view.findViewById(R.id.SingleRentAddressTxt);
        floorCountTxt = (TextView)view.findViewById(R.id.SingleRentFloorCountTxt);
        landCaseTxt = (TextView)view.findViewById(R.id.SingleRentLandCaseTxt);
        userDescriptionTxt =(TextView)view.findViewById(R.id.SingleRentUserDescriptionMultiTxt);
        landStateTxt = (TextView)view.findViewById(R.id.SingleRentLandStateTxt);
        buildingYearTxt = (TextView)view.findViewById(R.id.SingleRentBuildingYearTxt);
        createAtTxt = (TextView)view.findViewById(R.id.SingleRentCreatedAtTxt);
        userNameTxt = (TextView)view.findViewById(R.id.SingleRentUserNameTxt);
        userPhoneTxt = (TextView)view.findViewById(R.id.SingleRentUserPhone);
        descriptionTxt = (EditText)view.findViewById(R.id.SingleRentDescriptionTxt);
        userAvatarImg = (ImageView)view.findViewById(R.id.SingleRentUserAvatarImg);
        GetLandInfoRequest(getContext());
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
                        String[] mapStr = responseData.getString(Constants.LAND_INFO_MAP).split(",");
                        mapLatLng = new LatLng(Double.parseDouble(mapStr[0]),Double.parseDouble(mapStr[1]));
                        loadMap();
                        landStateTxt.setText(responseData.getString(Constants.LAND_INFO_LAND_STATE_TITLE));
                        buildingYearTxt.setText(responseData.getString(Constants.LAND_INFO_BUILDING_YEAR));
                        roomCountTxt.setText(responseData.getString(Constants.LAND_INFO_ROOM_COUNT));
                        titleTxt.setText(responseData.getString(Constants.LAND_INFO_TITLe));
                        landTypeTxt.setText(responseData.getString(Constants.LAND_INFO_LAND_TYPE_TITLE));
                        floorCountTxt.setText(responseData.getString(Constants.LAND_INFO_FLOOR_COUNT));
                        spaceFoundationTxt.setText(responseData.getString(Constants.LAND_INFO_FOUNDATION_SPACE));
                        mortgageTotalPriceTxt.setText(new DecimalFormat("###,###,###").format(Integer.parseInt(responseData.getString(Constants.LAND_INFO_MORTGAGE_TOTAL_PRICE))));
                        rentTotalPriceTxt.setText(new DecimalFormat("###,###,###").format(Integer.parseInt(responseData.getString(Constants.LAND_INFO_RENT_TOTAL_PRICE))));
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
        getLandInfoRequestAsync.execute();

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