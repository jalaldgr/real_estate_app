package ir.hamedanmelk.hamedanmelk.ui.newland;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.gson.Gson;
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.apptik.widget.multiselectspinner.BaseMultiSelectSpinner;
import io.apptik.widget.multiselectspinner.MultiSelectSpinner;
import ir.hamedanmelk.hamedanmelk.R;
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
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.FilePath;
import ir.hamedanmelk.hamedanmelk.tools.MYSQlDBHelper;
import ir.hamedanmelk.hamedanmelk.tools.Urls;
import ir.hamsaa.persiandatepicker.util.PersianDateParser;
import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

public class NewLandFragment extends Fragment  implements OnMapReadyCallback, DatePickerDialog.OnDateSetListener{
    private RequestQueue myRequestQueue;
    private JsonObjectRequest myJsonObjectRequest;

    public static final int PICK_IMAGES = 2;
    private static final String TAG = "NewLandFragment";
    public String selectedImage="one";
    public List<String> selectedImages = new ArrayList<>();
    public List<String> selectedUseTypes = new ArrayList<>();
    public List<String> selectedEquipments = new ArrayList<>();

    NewLandModel requestNewModel= new NewLandModel();

    String   selectedFileStr;
    Uri      selectedFileUri;
    String   UID;
    EditText titleEtx;
    Spinner  buildingConditionSpnr;
    Spinner landCaseSpnr;
    Spinner landTypeSpnr;
    MultiSelectSpinner landUseTypeSpnr;
    Spinner voucherTypeSpnr;
    Spinner preVoucherTypeSpnr;
    Spinner exVoucherTypeSpnr;
    EditText dongETxt;
    EditText preDongETxt;
    EditText exDongETxt;
    EditText buildingYearETxt;
    EditText spaceFoundationETxt;
    EditText deliveryETxt;
    MaterialCheckBox residentOwnerChkBx;
    Spinner rentalPreferenceSpnr;
    MaterialCheckBox exchangeChkBx;
    EditText totalPriceETxt;
    EditText preSalePriceETxt;
    EditText prePayPriceETxt;
    EditText debtTotalPriceETxt;
    Spinner loanTypeSpnr;
    EditText mortgageTotalPriceETxt;
    EditText totalRentPriceETxt;
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
    Spinner kitchenServicesSpnr;
    Spinner directionSpnr;
    Spinner landStateSpnr;///////// Land State=> ads type
    Spinner waterSpnr;
    Spinner gasSpnr;
    Spinner electricitySpnr;
    Spinner phoneSpnr;
    EditText descriptionETxt;
    MapView landMapView;
    LinearLayout selectedImagesGrid;
    ImageView imageOne;
    ImageView imageTwo;
    ImageView imageThree;
    ImageView imageFour;
    ImageView imageFive;
    com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog datePicker;
    Date grgDate;
    PersianDate persianDate;
    PersianCalendar persianCalendar = new PersianCalendar();

    Button   submitBtn;

    GoogleMap mgoogleMap;
    private Marker mapMarker;
    private LatLng mapLatLng = new LatLng(Constants.MAP_MEYDAN_LAT,Constants.MAP_MEYDAN_LNG);
    private boolean mapLoadedFLAG=false;

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

    ArrayList<LandStateTypeModel> landStateTypeModels;
    List<String> landStateTitles= new ArrayList<String>();
    List<String> landStateIDs= new ArrayList<String>();
    ArrayAdapter<String> landStateAdapter ;

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


    MYSQlDBHelper dbHelper;

    public NewLandFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        dbHelper = new MYSQlDBHelper(getContext());
        SharedPreferences user_pref = Objects.requireNonNull(getActivity()).getSharedPreferences(getString(R.string.user_shared_preference), Context.MODE_PRIVATE);
        UID =  user_pref.getString("id","0");
        requestNewModel.setLatitude(Double.toString(mapLatLng.latitude));
        requestNewModel.setLongitude(Double.toString(mapLatLng.longitude));

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_land, container, false);

//////////////////////////////////////Find Elements///////////////////////////////////////////////
        titleEtx = (EditText) view.findViewById(R.id.NewLandFragmentTitleTxt);
        buildingConditionSpnr = (Spinner) view.findViewById(R.id.NewLandFragmentBuildingConditionSpnr);
        landCaseSpnr = (Spinner) view.findViewById(R.id.NewLandFragmentLandCaseSpnr);
        landTypeSpnr = (Spinner) view.findViewById(R.id.NewLandFragmentLandTypeSpnr);
        landUseTypeSpnr = (MultiSelectSpinner) view.findViewById(R.id.NewLandFragmentLandUseTypeMltSpnr);
        voucherTypeSpnr = (Spinner) view.findViewById(R.id.NewLandFragmentVoucherTypeSpnr);
        preVoucherTypeSpnr = (Spinner) view.findViewById(R.id.NewLandFragmentPreVoucherTypeSpnr);
        exVoucherTypeSpnr = (Spinner) view.findViewById(R.id.NewLandFragmentExVoucherTypeSpnr);
        dongETxt = (EditText) view.findViewById(R.id.NewLandFragmentDongTxt);
        preDongETxt = (EditText) view.findViewById(R.id.NewLandFragmentPreDongTxt);
        exDongETxt = (EditText) view.findViewById(R.id.NewLandFragmentExDongTxt);
        buildingYearETxt = (EditText) view.findViewById(R.id.NewLandFragmentBuildingYearTxt);
        spaceFoundationETxt = (EditText) view.findViewById(R.id.NewLandFragmentFoundationSpaceTxt);
        deliveryETxt = (EditText) view.findViewById(R.id.NewLandFragmentDeliveryDateTxt);
        residentOwnerChkBx = (MaterialCheckBox) view.findViewById(R.id.NewLandFragmentResidentOwnerChkBx);
        rentalPreferenceSpnr = (Spinner) view.findViewById(R.id.NewLandFragmentRentalPreferenceSpnr);
        exchangeChkBx = (MaterialCheckBox) view.findViewById(R.id.NewLandFragmentExchangeChkBx);
        totalPriceETxt = (EditText) view.findViewById(R.id.NewLandFragmentTotalPriceTxt);
        preSalePriceETxt = (EditText) view.findViewById(R.id.NewLandFragmentPreSalePriceTxt);
        prePayPriceETxt = (EditText) view.findViewById(R.id.NewLandFragmentPrePayPriceTxt);
        debtTotalPriceETxt = (EditText) view.findViewById(R.id.NewLandFragmentDebtTotalPriceTxt);
        loanTypeSpnr = (Spinner) view.findViewById(R.id.NewLandFragmentLoanTypeSpnr);
        mortgageTotalPriceETxt = (EditText) view.findViewById(R.id.NewLandFragmentMortgGageTotalPriceTxt);
        totalRentPriceETxt = (EditText) view.findViewById(R.id.NewLandFragmentRentTotalPriceTxt);
        whichFloorSpnr = (Spinner) view.findViewById(R.id.NewLandFragmentWichFloorSpnr);
        roomCountSpnr = (Spinner) view.findViewById(R.id.NewLandFragmentRoomCountSpnr);
        floorCountSpnr = (Spinner) view.findViewById(R.id.NewLandFragmentFloorCountSpnr);
        unitInFloorSpnr = (Spinner) view.findViewById(R.id.NewLandFragmentUnitInFloorSpnr);
        provinceSpnr = (Spinner) view.findViewById(R.id.NewLandFragmentProvinceSpnr);
        citySpnr = (Spinner) view.findViewById(R.id.NewLandFragmentCitySpnr);
        areaSpnr = (Spinner) view.findViewById(R.id.NewLandFragmentAreaSpnr);
        districtSpnr = (Spinner) view.findViewById(R.id.NewLandFragmentDistrictSpnr);
        addressETxt = (EditText) view.findViewById(R.id.NewLandFragmentAddressTxt);
        landViewSpnr = (Spinner) view.findViewById(R.id.NewLandFragmentLandViewSpnr);
        equipmentSpnr = (MultiSelectSpinner) view.findViewById(R.id.NewLandFragmentEquipmentsSpnr);
        floorCoveringSpnr = (Spinner) view.findViewById(R.id.NewLandFragmentFloorCoveringSpnr);
        kitchenServicesSpnr = (Spinner) view.findViewById(R.id.NewLandFragmentKitchenServicesSpnr);
        directionSpnr = (Spinner) view.findViewById(R.id.NewLandFragmentDirectionSpnr);
        landStateSpnr = (Spinner) view.findViewById(R.id.NewLandFragmentAdTypeSpnr);
        waterSpnr = (Spinner) view.findViewById(R.id.NewLandFragmentWaterSpnr);
        gasSpnr = (Spinner) view.findViewById(R.id.NewLandFragmentGasSpnr);
        electricitySpnr = (Spinner) view.findViewById(R.id.NewLandFragmentElectricitySpnr);
        phoneSpnr = (Spinner) view.findViewById(R.id.NewLandFragmentPhoneSpnr);
        descriptionETxt = (EditText) view.findViewById(R.id.NewLandFragmentDescriptionTxt);
        landMapView = (MapView) view.findViewById(R.id.mapView);
        submitBtn = (Button) view.findViewById(R.id.NewLandFragmentSubmitBtn);
        selectedImagesGrid = (LinearLayout) view.findViewById(R.id.NewLandFragmentGalleryGrid);
        imageOne = (ImageView) view.findViewById(R.id.NewLandOneImg);
        imageTwo = (ImageView) view.findViewById(R.id.NewLandTwoImg);
        imageThree = (ImageView) view.findViewById(R.id.NewLandThreeImg);
        imageFour = (ImageView) view.findViewById(R.id.NewLandFourImg);
        imageFive = (ImageView) view.findViewById(R.id.NewLandFiveImg);
        persianDate = new PersianDate();
        datePicker = new DatePickerDialog();
        datePicker.setMinDate(persianCalendar);

        ///////////////////////Read inputs/////////////////////////////


///////////////////////////Load map//////////////////////////////////////////////
        loadMap();

////////////////////Register Context Menu for images/////////////////////////////
        registerForContextMenu(imageOne);
        registerForContextMenu(imageTwo);
        registerForContextMenu(imageThree);
        registerForContextMenu(imageFour);
        registerForContextMenu(imageFive);

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
        rentalPreferenceSpnr.setAdapter(rentalPreferenceAdapter);
        rentalPreferenceSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                requestNewModel.setRentalPreferenceID(rentalPreferenceIDs.get(i));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


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
                requestNewModel.setProvinceID( provinceIDs.get(i));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        ////////////////////// City Spinner////////////////////////////////
        cityModels = dbHelper.GetCitiesList();
        for (CityModel Item : cityModels) {
            cityTitles.add(Item.getTitle());
            cityIDs.add(Item.getId());
        }
        cityAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, cityTitles);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpnr.setAdapter(cityAdapter);
        citySpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                requestNewModel.setCityID( cityIDs.get(i));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        ////////////////////// Area Spinner////////////////////////////////
        areaModels = dbHelper.GetAreaList();
        for (AreaModel Item : areaModels) {
            areaTitles.add(Item.getTitle());
            areaIDs.add(Item.getId());
        }
        areaAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, areaTitles);
        areaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        areaSpnr.setAdapter(areaAdapter);
        areaSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                requestNewModel.setAreaID( areaIDs.get(i));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        ////////////////////// District Spinner////////////////////////////////
        districtModels = dbHelper.GetDistrictList();
        for (DistrictModel Item : districtModels) {
            districtTitles.add(Item.getTitle());
            districtIDs.add(Item.getId());
        }
        districtAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, districtTitles);
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        districtSpnr.setAdapter(districtAdapter);
        districtSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                requestNewModel.setDistrictID( districtIDs.get(i));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        ////////////////////// District Spinner////////////////////////////////
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


        ////////////////////// Province Spinner////////////////////////////////
        kitchenServiceModels = dbHelper.GetKitchenServicesList();
        for (KitchenServiceModel Item : kitchenServiceModels) {
            kitchenServiceTitles.add(Item.getTitle());
            kitchenServiceIDs.add(Item.getId());
        }
        kitchenServiceAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, kitchenServiceTitles);
        kitchenServiceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kitchenServicesSpnr.setAdapter(kitchenServiceAdapter);
        kitchenServicesSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                requestNewModel.setKitchenServiceID( kitchenServiceIDs.get(i));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        ////////////////////// Province Spinner////////////////////////////////
        landDirectionModels = dbHelper.GetLandDirectionsList();
        Log.d(TAG, "onCreateView direction: "+landDirectionModels.size());
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


        ////////////////////// Province Spinner////////////////////////////////
        landStateTypeModels = dbHelper.GetLandStateList();
        for (LandStateTypeModel Item : landStateTypeModels) {
            landStateTitles.add(Item.getTitle());
            landStateIDs.add(Item.getId());
            Log.d(TAG, "onCreateView landStateIDs: "+Item.getTitle());
        }
        landStateAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, landStateTitles);
        landStateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        landStateSpnr.setAdapter(landStateAdapter);
        landStateSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                requestNewModel = new NewLandModel();
                requestNewModel.setLandStateID( landStateIDs.get(i));
                switch (i){
                    case 0:
                        loanTypeSpnr.setVisibility(View.VISIBLE);
                        totalPriceETxt.setVisibility(View.VISIBLE);
                        debtTotalPriceETxt.setVisibility(View.VISIBLE);
                        preDongETxt.setVisibility(View.GONE);
                        exDongETxt.setVisibility(View.GONE);
                        preSalePriceETxt.setVisibility(View.GONE);
                        preVoucherTypeSpnr.setVisibility(View.GONE);
                        totalRentPriceETxt.setVisibility(View.GONE);
                        mortgageTotalPriceETxt.setVisibility(View.GONE);
                        rentalPreferenceSpnr.setVisibility(View.GONE);
                        deliveryETxt.setVisibility(View.GONE);
                        residentOwnerChkBx.setVisibility(View.GONE);
                        break;

                    case 1:
                        totalRentPriceETxt.setVisibility(View.VISIBLE);
                        mortgageTotalPriceETxt.setVisibility(View.VISIBLE);
                        rentalPreferenceSpnr.setVisibility(View.VISIBLE);
                        totalPriceETxt.setVisibility(View.GONE);
                        prePayPriceETxt.setVisibility(View.GONE);
                        voucherTypeSpnr.setVisibility(View.GONE);
                        preVoucherTypeSpnr.setVisibility(View.GONE);
                        exVoucherTypeSpnr.setVisibility(View.GONE);
                        loanTypeSpnr.setVisibility(View.GONE);
                        dongETxt.setVisibility(View.GONE);
                        exDongETxt.setVisibility(View.GONE);
                        preDongETxt.setVisibility(View.GONE);
                        deliveryETxt.setVisibility(View.GONE);
                        exchangeChkBx.setVisibility(View.GONE);
                        debtTotalPriceETxt.setVisibility(View.GONE);
                        preSalePriceETxt.setVisibility(View.GONE);
                        break;

                    case 2:
                        prePayPriceETxt.setVisibility(View.VISIBLE);
                        preVoucherTypeSpnr.setVisibility(View.VISIBLE);
                        preSalePriceETxt.setVisibility(View.VISIBLE);
                        preVoucherTypeSpnr.setVisibility(View.VISIBLE);
                        deliveryETxt.setVisibility(View.VISIBLE);
                        preDongETxt.setVisibility(View.VISIBLE);
                        totalRentPriceETxt.setVisibility(View.GONE);
                        mortgageTotalPriceETxt.setVisibility(View.GONE);
                        dongETxt.setVisibility(View.GONE);
                        exVoucherTypeSpnr.setVisibility(View.GONE);
                        exDongETxt.setVisibility(View.GONE);
                        exVoucherTypeSpnr.setVisibility(View.GONE);
                        voucherTypeSpnr.setVisibility(View.GONE);
                        loanTypeSpnr.setVisibility(View.GONE);
                        rentalPreferenceSpnr.setVisibility(View.GONE);
                        debtTotalPriceETxt.setVisibility(View.GONE);
                        exchangeChkBx.setVisibility(View.GONE);

                        break;

                    case 3:
                        mortgageTotalPriceETxt.setVisibility(View.GONE);
                        totalRentPriceETxt.setVisibility(View.GONE);
                        totalPriceETxt.setVisibility(View.GONE);
                        prePayPriceETxt.setVisibility(View.GONE);
                        debtTotalPriceETxt.setVisibility(View.GONE);
                        totalPriceETxt.setVisibility(View.GONE);
                        preVoucherTypeSpnr.setVisibility(View.GONE);
                        exVoucherTypeSpnr.setVisibility(View.GONE);
                        exDongETxt.setVisibility(View.GONE);
                        preDongETxt.setVisibility(View.GONE);
                        dongETxt.setVisibility(View.GONE);
                        debtTotalPriceETxt.setVisibility(View.GONE);
                        deliveryETxt.setVisibility(View.GONE);
                        rentalPreferenceSpnr.setVisibility(View.GONE);
                        preSalePriceETxt.setVisibility(View.GONE);

                        break;
                }
                requestNewModel.setLandStateID( landStateIDs.get(i));
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
                requestNewModel.setVoucherTypeID( voucherIDs.get(i));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        exVoucherTypeSpnr.setAdapter(voucherAdapter);
        exVoucherTypeSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                requestNewModel.setExVoucherTypeID( voucherIDs.get(i));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        preVoucherTypeSpnr.setAdapter(voucherAdapter);
        preVoucherTypeSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


        residentOwnerChkBx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)requestNewModel.setResidentOwner(Constants.ONE);
                else requestNewModel.setResidentOwner(Constants.ZERO);
            }
        });

        exchangeChkBx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)requestNewModel.setChkExchanged(Constants.ONE);
                else requestNewModel.setChkExchanged(Constants.ZERO);
            }
        });





        /////////////////////////////////// Five Image //////////////////////////////////////
        imageOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageOne.showContextMenu();
                selectedImage = "one";
            }
        });
        imageTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageTwo.showContextMenu();
                selectedImage = "two";

            }
        });
        imageThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageThree.showContextMenu();
                selectedImage = "three";
            }
        });
        imageFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageFour.showContextMenu();
                selectedImage = "four";
            }
        });
        imageFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageFive.showContextMenu();
                selectedImage = "five";
            }
        });

////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////// Text inputs /////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
        titleEtx.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
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
                requestNewModel.setDebtTotalPrice(debtTotalPriceETxt.getText().toString());
            }
        });

        buildingYearETxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                requestNewModel.setBuildingYear(buildingYearETxt.getText().toString());
            }
        });

        addressETxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                requestNewModel.setAddress(addressETxt.getText().toString());
            }
        });

        deliveryETxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                requestNewModel.setDeliveryDate("2021-02-21");

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        deliveryETxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestNewModel.setDeliveryDate("2021-02-21");
                datePicker.show(getActivity().getFragmentManager(),"ffff");

            }
        });

        descriptionETxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                requestNewModel.setDescription(descriptionETxt.getText().toString());

            }
        });

        exDongETxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                requestNewModel.setExDong(exDongETxt.getText().toString());
            }
        });

        preDongETxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                requestNewModel.setPreDong(preDongETxt.getText().toString());
            }
        });

        dongETxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                requestNewModel.setDong(dongETxt.getText().toString());
            }
        });

        mortgageTotalPriceETxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                requestNewModel.setMortgageTotalPrice(mortgageTotalPriceETxt.getText().toString());
            }
        });

        prePayPriceETxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                requestNewModel.setPrePayPrice(prePayPriceETxt.getText().toString());
            }
        });

        preSalePriceETxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                requestNewModel.setPreSaleTotalPrice(preSalePriceETxt.getText().toString());
            }
        });

        spaceFoundationETxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                requestNewModel.setFoundationSpace(spaceFoundationETxt.getText().toString());
            }
        });

        totalPriceETxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                requestNewModel.setSaleTotalPrice(totalPriceETxt.getText().toString());
            }
        });

        totalRentPriceETxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                requestNewModel.setRentTotalPrice(totalRentPriceETxt.getText().toString());
            }
        });

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestNewModel.setTitle(titleEtx.getText().toString());
//                requestNewModel.setDong(dongETxt.getText().toString());
//                requestNewModel.setExDong(exDongETxt.getText().toString());
//                requestNewModel.setPreDong(preDongETxt.getText().toString());
//                requestNewModel.setSaleTotalPrice(totalPriceETxt.getText().toString());
//                requestNewModel.setPreSaleTotalPrice(preSalePriceETxt.getText().toString());
//                requestNewModel.setDescription(descriptionETxt.getText().toString());
//                requestNewModel.setAddress(addressETxt.getText().toString());
//                requestNewModel.setBuildingYear(buildingYearETxt.getText().toString());
//                requestNewModel.setDebtTotalPrice(debtTotalPriceETxt.getText().toString());
//                requestNewModel.setMortgageTotalPrice(mortgageTotalPriceETxt.getText().toString());
//                requestNewModel.setRentTotalPrice(totalRentPriceETxt.getText().toString());
//                requestNewModel.setPrePayPrice(prePayPriceETxt.getText().toString());
//                requestNewModel.setFoundationSpace(spaceFoundationETxt.getText().toString());
//                requestNewModel.setRoomCount(Long.toString(roomCountSpnr.getSelectedItemId()));
//                requestNewModel.setFloorCount(Long.toString(floorCountSpnr.getSelectedItemId()));
//                requestNewModel.setUnitInFloor(Long.toString(unitInFloorSpnr.getSelectedItemId()));
                requestNewModel.setFloor(Long.toString(floorCountSpnr.getSelectedItemId()));
                requestNewModel.setWater(Long.toString(waterSpnr.getSelectedItemId()));
                requestNewModel.setGas(Long.toString(gasSpnr.getSelectedItemId()));
                requestNewModel.setElectricy(Long.toString(electricitySpnr.getSelectedItemId()));
                requestNewModel.setPhone(Long.toString(phoneSpnr.getSelectedItemId()));
                if(residentOwnerChkBx.isChecked())requestNewModel.setResidentOwner(Constants.ONE);
                else requestNewModel.setResidentOwner(Constants.ZERO);
                if(exchangeChkBx.isChecked())requestNewModel.setChkExchanged(Constants.ONE);
                else requestNewModel.setChkExchanged(Constants.ZERO);
                requestNewModel.setUID(UID);


                //                requestNewModel.setImageFiles(eqStr);
                try {
                    HomeFragmentPOSTRequest(getContext());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }



//////////////////////////////////Context Menu///////////////////////////////////

    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = Objects.requireNonNull(getActivity()).getMenuInflater();
        inflater.inflate(R.menu.image_long_press_menu, menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onContextItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case R.id.select_image: {
                if(ActivityCompat.checkSelfPermission(Objects.requireNonNull(getActivity()),
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            2000);
                }
                else {
                    startGallery();
                }
            }
            break;
            case R.id.remove_image: {

                switch (selectedImage){
                    case "one":{
                        imageOne.setImageBitmap(null);
                        break;}
                    case "two":{
                        imageTwo.setImageBitmap(null);
                        break;}
                    case "three":{
                        imageThree.setImageBitmap(null);
                        break;}
                    case "four":{
                        imageFour.setImageBitmap(null);
                        break;}
                    case "five":{
                        imageFive.setImageBitmap(null);
                        break;}
                }

            }
            default:
                return false;
        }
        return false;
    }

    ////////////////////////////////pickUp Image ////////////////////////////////////

    private void startGallery() {
        Intent cameraIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        cameraIntent.setType("image/*");
        if (cameraIntent.resolveActivity(Objects.requireNonNull(getActivity()).getPackageManager()) != null) {
            startActivityForResult(cameraIntent, 1000);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super method removed
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1000) {
                selectedFileUri = data.getData();
                selectedFileStr = FilePath.getPath(getContext(),selectedFileUri);
                Bitmap bitmapImage = null;
                try {
                    bitmapImage = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(getActivity()).getContentResolver(), selectedFileUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                switch (selectedImage){
                    case "one":{
                        imageOne.setImageBitmap(bitmapImage);
                        selectedImage="one";
                        selectedImages.add(selectedFileStr);
                        break;}
                    case "two":{
                        imageTwo.setImageBitmap(bitmapImage);
                        selectedImage="two";
                        break;}
                    case "three":{
                        imageThree.setImageBitmap(bitmapImage);
                        selectedImage="three";
                        break;}
                    case "four":{
                        imageFour.setImageBitmap(bitmapImage);
                        selectedImage="four";
                        break;}
                    case "five":{
                        imageFive.setImageBitmap(bitmapImage);
                        selectedImage="five";
                        break;}
                }
            }
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

        mgoogleMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                mapLatLng = mgoogleMap.getCameraPosition().target;
                requestNewModel.setLatitude(Double.toString(mapLatLng.latitude));
                requestNewModel.setLongitude(Double.toString(mapLatLng.longitude));
            }
        });

    }

    private void HomeFragmentPOSTRequest(Context Cntx) throws JSONException {

        Map<String, String> postParam= new HashMap<String, String>();
        Gson gson = new Gson();
        JSONObject obj=null;
        try {
            String json = new Gson().toJson(requestNewModel);
            Log.d(TAG, "HomeFragmentPOSTRequest Gson>>>: "+json);
             obj = new JSONObject(json);
        }catch (Exception e){
            Log.d(TAG, "HomeFragmentPOSTRequest: "+e.toString());
        }

        final ProgressDialog progressDialog=new ProgressDialog(Cntx);
        progressDialog.setMessage(getResources().getString(R.string.loading_message));
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        myRequestQueue = Volley.newRequestQueue(Cntx);
        myJsonObjectRequest = new JsonObjectRequest(Request.Method.POST
                , Urls.getBaseURL()+Urls.getRegisterLand(), obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        Log.d(TAG, "onResponse: "+response.toString());
                        try {
                            if(response.getString(Constants.JSON_RESPONSE_DATA).contains("success"))
                                Toast.makeText(getContext(),"آگهی با موفقیت ثبت شد",Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),
                                "Response ERRRRRor :" + error.toString(), Toast.LENGTH_LONG).show();
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                    }

                }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        myRequestQueue.add(myJsonObjectRequest);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Log.d(TAG, "onDateSet: ");
    }
}
