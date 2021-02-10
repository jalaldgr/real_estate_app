package ir.hamedanmelk.hamedanmelk.ui.newland;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.darsh.multipleimageselect.activities.AlbumSelectActivity;
import com.darsh.multipleimageselect.models.Image;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.gson.Gson;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import io.apptik.widget.multiselectspinner.BaseMultiSelectSpinner;
import io.apptik.widget.multiselectspinner.MultiSelectSpinner;
import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.ImageModel;
import ir.hamedanmelk.hamedanmelk.models.NewLandModel;
import ir.hamedanmelk.hamedanmelk.models.micro.AreaModel;
import ir.hamedanmelk.hamedanmelk.models.micro.BuildingConditionModel;
import ir.hamedanmelk.hamedanmelk.models.micro.CityModel;
import ir.hamedanmelk.hamedanmelk.models.micro.DistrictModel;
import ir.hamedanmelk.hamedanmelk.models.micro.EquipmentModel;
import ir.hamedanmelk.hamedanmelk.models.micro.FloorCoveringModel;
import ir.hamedanmelk.hamedanmelk.models.micro.KitchenServiceModel;
import ir.hamedanmelk.hamedanmelk.models.micro.LandCaseTypeModel;
import ir.hamedanmelk.hamedanmelk.models.micro.LandDirectionModel;
import ir.hamedanmelk.hamedanmelk.models.micro.LandStateTypeModel;
import ir.hamedanmelk.hamedanmelk.models.micro.LandTypeModel;
import ir.hamedanmelk.hamedanmelk.models.micro.LandViewModel;
import ir.hamedanmelk.hamedanmelk.models.micro.LoanTypeModel;
import ir.hamedanmelk.hamedanmelk.models.micro.ProvinceModel;
import ir.hamedanmelk.hamedanmelk.models.micro.RentalPreferenceModel;
import ir.hamedanmelk.hamedanmelk.models.micro.UseTypeModel;
import ir.hamedanmelk.hamedanmelk.models.micro.VoucherModel;
import ir.hamedanmelk.hamedanmelk.models.myResponse;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.ExpandableHeightGridView;
import ir.hamedanmelk.hamedanmelk.tools.MYSQlDBHelper;
import ir.hamedanmelk.hamedanmelk.tools.RetrofitInterface;
import ir.hamedanmelk.hamedanmelk.tools.Urls;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import saman.zamani.persiandate.PersianDate;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class NewLandPreSaleFragment extends Fragment  implements OnMapReadyCallback, DatePickerDialog.OnDateSetListener{
    private RequestQueue myRequestQueue;
    private JsonObjectRequest myJsonObjectRequest;

    public static final int PICK_IMAGES = 5;
    private static final String TAG = "NewLandPreSaleFragment";
    public List<String> selectedUseTypes = new ArrayList<>();
    public List<String> selectedEquipments = new ArrayList<>();
    NewLandModel requestNewModel= new NewLandModel();
    String   UID;
    String   deliveryDateStr;
    EditText titleEtx;
    Spinner  buildingConditionSpnr;
    Spinner landCaseSpnr;
    Spinner landTypeSpnr;
    MultiSelectSpinner landUseTypeSpnr;
    Spinner voucherTypeSpnr;
    EditText dongETxt;
    EditText buildingYearETxt;
    EditText spaceFoundationETxt;
    EditText totalPriceETxt;
    EditText prePayPrice;
    EditText debtTotalPriceETxt;
    Spinner loanTypeSpnr;
    Spinner whichFloorSpnr;
    Spinner roomCountSpnr;
    Spinner floorCountSpnr;
    Spinner unitInFloorSpnr;
    Spinner provinceSpnr;
    Spinner citySpnr;
    Spinner areaSpnr;
    Spinner districtSpnr;
    EditText addressETxt;
    Spinner landViewSpnr;
    MultiSelectSpinner  equipmentSpnr;
    Spinner floorCoveringSpnr;
    Spinner directionSpnr;
    Spinner waterSpnr;
    Spinner gasSpnr;
    Spinner electricitySpnr;
    Spinner phoneSpnr;
    EditText descriptionETxt;
    EditText deliveryDate;
    EditText voucherDate;
    MapView landMapView;
    ExpandableHeightGridView selectedImagesExpandableGrid;
    Button addPhotoBtn;
    Button addMapBtn;
    DatePickerDialog datePicker;
    Date grgDate;
    PersianDate persianDate;
    PersianCalendar persianCalendar = new PersianCalendar();
    Button   submitBtn;
    GoogleMap mgoogleMap;
    LatLng mapLatLng;
    boolean selectMapClicked = false;
    EditText onMapTxt;

    LinearLayout mainlinearLayout;
    ArrayList<BuildingConditionModel> buildingConditionModels;
    List<String> buildingConditionTitles = new ArrayList<String>();
    List<String> buildingConditionIDs = new ArrayList<String>();
    ArrayAdapter<String> buildingConditionAdapter;


    ArrayList<LandCaseTypeModel> landCaseTypeModels;
    List<String> landCaseTitles= new ArrayList<String>();
    List<String> landCaseIDs= new ArrayList<String>();
    ArrayAdapter<String> landCaseAdapter ;

    ArrayList<LandTypeModel> landTypeModels;
    List<String> landTypeTitles= new ArrayList<String>();
    List<String> landTypeIDs= new ArrayList<String>();
    ArrayAdapter<String> landTypeAdapter ;

    ArrayList<RentalPreferenceModel> rentalPreferenceModels;
    List<String> rentalPreferenceTitles= new ArrayList<String>();
    List<String> rentalPreferenceIDs= new ArrayList<String>();
    ArrayAdapter<String> rentalPreferenceAdapter ;

    ArrayList<LoanTypeModel> loanTypeModels;
    List<String> loanTypeTitles= new ArrayList<String>();
    List<String> loanTypeIDs= new ArrayList<String>();
    ArrayAdapter<String> loanTypeAdapter ;

    ArrayList<ProvinceModel> provinceModels;
    List<String> provinceTitles= new ArrayList<String>();
    List<String> provinceIDs= new ArrayList<String>();
    ArrayAdapter<String> provinceAdapter ;

    ArrayList<CityModel> cityModels;
    List<String> cityTitles= new ArrayList<String>();
    List<String> cityIDs= new ArrayList<String>();
    ArrayAdapter<String> cityAdapter ;

    ArrayList<AreaModel> areaModels;
    List<String> areaTitles= new ArrayList<String>();
    List<String> areaIDs= new ArrayList<String>();
    ArrayAdapter<String> areaAdapter ;

    ArrayList<DistrictModel> districtModels;
    List<String> districtTitles= new ArrayList<String>();
    List<String> districtIDs= new ArrayList<String>();
    ArrayAdapter<String> districtAdapter ;

    ArrayList<LandViewModel> landViewModels;
    List<String> landViewTitles= new ArrayList<String>();
    List<String> landViewIDs= new ArrayList<String>();
    ArrayAdapter<String> landViewAdapter ;

    ArrayList<FloorCoveringModel> floorCoveringModels;
    List<String> floorCoveringTitles= new ArrayList<String>();
    List<String> floorCoveringIDs= new ArrayList<String>();
    ArrayAdapter<String> floorCoveringAdapter ;

    ArrayList<KitchenServiceModel> kitchenServiceModels;
    List<String> kitchenServiceTitles= new ArrayList<String>();
    List<String> kitchenServiceIDs= new ArrayList<String>();
    ArrayAdapter<String> kitchenServiceAdapter ;

    ArrayList<LandDirectionModel> landDirectionModels;
    List<String> landDirectionTitles= new ArrayList<String>();
    List<String> landDirectionTypeIDs= new ArrayList<String>();
    ArrayAdapter<String> landDirectionAdapter ;

    ArrayList<UseTypeModel> useTypeModels;
    List<String> useTypeTitles= new ArrayList<String>();
    List<String> useTypeIDs= new ArrayList<String>();
    ArrayAdapter<String> useTypeAdapter ;

    ArrayList<EquipmentModel> equipmentModels;
    List<String> equipmentTitles= new ArrayList<String>();
    List<String> equipmentIDs= new ArrayList<String>();
    ArrayAdapter<String> equipmentAdapter ;

    ArrayList<VoucherModel> voucherModels;
    List<String> voucherTitles= new ArrayList<String>();
    List<String> voucherIDs= new ArrayList<String>();
    ArrayAdapter<String> voucherAdapter ;

    List<ImageModel> imageModels = new ArrayList<>();
    List<String> imagesStr = new ArrayList<>();
    MYSQlDBHelper dbHelper;

    public NewLandPreSaleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        dbHelper = new MYSQlDBHelper(getContext());
        SharedPreferences user_pref = Objects.requireNonNull(getActivity()).getSharedPreferences(getString(R.string.user_shared_preference), Context.MODE_PRIVATE);
        SharedPreferences new_land_pref = Objects.requireNonNull(getActivity()).getSharedPreferences(getString(R.string.new_land_pref), Context.MODE_PRIVATE);
        String lng = new_land_pref.getString(Constants.NEW_LAND_LONGITUDE,"34.798315");
        String lat = new_land_pref.getString(Constants.NEW_LAND_LATITIUDE,"48.594898");
        mapLatLng =new LatLng( Double.parseDouble(lat),Double.parseDouble(lng));
        UID =  user_pref.getString("id","0");
        requestNewModel.setLatitude(Double.toString(mapLatLng.latitude));
        requestNewModel.setLongitude(Double.toString(mapLatLng.longitude));
        List<String>defaultUseTypeID=new ArrayList<>();
        defaultUseTypeID.add("1");
        requestNewModel.setUseTypeID(defaultUseTypeID);

    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_pre_sale, container, false);

//////////////////////////////////////Find Elements///////////////////////////////////////////////
        titleEtx = (EditText) view.findViewById(R.id.NewLandPreSaleFragmentTitleTxt);
        buildingConditionSpnr = (Spinner) view.findViewById(R.id.NewLandPreSaleFragmentBuildingConditionSpnr);
        landCaseSpnr = (Spinner) view.findViewById(R.id.NewLandPreSaleFragmentLandCaseSpnr);
        landTypeSpnr = (Spinner) view.findViewById(R.id.NewLandPreSaleFragmentLandTypeSpnr);
        landUseTypeSpnr = (MultiSelectSpinner) view.findViewById(R.id.NewLandPreSaleFragmentLandUseTypeMltSpnr);
        dongETxt = (EditText) view.findViewById(R.id.NewLandPreSaleFragmentDongTxt);
        voucherTypeSpnr = (Spinner)view.findViewById(R.id.NewLandPreSaleFragmentVoucherTypeSpnr);
        buildingYearETxt = (EditText) view.findViewById(R.id.NewLandPreSaleFragmentBuildingYearTxt);
        spaceFoundationETxt = (EditText) view.findViewById(R.id.NewLandPreSaleFragmentFoundationSpaceTxt);
        totalPriceETxt = (EditText) view.findViewById(R.id.NewLandPreSaleFragmentTotalPriceTxt);
        debtTotalPriceETxt = (EditText) view.findViewById(R.id.NewLandPreSaleFragmentDebtTotalPriceTxt);
        prePayPrice = (EditText)view.findViewById(R.id.NewLandPreSaleFragmentPrePayTotalPriceTxt);
        loanTypeSpnr = (Spinner) view.findViewById(R.id.NewLandPreSaleFragmentLoanTypeSpnr);
        whichFloorSpnr = (Spinner) view.findViewById(R.id.NewLandPreSaleFragmentWichFloorSpnr);
        roomCountSpnr = (Spinner) view.findViewById(R.id.NewLandPreSaleFragmentRoomCountSpnr);
        floorCountSpnr = (Spinner) view.findViewById(R.id.NewLandPreSaleFragmentFloorCountSpnr);
        unitInFloorSpnr = (Spinner) view.findViewById(R.id.NewLandPreSaleFragmentUnitInFloorSpnr);
        provinceSpnr = (Spinner) view.findViewById(R.id.NewLandPreSaleFragmentProvinceSpnr);
        citySpnr = (Spinner) view.findViewById(R.id.NewLandPreSaleFragmentCitySpnr);
        areaSpnr = (Spinner) view.findViewById(R.id.NewLandPreSaleFragmentAreaSpnr);
        districtSpnr = (Spinner) view.findViewById(R.id.NewLandPreSaleFragmentDistrictSpnr);
        addressETxt = (EditText) view.findViewById(R.id.NewLandPreSaleFragmentAddressTxt);
        landViewSpnr = (Spinner) view.findViewById(R.id.NewLandPreSaleFragmentLandViewSpnr);
        equipmentSpnr = (MultiSelectSpinner) view.findViewById(R.id.NewLandPreSaleFragmentEquipmentsSpnr);
        floorCoveringSpnr = (Spinner) view.findViewById(R.id.NewLandPreSaleFragmentFloorCoveringSpnr);
//        kitchenServicesSpnr = (Spinner) view.findViewById(R.id.NewLandPreSaleFragmentKitchenServicesSpnr);
        directionSpnr = (Spinner) view.findViewById(R.id.NewLandPreSaleFragmentDirectionSpnr);
        waterSpnr = (Spinner) view.findViewById(R.id.NewLandPreSaleFragmentWaterSpnr);
        gasSpnr = (Spinner) view.findViewById(R.id.NewLandPreSaleFragmentGasSpnr);
        electricitySpnr = (Spinner) view.findViewById(R.id.NewLandPreSaleFragmentElectricitySpnr);
        phoneSpnr = (Spinner) view.findViewById(R.id.NewLandPreSaleFragmentPhoneSpnr);
        descriptionETxt = (EditText) view.findViewById(R.id.NewLandPreSaleFragmentDescriptionTxt);
        deliveryDate = (EditText)view.findViewById(R.id.NewLandPreSaleFragmentDeliveryDateETxt);
        voucherDate = (EditText)view.findViewById(R.id.NewLandPreSaleFragmentVoucherDateETxt);
        landMapView = (MapView) view.findViewById(R.id.mapView);
        submitBtn = (Button) view.findViewById(R.id.NewLandPreSaleFragmentSubmitBtn);
        selectedImagesExpandableGrid = (ExpandableHeightGridView) view.findViewById(R.id.NewLandPreSaleFragmentGalleryExpandableGrid);
        addPhotoBtn = (Button)view.findViewById(R.id.NewLandPreSaleFragmentAddPhotoBtn);
        addMapBtn = (Button)view.findViewById(R.id.NewLandPreSaleFragmentAddMapBtn);
        onMapTxt = (EditText) view.findViewById(R.id.NewLandPreSaleFragmentOnMapTxt);

        mainlinearLayout = (LinearLayout)view.findViewById(R.id.NewPreSalemainLaouyt);
        persianDate = new PersianDate();
        datePicker = DatePickerDialog.newInstance(
                this,
                persianCalendar.getPersianYear(),
                persianCalendar.getPersianMonth(),
                persianCalendar.getPersianDay()
        );
        datePicker.setMinDate(persianCalendar);
        ///////////////////////Read inputs/////////////////////////////


///////////////////////////Load map//////////////////////////////////////////////
        loadMap();
        mainlinearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                return false;
            }
        });
////////////////////Register Context Menu for images/////////////////////////////
//        registerForContextMenu(imageOne);


////////////////////// Building Conditions Spinner////////////////////////////////
        buildingConditionModels = dbHelper.GetBuildingConditionsList();
        for (BuildingConditionModel Item : buildingConditionModels) {
            buildingConditionTitles.add(Item.getTitle());
            buildingConditionIDs.add(Item.getId());
        }
        buildingConditionAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, buildingConditionTitles);
        buildingConditionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        buildingConditionSpnr.setAdapter(buildingConditionAdapter);
        buildingConditionSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                requestNewModel.setBuildingConditionID( buildingConditionIDs.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


//////////////////////////////// Building Conditions Spinner////////////////////////////////
        buildingConditionModels = dbHelper.GetBuildingConditionsList();
        for (BuildingConditionModel Item : buildingConditionModels) {
            buildingConditionTitles.add(Item.getTitle());
            buildingConditionIDs.add(Item.getId());
        }
        buildingConditionAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, buildingConditionTitles);
        buildingConditionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        buildingConditionSpnr.setAdapter(buildingConditionAdapter);
        buildingConditionSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                requestNewModel.setBuildingConditionID(buildingConditionIDs.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

////////////////////// land Case Spinner////////////////////////////////
        landCaseTypeModels = dbHelper.GetLandCaseTypesList();
        for (LandCaseTypeModel Item : landCaseTypeModels) {
            landCaseTitles.add(Item.getTitle());
            landCaseIDs.add(Item.getId());
        }
        landCaseAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, landCaseTitles);
        landCaseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        landCaseSpnr.setAdapter(landCaseAdapter);
        landCaseSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                requestNewModel.setLandCaseID(landCaseIDs.get(i));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        ////////////////////// land Type Spinner////////////////////////////////
        landTypeModels = dbHelper.GetLandTypeList();
        for (LandTypeModel Item : landTypeModels) {
            landTypeTitles.add(Item.getTitle());
            landTypeIDs.add(Item.getId());
        }
        landTypeAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, landTypeTitles);
        landTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        landTypeSpnr.setAdapter(landTypeAdapter);
        landTypeSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                requestNewModel.setLandTypeID(landTypeIDs.get(i));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        ////////////////////// Rental Preference Spinner////////////////////////////////
        rentalPreferenceModels = dbHelper.GetRentalPreferenceList();
        for (RentalPreferenceModel Item : rentalPreferenceModels) {
            rentalPreferenceTitles.add(Item.getTitle());
            rentalPreferenceIDs.add(Item.getId());
        }
        rentalPreferenceAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, rentalPreferenceTitles);
        rentalPreferenceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ////////////////////// Loan Type Spinner////////////////////////////////
        loanTypeModels = dbHelper.GetLoanTypesList();
        for (LoanTypeModel Item : loanTypeModels) {
            loanTypeTitles.add(Item.getTitle());
            loanTypeIDs.add(Item.getId());
        }
        loanTypeAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, loanTypeTitles);
        loanTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        loanTypeSpnr.setAdapter(loanTypeAdapter);
        loanTypeSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                requestNewModel.setLoanTypeID(loanTypeIDs.get(i));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        ////////////////////// Province Spinner////////////////////////////////
        provinceModels = dbHelper.GetProvincesList();
        for (ProvinceModel Item : provinceModels) {
            provinceTitles.add(Item.getTitle());
            provinceIDs.add(Item.getId());
        }
        provinceAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, provinceTitles);
        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        provinceSpnr.setAdapter(provinceAdapter);
        provinceSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ////////////////////// City Spinner////////////////////////////////
                cityModels = dbHelper.GetCitiesByProvinceID(provinceIDs.get(i));
                cityTitles.clear();cityIDs.clear();
                for(CityModel item : cityModels){
                    cityTitles.add(item.getTitle());
                    cityIDs.add(item.getId());
                }
                cityAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getContext()), android.R.layout.simple_spinner_item, cityTitles);
                citySpnr.setAdapter(cityAdapter);
                requestNewModel.setProvinceID( provinceIDs.get(i));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        citySpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                requestNewModel.setCityID( cityIDs.get(i));
                ////////////////////// Area Spinner////////////////////////////////
                areaModels = dbHelper.GetAreasByCityID(cityIDs.get(i));
                areaTitles.clear();areaIDs.clear();
                for(AreaModel item : areaModels){
                    areaTitles.add(item.getTitle());
                    areaIDs.add(item.getId());
                }
                areaAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getContext()), android.R.layout.simple_spinner_item, areaTitles);
                areaSpnr.setAdapter(areaAdapter);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        areaSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                requestNewModel.setAreaID( areaIDs.get(i));
                ////////////////////// District Spinner////////////////////////////////
                districtModels = dbHelper.GetDistrictsByAreaID(areaIDs.get(i));
                districtIDs.clear();districtTitles.clear();
                for (DistrictModel Item : districtModels) {
                    districtTitles.add(Item.getTitle());
                    districtIDs.add(Item.getId());
                }
                districtAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getContext()), android.R.layout.simple_spinner_item, districtTitles);
                districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                districtSpnr.setAdapter(districtAdapter);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        ////////////////////// District Spinner////////////////////////////////
        districtSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                requestNewModel.setDistrictID( districtIDs.get(i));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        ////////////////////// floor covering Spinner////////////////////////////////
        floorCoveringModels = dbHelper.GetFloorCoveringList();
        for (FloorCoveringModel Item : floorCoveringModels) {
            floorCoveringTitles.add(Item.getTitle());
            floorCoveringIDs.add(Item.getId());
        }
        floorCoveringAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, floorCoveringTitles);
        floorCoveringAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        floorCoveringSpnr.setAdapter(floorCoveringAdapter);
        floorCoveringSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                requestNewModel.setFloorCoveringID(floorCoveringIDs.get(i));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        ////////////////////// kitchen service Spinner////////////////////////////////
//        kitchenServiceModels = dbHelper.GetKitchenServicesList();
//        for (KitchenServiceModel Item : kitchenServiceModels) {
//            kitchenServiceTitles.add(Item.getTitle());
//            kitchenServiceIDs.add(Item.getId());
//        }
//        kitchenServiceAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, kitchenServiceTitles);
//        kitchenServiceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        kitchenServicesSpnr.setAdapter(kitchenServiceAdapter);
//        kitchenServicesSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                requestNewModel.setKitchenServiceID( kitchenServiceIDs.get(i));
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//            }
//        });


        ////////////////////// Province Spinner////////////////////////////////
        landDirectionModels = dbHelper.GetLandDirectionsList();
        for (LandDirectionModel Item : landDirectionModels) {
            landDirectionTitles.add(Item.getTitle());
            landDirectionTypeIDs.add(Item.getId());
        }
        landDirectionAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, landDirectionTitles);
        landDirectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        directionSpnr.setAdapter(landDirectionAdapter);
        directionSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                requestNewModel.setDirectionID( landDirectionTypeIDs.get(i));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        ////////////////////// LandView Spinner////////////////////////////////
        landViewModels = dbHelper.GetLandViewsList();
        for (LandViewModel Item : landViewModels) {
            landViewTitles.add(Item.getTitle());
            landViewIDs.add(Item.getId());
        }
        landViewAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, landViewTitles);
        landViewAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        landViewSpnr.setAdapter(landViewAdapter);
        landViewSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                requestNewModel.setLandViewID( landViewIDs.get(i));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        //////////////////Voucher + Ex,Pre Voucher Spinner/////////////////////////////////////////////////
        voucherModels = dbHelper.GetVouchersList();
        for (VoucherModel Item : voucherModels) {
            voucherTitles.add(Item.getTitle());
            voucherIDs.add(Item.getId());
        }
        voucherAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, voucherTitles);
        voucherAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        voucherTypeSpnr.setAdapter(voucherAdapter);
        voucherTypeSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                requestNewModel.setPreVoucherTypeID( voucherIDs.get(i));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        ////////////////////// UseType MultiSelect Spinner////////////////////////////////
        useTypeModels = dbHelper.GetUseTypeList();
        for (UseTypeModel Item : useTypeModels) {
            useTypeTitles.add(Item.getTitle());
            useTypeIDs.add(Item.getId());
        }
        useTypeAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext())
                , android.R.layout.simple_list_item_multiple_choice, useTypeTitles);
        landUseTypeSpnr.setListAdapter(useTypeAdapter)
                .setListener(new BaseMultiSelectSpinner.MultiSpinnerListener() {
                    @Override
                    public void onItemsSelected(boolean[] selected) {
                        for (int i=0 ; i<selected.length;i++){
                            if(selected[i]) selectedUseTypes.add(useTypeIDs.get(i));
                        }
                        requestNewModel.setUseTypeID(selectedUseTypes);
                    }
                });

///////////////////////////Get Equipments////////////////////////////////////////////////////////
        equipmentModels = dbHelper.GetLandEquipmentsList();
        for (EquipmentModel Item : equipmentModels) {
            equipmentTitles.add(Item.getTitle());
            equipmentIDs.add(Item.getId());
        }
        equipmentAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext())
                , android.R.layout.simple_list_item_multiple_choice, equipmentTitles);
        equipmentSpnr.setListAdapter(equipmentAdapter)
                .setListener(new BaseMultiSelectSpinner.MultiSpinnerListener() {
                    @Override
                    public void onItemsSelected(boolean[] selected) {
                        for (int i=0 ; i<selected.length;i++){
                            if(selected[i]) selectedEquipments.add(equipmentIDs.get(i));
                        }
                        requestNewModel.setEquipment(selectedEquipments);
                    }
                });

        ///////////////////////Date Picker /////////////////////////////////
//        deliveryDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d(TAG, "onClick: ");
//                datePicker.show(getActivity().getFragmentManager(), "Datepickerdialog");
//            }
//        });
        deliveryDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                                    datePicker.show(getActivity().getFragmentManager(), "Datepickerdialog");

                }
                return false;
            }
        });

        addPhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageModels.size()!=5){
                    startMultiImagesGallery();
                }
                else
                {
                    Toast.makeText(getContext(),getResources().getString(R.string.max_images_selected),Toast.LENGTH_LONG).show();
                }
            }
        });
        addMapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavController controller = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
                Bundle args = new Bundle();
                args.putDouble("Latitude",mapLatLng.latitude);
                args.putDouble("Longitude",mapLatLng.longitude);
                controller.navigate(R.id.selectMapFragment,args);
                selectMapClicked=true;

            }
        });


        if(selectMapClicked){onMapTxt.requestFocus();
        }


        totalPriceETxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                totalPriceETxt.removeTextChangedListener(this);

                try {
                    String originalString = editable.toString();

                    Long longval;
                    if (originalString.contains(",")) {
                        originalString = originalString.replaceAll(",", "");
                    }
                    longval = Long.parseLong(originalString);

                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                    formatter.applyPattern("#,###,###,###");
                    String formattedString = formatter.format(longval);

                    //setting text after format to EditText
                    totalPriceETxt.setText(formattedString);
                    totalPriceETxt.setSelection(totalPriceETxt.getText().length());
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                totalPriceETxt.addTextChangedListener(this);
            }
        });
        prePayPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                prePayPrice.removeTextChangedListener(this);

                try {
                    String originalString = editable.toString();

                    Long longval;
                    if (originalString.contains(",")) {
                        originalString = originalString.replaceAll(",", "");
                    }
                    longval = Long.parseLong(originalString);

                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                    formatter.applyPattern("#,###,###,###");
                    String formattedString = formatter.format(longval);

                    //setting text after format to EditText
                    prePayPrice.setText(formattedString);
                    prePayPrice.setSelection(prePayPrice.getText().length());
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                prePayPrice.addTextChangedListener(this);
            }
        });
        debtTotalPriceETxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                debtTotalPriceETxt.removeTextChangedListener(this);

                try {
                    String originalString = editable.toString();

                    Long longval;
                    if (originalString.contains(",")) {
                        originalString = originalString.replaceAll(",", "");
                    }
                    longval = Long.parseLong(originalString);

                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                    formatter.applyPattern("#,###,###,###");
                    String formattedString = formatter.format(longval);

                    //setting text after format to EditText
                    debtTotalPriceETxt.setText(formattedString);
                    debtTotalPriceETxt.setSelection(debtTotalPriceETxt.getText().length());
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                debtTotalPriceETxt.addTextChangedListener(this);
            }
        });



//////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(titleEtx.getText().toString())) {
                    titleEtx.setError(getResources().getString(R.string.title_input_error_msg));
                    titleEtx.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(totalPriceETxt.getText().toString())) {
                    totalPriceETxt.setError(getResources().getString(R.string.price_input_error_msg));
                    totalPriceETxt.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(dongETxt.getText().toString())) {
                    dongETxt.setError(getResources().getString(R.string.dong_input_error_msg));
                    dongETxt.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(deliveryDate.getText().toString())) {
                    deliveryDate.setError(getResources().getString(R.string.date_input_error_msg));
                    deliveryDate.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(spaceFoundationETxt.getText().toString())) {
                    spaceFoundationETxt.setError(getResources().getString(R.string.foundation_space_input_error_msg));
                    spaceFoundationETxt.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(buildingYearETxt.getText().toString())) {
                    buildingYearETxt.setError(getResources().getString(R.string.building_year_input_error_msg));
                    buildingYearETxt.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(debtTotalPriceETxt.getText().toString())) {
                    debtTotalPriceETxt.setError(getResources().getString(R.string.loan_price_input_error_msg));
                    debtTotalPriceETxt.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(prePayPrice.getText().toString())) {
                    prePayPrice.setError(getResources().getString(R.string.pre_pay_input_error_msg));
                    prePayPrice.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(addressETxt.getText().toString())) {
                    addressETxt.setError(getResources().getString(R.string.address_input_error_msg));
                    addressETxt.requestFocus();
                    return;
                }


                requestNewModel.setTitle(titleEtx.getText().toString());
                requestNewModel.setPreDong(dongETxt.getText().toString());
                requestNewModel.setPreSaleTotalPrice(totalPriceETxt.getText().toString().replace(",",""));
                requestNewModel.setDescription(descriptionETxt.getText().toString());
                requestNewModel.setAddress(addressETxt.getText().toString());
                requestNewModel.setBuildingYear(buildingYearETxt.getText().toString());
                requestNewModel.setDebtTotalPrice(debtTotalPriceETxt.getText().toString().replace(",",""));
                requestNewModel.setPrePayPrice(prePayPrice.getText().toString().replace(",",""));
                requestNewModel.setFoundationSpace(spaceFoundationETxt.getText().toString());
                requestNewModel.setRoomCount(Long.toString(roomCountSpnr.getSelectedItemId()));
                requestNewModel.setFloorCount(Long.toString(floorCountSpnr.getSelectedItemId()));
                requestNewModel.setUnitInFloor(Long.toString(unitInFloorSpnr.getSelectedItemId()));
                requestNewModel.setFloor(Long.toString(floorCountSpnr.getSelectedItemId()));
                requestNewModel.setWater(Long.toString(waterSpnr.getSelectedItemId()));
                requestNewModel.setGas(Long.toString(gasSpnr.getSelectedItemId()));
                requestNewModel.setElectricy(Long.toString(electricitySpnr.getSelectedItemId()));
                requestNewModel.setPhone(Long.toString(phoneSpnr.getSelectedItemId()));
                requestNewModel.setLandStateID("3");
                requestNewModel.setUID(UID);
                requestNewModel.setWater(Long.toString(waterSpnr.getSelectedItemId()));
                requestNewModel.setGas(Long.toString(gasSpnr.getSelectedItemId()));
                requestNewModel.setElectricy(Long.toString(electricitySpnr.getSelectedItemId()));
                requestNewModel.setPhone(Long.toString(phoneSpnr.getSelectedItemId()));
                requestNewModel.setDeliveryDate(deliveryDateStr);
                SelectedImageRecyclerViewAdapter imageRecyclerViewAdapter = null;
                imageRecyclerViewAdapter = (SelectedImageRecyclerViewAdapter) selectedImagesExpandableGrid.getAdapter();
                if(imageRecyclerViewAdapter!=null){
                    List<ImageModel> selectedImageModels = imageRecyclerViewAdapter.imageModels;
                    for (int i = 0; i < selectedImageModels.size(); i++) {
                        imagesStr.add(selectedImageModels.get(i).getImageStrPath());
                    }
                    requestNewModel.setImageFile(imagesStr);
                }

                try {
                    RetrofitPost();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }



////////////////////////////////pickUp Image ////////////////////////////////////
    public void startMultiImagesGallery(){
        Intent intent = new Intent(getActivity(), AlbumSelectActivity.class);
        intent.setType("image/*");
        intent.putExtra(com.darsh.multipleimageselect.helpers.Constants.INTENT_EXTRA_LIMIT,PICK_IMAGES-imageModels.size());
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(intent, 2000);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2000 && resultCode == Activity.RESULT_OK && data != null) {
            ArrayList<Image> images = data.getParcelableArrayListExtra(com.darsh.multipleimageselect.helpers.Constants.INTENT_EXTRA_IMAGES);
            ImageModel imageModel;
            for (int i = 0, l = images.size(); i < l; i++) {
                imageModel = new ImageModel(Uri.fromFile(new File(images.get(i).path)));
                imageModels.add(imageModel);

            }
            SelectedImageRecyclerViewAdapter selectedImageRecyclerViewAdapter = new SelectedImageRecyclerViewAdapter(imageModels,getActivity());
            selectedImagesExpandableGrid.setAdapter(selectedImageRecyclerViewAdapter);
            selectedImagesExpandableGrid.setExpanded(true);
        }
    }



//////////////////////////Get Map//////////////////////////////////

    @Override
    public void onViewCreated(@NonNull View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    void loadMap(){
        if(landMapView !=null){
            landMapView.onCreate(null);
            landMapView.onResume();
            landMapView.getMapAsync(this);
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

    }
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String pickedDate = year+"-"+(monthOfYear+1)+"-"+dayOfMonth;
        deliveryDate.setText(pickedDate);
        int[] gDateInt = persianDate.toGregorian(year,monthOfYear,dayOfMonth);

        int gday = gDateInt[2];
        int gmonth = gDateInt[1];
        int gyear =gDateInt[0];


        Calendar c = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        c.set(gyear, gmonth, gday, 0, 0);
        Date date = null;
        String Str=null;
        try {
            date = inputFormat.parse(c.getTime().toString());
            Str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "onDateSet Str: "+Str+"    date:" +date.toString());

        deliveryDateStr = Str;


    }
    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences new_land_pref = Objects.requireNonNull(getActivity()).getSharedPreferences(getString(R.string.new_land_pref), Context.MODE_PRIVATE);
        String lng = new_land_pref.getString(Constants.NEW_LAND_LONGITUDE,"34.798315");
        String lat = new_land_pref.getString(Constants.NEW_LAND_LATITIUDE,"48.594898");
        mapLatLng =new LatLng( Double.parseDouble(lat),Double.parseDouble(lng));
        requestNewModel.setLatitude(lat);
        requestNewModel.setLongitude(lng);
        loadMap();

    }


    private void RetrofitPost() throws JSONException {
        final String THISTAG = "NewPrtcption RetroPost";
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage(getResources().getString(R.string.loading_message));
        progressDialog.show();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d(THISTAG, "log log: " + message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        final Retrofit myRetrofit = new Retrofit.Builder().baseUrl("https://hamedanmelk.ir")
                .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();

        RetrofitInterface RI = myRetrofit.create(RetrofitInterface.class);
        Call<myResponse> uploadResponse = RI.UploadNewLand(  requestNewModel.getmultipartBodyPart(),requestNewModel.getImagesHashMap());
        uploadResponse.enqueue(new Callback<myResponse>() {

            @Override
            public void onResponse(Call<myResponse> call, retrofit2.Response<myResponse> response) {
                if (progressDialog.isShowing())progressDialog.dismiss();
                if(response.body().getData().contains("Success")){
                    Toast.makeText(getContext(),getResources().getString(R.string.success_new_land),Toast.LENGTH_LONG).show();
                    NavController controller = Navigation.findNavController(Objects.requireNonNull(getActivity()),R.id.nav_host_fragment);
                    controller.navigate(R.id.navigation_home);
                }
                else {
                    Toast.makeText(getContext(),getResources().getString(R.string.fail_msg),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<myResponse> call, Throwable t) {
                if (progressDialog.isShowing())progressDialog.dismiss();
                Toast.makeText(getContext(),getResources().getString(R.string.fail_msg),Toast.LENGTH_LONG).show();
            }
        });


    }



}
