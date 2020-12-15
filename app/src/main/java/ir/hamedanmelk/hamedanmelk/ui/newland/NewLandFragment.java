package ir.hamedanmelk.hamedanmelk.ui.newland;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.android.gms.maps.MapView;
import com.google.android.material.checkbox.MaterialCheckBox;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import io.apptik.widget.multiselectspinner.MultiSelectSpinner;
import ir.hamedanmelk.hamedanmelk.R;
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
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.FilePath;
import ir.hamedanmelk.hamedanmelk.tools.MYSQlDBHelper;
import ir.hamedanmelk.hamedanmelk.tools.MultipartUtility;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

public class NewLandFragment extends Fragment {
    String newLandLatitudeStr;
    String newLandLongitudeStr;
    String newLandTitleStr;
    ImageView[] newLandImageFiles;
    List<String> newLandUseTypeIDLstArr;
    String newLandLandStateIDStr;
    String newLandChkExchangedStr;
    String newLandUIDStr;
    String newLandDongStr;
    String newLandVoucherTypeIDStr;
    String newLandSaleTotalPriceStr;
    String newLandPreDongStr;
    String newLandPreVoucherTypeIDStr;
    String newLandDeliveryDateStr;
    String newLandPreSaleTotalPriceStr;
    String newLandExDongStr;
    String newLandExVoucherTypeIDStr;
    String newLandDescriptionStr;
    String newLandAddressStr;
    String newLandProvinceIDStr;
    String newLandCityIDStr;
    String newLandAreaIDStr;
    String newLandDistrictIDStr;
    String newLandLandTypeIDStr;
    String newLandBuildingConditionIDStr;
    String newLandBuildingYearStr;
    String newLandDebtTotalPriceStr;
    String newLandMortgageTotalPriceStr;
    String newLandRentTotalPriceStr;
    String newLandPrePayPriceStr;
    String newLandRentalPreferenceIDStr;
    String newLandResidentOwnerStr;
    String newLandFoundationSpaceStr;
    String newLandDirectionIDStr;
    String newLandLandViewIDStr;
    String newLandFloorCoveringIDStr;
    String newLandKitchenServiceIDStr;
    String newLandRoomCountStr;
    String newLandFloorCountStr;
    String newLandUnitInFloorStr;
    String newLandFloorStr;
    String newLandLandCaseIDStr;
    String newLandLoanTypeIDStr;
    String newLandWaterStr;
    String newLandGasStr;
    String newLandElectricityStr;
    String newLandPhoneStr;


    String   selectedFileStr;
    Uri      selectedFileUri;
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
    MultiSelectSpinner equipmentSpnr;
    Spinner floorCoveringSpnr;
    Spinner kitchenServicesSpnr;
    Spinner directionSpnr;
    Spinner landStateSpnr;///////// Land State=> ads type
    Spinner waterSpnr;
    Spinner gasSpnr;
    Spinner electricitySpnr;
    Spinner phoneSpnr;
    EditText descriptionETxt;
    MapView mapView;
    Button   submitBtn;


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


    private static final String TAG ="NewLandFragment";
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

        newLandUIDStr = user_pref.getString("id","0");
        Log.d(TAG, "onCreate User ID: "+newLandUIDStr);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_land, container, false);

//////////////////////////////////////Find Elements///////////////////////////////////////////////
        titleEtx=(EditText) view.findViewById(R.id.NewLandFragmentTitleTxt);
        buildingConditionSpnr=(Spinner) view.findViewById(R.id.NewLandFragmentBuildingConditionSpnr);
        landCaseSpnr = (Spinner)view.findViewById(R.id.NewLandFragmentLandCaseSpnr);
        landTypeSpnr=(Spinner)view.findViewById(R.id.NewLandFragmentLandTypeSpnr);
        landUseTypeSpnr =(MultiSelectSpinner) view.findViewById(R.id.NewLandFragmentLandUseTypeMltSpnr);
        voucherTypeSpnr=(Spinner)view.findViewById(R.id.NewLandFragmentVoucherTypeSpnr);
        preVoucherTypeSpnr=(Spinner)view.findViewById(R.id.NewLandFragmentPreVoucherTypeSpnr);
        exVoucherTypeSpnr=(Spinner)view.findViewById(R.id.NewLandFragmentExVoucherTypeSpnr);
        dongETxt=(EditText)view.findViewById(R.id.NewLandFragmentDongTxt);
        preDongETxt=(EditText)view.findViewById(R.id.NewLandFragmentPreDongTxt);
        exDongETxt=(EditText)view.findViewById(R.id.NewLandFragmentExDongTxt);
        buildingYearETxt=(EditText)view.findViewById(R.id.NewLandFragmentBuildingYearTxt);
        spaceFoundationETxt=(EditText)view.findViewById(R.id.NewLandFragmentFoundationSpaceTxt);
        deliveryETxt=(EditText)view.findViewById(R.id.NewLandFragmentDeliveryDateTxt);
        residentOwnerChkBx=(MaterialCheckBox) view.findViewById(R.id.NewLandFragmentResidentOwnerChkBx);
        rentalPreferenceSpnr=(Spinner)view.findViewById(R.id.NewLandFragmentRentalPreferenceSpnr);
        exchangeChkBx=(MaterialCheckBox) view.findViewById(R.id.NewLandFragmentExchangeChkBx);
        totalPriceETxt=(EditText)view.findViewById(R.id.NewLandFragmentTotalPriceTxt);
        preSalePriceETxt=(EditText)view.findViewById(R.id.NewLandFragmentPreSalePriceTxt);
        prePayPriceETxt=(EditText)view.findViewById(R.id.NewLandFragmentPrePayPriceTxt);
        debtTotalPriceETxt=(EditText)view.findViewById(R.id.NewLandFragmentDebtTotalPriceTxt);
        loanTypeSpnr=(Spinner)view.findViewById(R.id.NewLandFragmentLoanTypeSpnr);
        mortgageTotalPriceETxt=(EditText)view.findViewById(R.id.NewLandFragmentMortgGageTotalPriceTxt);
        totalRentPriceETxt =(EditText)view.findViewById(R.id.NewLandFragmentRentTotalPriceTxt);
        whichFloorSpnr=(Spinner)view.findViewById(R.id.NewLandFragmentWichFloorSpnr);
        roomCountSpnr=(Spinner)view.findViewById(R.id.NewLandFragmentRoomCountSpnr);
        floorCountSpnr=(Spinner)view.findViewById(R.id.NewLandFragmentFloorCountSpnr);
        unitInFloorSpnr=(Spinner)view.findViewById(R.id.NewLandFragmentUnitInFloorSpnr);
        provinceSpnr=(Spinner)view.findViewById(R.id.NewLandFragmentProvinceSpnr);
        citySpnr=(Spinner)view.findViewById(R.id.NewLandFragmentCitySpnr);
        areaSpnr=(Spinner)view.findViewById(R.id.NewLandFragmentAreaSpnr);
        districtSpnr=(Spinner)view.findViewById(R.id.NewLandFragmentDistrictSpnr);
        addressETxt=(EditText) view.findViewById(R.id.NewLandFragmentAddressTxt);
        landViewSpnr=(Spinner)view.findViewById(R.id.NewLandFragmentLandViewSpnr);
        equipmentSpnr=(MultiSelectSpinner) view.findViewById(R.id.NewLandFragmentEquipmentsSpnr);
        floorCoveringSpnr =(Spinner)view.findViewById(R.id.NewLandFragmentFloorCoveringSpnr);
        kitchenServicesSpnr =(Spinner)view.findViewById(R.id.NewLandFragmentKitchenServicesSpnr);
        directionSpnr=(Spinner)view.findViewById(R.id.NewLandFragmentDirectionSpnr);
        landStateSpnr =(Spinner)view.findViewById(R.id.NewLandFragmentAdTypeSpnr);
        waterSpnr=(Spinner)view.findViewById(R.id.NewLandFragmentWaterSpnr);
        gasSpnr=(Spinner)view.findViewById(R.id.NewLandFragmentGasSpnr);
        electricitySpnr=(Spinner)view.findViewById(R.id.NewLandFragmentElectricitySpnr);
        phoneSpnr=(Spinner)view.findViewById(R.id.NewLandFragmentPhoneSpnr);
        descriptionETxt=(EditText) view.findViewById(R.id.NewLandFragmentDescriptionTxt);
        mapView=(MapView) view.findViewById(R.id.NewLandFragmentMapView);
        submitBtn=(Button) view.findViewById(R.id.NewLandFragmentSubmitBtn);

////////////////////// Building Conditions Spinner////////////////////////////////
        buildingConditionModels =dbHelper.GetBuildingConditionsList();
        for(BuildingConditionModel Item : buildingConditionModels){
            buildingConditionTitles.add(Item.getTitle());
            buildingConditionIDs.add(Item.getId());
        }
        buildingConditionAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, buildingConditionTitles);
        buildingConditionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        buildingConditionSpnr.setAdapter(buildingConditionAdapter);
        buildingConditionSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                newLandBuildingConditionIDStr = buildingConditionIDs.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });



//////////////////////////////// Building Conditions Spinner////////////////////////////////
        buildingConditionModels =dbHelper.GetBuildingConditionsList();
        for(BuildingConditionModel Item : buildingConditionModels){
            buildingConditionTitles.add(Item.getTitle());
            buildingConditionIDs.add(Item.getId());
        }
        buildingConditionAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, buildingConditionTitles);
        buildingConditionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        buildingConditionSpnr.setAdapter(buildingConditionAdapter);
        buildingConditionSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                newLandBuildingConditionIDStr = buildingConditionIDs.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

////////////////////// land Case Spinner////////////////////////////////
        landCaseTypeModels =dbHelper.GetLandCaseTypesList();
        for(LandCaseTypeModel Item : landCaseTypeModels){
            landCaseTitles.add(Item.getTitle());
            landCaseIDs.add(Item.getId());
        }
        landCaseAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, landCaseTitles);
        landCaseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        landCaseSpnr.setAdapter(landCaseAdapter);
        landCaseSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                newLandLandCaseIDStr = landCaseIDs.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        ////////////////////// land Type Spinner////////////////////////////////
        landTypeModels =dbHelper.GetLandTypeList();
        for(LandTypeModel Item : landTypeModels){
            landTypeTitles.add(Item.getTitle());
            landTypeIDs.add(Item.getId());
        }
        landTypeAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, landTypeTitles);
        landTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        landTypeSpnr.setAdapter(landTypeAdapter);
        landTypeSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                newLandLandTypeIDStr = landTypeIDs.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        ////////////////////// Rental Preference Spinner////////////////////////////////
        rentalPreferenceModels =dbHelper.GetRentalPreferenceList();
        for(RentalPreferenceModel Item : rentalPreferenceModels){
            rentalPreferenceTitles.add(Item.getTitle());
            rentalPreferenceIDs.add(Item.getId());
        }
        rentalPreferenceAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, rentalPreferenceTitles);
        rentalPreferenceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rentalPreferenceSpnr.setAdapter(rentalPreferenceAdapter);
        rentalPreferenceSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                newLandRentalPreferenceIDStr = rentalPreferenceIDs.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });



        ////////////////////// Loan Type Spinner////////////////////////////////
        loanTypeModels =dbHelper.GetLoanTypesList();
        for(LoanTypeModel Item : loanTypeModels){
            loanTypeTitles.add(Item.getTitle());
            loanTypeIDs.add(Item.getId());
        }
        loanTypeAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, loanTypeTitles);
        loanTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        loanTypeSpnr.setAdapter(loanTypeAdapter);
        loanTypeSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                newLandLoanTypeIDStr = loanTypeIDs.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        ////////////////////// Province Spinner////////////////////////////////
        provinceModels =dbHelper.GetProvincesList();
        for(ProvinceModel Item : provinceModels){
            provinceTitles.add(Item.getTitle());
            provinceIDs.add(Item.getId());
        }
        provinceAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, provinceTitles);
        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        provinceSpnr.setAdapter(provinceAdapter);
        provinceSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                newLandProvinceIDStr = provinceIDs.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });



        ////////////////////// City Spinner////////////////////////////////
        cityModels =dbHelper.GetCitiesList();
        for(CityModel Item : cityModels){
            cityTitles.add(Item.getTitle());
            cityIDs.add(Item.getId());
        }
        cityAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, cityTitles);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpnr.setAdapter(cityAdapter);
        citySpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                newLandCityIDStr = cityIDs.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        ////////////////////// Area Spinner////////////////////////////////
        areaModels =dbHelper.GetAreaList();
        for(AreaModel Item : areaModels){
            areaTitles.add(Item.getTitle());
            areaIDs.add(Item.getId());
        }
        areaAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, areaTitles);
        areaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        areaSpnr.setAdapter(areaAdapter);
        areaSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                newLandAreaIDStr = areaIDs.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        ////////////////////// District Spinner////////////////////////////////
        districtModels =dbHelper.GetDistrictList();
        for(DistrictModel Item : districtModels){
            districtTitles.add(Item.getTitle());
            districtIDs.add(Item.getId());
        }
        districtAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, districtTitles);
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        districtSpnr.setAdapter(districtAdapter);
        districtSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                newLandDistrictIDStr = districtIDs.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });



        ////////////////////// District Spinner////////////////////////////////
        floorCoveringModels =dbHelper.GetFloorCoveringList();
        for(FloorCoveringModel Item : floorCoveringModels){
            floorCoveringTitles.add(Item.getTitle());
            floorCoveringIDs.add(Item.getId());
        }
        floorCoveringAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, floorCoveringTitles);
        floorCoveringAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        floorCoveringSpnr.setAdapter(floorCoveringAdapter);
        floorCoveringSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                newLandFloorCoveringIDStr = floorCoveringIDs.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });



        ////////////////////// Province Spinner////////////////////////////////
        kitchenServiceModels =dbHelper.GetKitchenServicesList();
        for(KitchenServiceModel Item : kitchenServiceModels){
            kitchenServiceTitles.add(Item.getTitle());
            kitchenServiceIDs.add(Item.getId());
        }
        kitchenServiceAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, kitchenServiceTitles);
        kitchenServiceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kitchenServicesSpnr.setAdapter(kitchenServiceAdapter);
        kitchenServicesSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                newLandKitchenServiceIDStr = kitchenServiceIDs.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        ////////////////////// Province Spinner////////////////////////////////
        landDirectionModels =dbHelper.GetLandDirectionsList();
        for(LandDirectionModel Item : landDirectionModels){
            landDirectionTitles.add(Item.getTitle());
            landDirectionTypeIDs.add(Item.getId());
        }
        landDirectionAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, landDirectionTitles);
        landDirectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        directionSpnr.setAdapter(landDirectionAdapter);
        directionSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                newLandDirectionIDStr = landDirectionTypeIDs.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        ////////////////////// Province Spinner////////////////////////////////
        landStateTypeModels =dbHelper.GetLandStateList();
        for(LandStateTypeModel Item : landStateTypeModels){
            landStateTitles.add(Item.getTitle());
            landStateIDs.add(Item.getId());
        }
        landStateAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, landStateTitles);
        landStateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        landStateSpnr.setAdapter(landStateAdapter);
        landStateSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                newLandLandStateIDStr = landStateIDs.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });



        ////////////////////// UseType MultiSelect Spinner////////////////////////////////
        useTypeModels =dbHelper.GetUseTypeList();
        for(UseTypeModel Item : useTypeModels){
            useTypeTitles.add(Item.getTitle());
            useTypeIDs.add(Item.getId());
        }
        useTypeAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_list_item_multiple_choice, useTypeTitles);
        landUseTypeSpnr
                .setListAdapter(useTypeAdapter)
                .setMinSelectedItems(1);
        landUseTypeSpnr.getListAdapter().getCount();
        landUseTypeSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemSelected: "+adapterView.getSelectedItemId());
//                newLandUseTypeIDLstArr.add(useTypeIDs.get(i));
//                Log.d(TAG, "onItemSelected: "+newLandUseTypeIDLstArr.size()+ "   ID");
                // TODO: 12/15/2020 Get Selected Items
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ////////////////////// UseType Equipments Spinner////////////////////////////////
        equipmentModels =dbHelper.GetLandEquipmentsList();
        Log.d(TAG, "onCreateView size: "+equipmentModels.size());
        for(EquipmentModel Item : equipmentModels){
            equipmentTitles.add(Item.getTitle());
            equipmentIDs.add(Item.getId());
            Log.d(TAG, "onCreateView: "+Item.getTitle());
        }
        equipmentAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()),
                android.R.layout.simple_list_item_multiple_choice, equipmentTitles);
        equipmentSpnr
                .setListAdapter(equipmentAdapter)
                .setMinSelectedItems(1);
        equipmentAdapter.getCount();
        equipmentSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemSelected: "+adapterView.getSelectedItemId());
//                newLandUseTypeIDLstArr.add(useTypeIDs.get(i));
//                Log.d(TAG, "onItemSelected: "+newLandUseTypeIDLstArr.size()+ "   ID");
                // TODO: 12/15/2020 Get Selected Items
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


////////////////////////////PickUp Image///////////////////////////////////
//        logoImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                logoImg.showContextMenu();
//            }
//        });
//
//        logoImg.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                return false;
//            }
//        });
//
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewLandRegisterRequest(getContext());
            }
        });
        return view;
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
//                logoImg.setImageBitmap(bitmapImage);
            }
        }

    }

//////////////////////////////////Context Menu///////////////////////////////////

    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = Objects.requireNonNull(getActivity()).getMenuInflater();
        inflater.inflate(R.menu.image_long_press_menu, menu);
    }
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
//                logoImg.setImageBitmap(null);

            }
            default:
                return false;
        }
        return false;
    }

    public void NewLandRegisterRequest ( Context context) {

        class NewLandRegisterRequestAsync extends AsyncTask<Void, Void, List<String>> {
            private final ProgressDialog dialog = new ProgressDialog(getContext());
//            final File imageFile = new File(selectedFileStr);
            String requestUrl = Urls.getBaseURL() + Urls.getRegisterLand();
            @Override
            protected void onPreExecute() {
                super.onPreExecute();



                this.dialog.setMessage(getResources().getString(R.string.loading_message));
                this.dialog.setIndeterminate(true);
                this.dialog.setCanceledOnTouchOutside(false);
                this.dialog.show();
            }

            @Override
            protected void onPostExecute(List<String> s) {

                super.onPostExecute(s);
                if (this.dialog.isShowing()) this.dialog.dismiss();
                final NavController controller = Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.nav_host_fragment);

                try {
                    JSONObject requestJsonObject = new JSONObject(s.get(0));
                    if (requestJsonObject.getInt("State") > 0) {
                    }
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
            }

            @Override
            protected List<String> doInBackground(Void... voids) {
                try {
                    MultipartUtility multipart = new MultipartUtility(requestUrl, "UTF-8");


                    multipart.addFormField(Constants.NEW_LAND_LATITIUDE ,  newLandLatitudeStr);
                    multipart.addFormField(Constants.NEW_LAND_LONGITUDE ,  newLandLongitudeStr);
                    multipart.addFormField(Constants.NEW_LAND_TITLE , newLandTitleStr);
//                    multipart.addFormField(Constants.NEW_LAND_IMAGE_FILE , newLandTitleStr);
//                    multipart.addFormField(Constants.NEW_LAND_USE_TYPE_ID , "12");
//                    multipart.addFormField(Constants.NEW_LAND_EQUIPMENT , "15");
//                    multipart.addFormField(Constants.NEW_LAND_LAND_STATE_ID , newLandLandStateIDStr);
//                    multipart.addFormField(Constants.NEW_LAND_CHK_EXCHANGED , newLandChkExchangedStr);
//                    multipart.addFormField(Constants.NEW_LAND_DONG , newLandDongStr);
//                    multipart.addFormField(Constants.NEW_LAND_UID , newLandUIDStr);
//                    multipart.addFormField(Constants.NEW_LAND_VOUCHER_TYPE_ID , newLandVoucherTypeIDStr);
//                    multipart.addFormField(Constants.NEW_LAND_SALE_TOTAL_PRICE , newLandSaleTotalPriceStr);
//                    multipart.addFormField(Constants.NEW_LAND_PRE_DONG , newLandPreDongStr);
//                    multipart.addFormField(Constants.NEW_LAND_PRE_VOUCHER_TYPE_ID , newLandPreVoucherTypeIDStr);
//                    multipart.addFormField(Constants.NEW_LAND_DELIVERY_DATE , newLandDeliveryDateStr);
//                    multipart.addFormField(Constants.NEW_LAND_PRE_SALE_TOTAL_PRICE , newLandPreSaleTotalPriceStr);
//                    multipart.addFormField(Constants.NEW_LAND_EX_DONG , newLandExDongStr);
//                    multipart.addFormField(Constants.NEW_LAND_EX_VOUCHER_TYPE_ID , newLandExVoucherTypeIDStr);
//                    multipart.addFormField(Constants.NEW_LAND_DESCRIPTION , newLandDescriptionStr);
//                    multipart.addFormField(Constants.NEW_LAND_ADDRESS , newLandAddressStr);
//                    multipart.addFormField(Constants.NEW_LAND_PROVINCE_ID , newLandProvinceIDStr);
//                    multipart.addFormField(Constants.NEW_LAND_CITY_ID , newLandCityIDStr);
//                    multipart.addFormField(Constants.NEW_LAND_AREA_ID , newLandAreaIDStr);
//                    multipart.addFormField(Constants.NEW_LAND_DISTRICT_ID , newLandDistrictIDStr);
//                    multipart.addFormField(Constants.NEW_LAND_LAND_TYPE_ID , newLandLandTypeID);
//                    multipart.addFormField(Constants.NEW_LAND_BUILDING_CONDITION_ID , newLandBuildingConditionIDStr);
//                    multipart.addFormField(Constants.NEW_LAND_BUILDING_YEAR , newLandBuildingYearStr);
//                    multipart.addFormField(Constants.NEW_LAND_DEBT_TOTAL_PRICE , newLandDebtTotalPriceStr);
//                    multipart.addFormField(Constants.NEW_LAND_MORTGAGE_TOTAL_PRICE , newLandMortgageTotalPriceStr);
//                    multipart.addFormField(Constants.NEW_LAND_RENTAL_TOTAL_PRICE , newLandRentalPreferenceIDStr);
//                    multipart.addFormField(Constants.NEW_LAND_PRE_PAY , newLandPrePayPriceStr);
//                    multipart.addFormField(Constants.NEW_LAND_RENTAL_PREFERENCE , newLandRentalPreferenceIDStr);
//                    multipart.addFormField(Constants.NEW_LAND_RESIDENT_OWNER , newLandResidentOwnerStr);
//                    multipart.addFormField(Constants.NEW_LAND_FOUNDATION_SPACE , newLandFoundationSpaceStr);
//                    multipart.addFormField(Constants.NEW_LAND_DIRECTION_ID , newLandDirectionIDStr);
//                    multipart.addFormField(Constants.NEW_LAND_LAND_VIEW_ID , newLandLandViewIDStr);
//                    multipart.addFormField(Constants.NEW_LAND_FLOOR_COVERING_ID , newLandFloorCoveringIDStr);
//                    multipart.addFormField(Constants.NEW_LAND_KITCHEN_SERVICES , newLandKitchenServiceIDStr);
//                    multipart.addFormField(Constants.NEW_LAND_ROOM_COUNT , newLandRoomCountStr);
//                    multipart.addFormField(Constants.NEW_LAND_FLOOR_COUNT , newLandFloorCountStr);
//                    multipart.addFormField(Constants.NEW_LAND_UNIT_IN_FLOOR , newLandUnitInFloorStr);
//                    multipart.addFormField(Constants.NEW_LAND_FLOOR , newLandFloorStr);
//                    multipart.addFormField(Constants.NEW_LAND_LAND_CASE_ID , newLandLandCaseIDStr);
//                    multipart.addFormField(Constants.NEW_LAND_LOAN_TYPE_ID , newLandLoanTypeIDStr);
//                    multipart.addFormField(Constants.NEW_LAND_WATER , newLandWaterStr);
//                    multipart.addFormField(Constants.NEW_LAND_GAS , newLandGasStr);
//                    multipart.addFormField(Constants.NEW_LAND_ELECTRICITY , newLandElectricityStr);
//                    multipart.addFormField(Constants.NEW_LAND_PHONE , newLandPhoneStr);

                    List<String> responseList = multipart.finish();
                    Log.d(TAG, "doInBackground: response size"+responseList.size());
                    for (String item : responseList) {
                        Log.d(TAG, "Upload Files Response:::" + item);
                    }
                    return responseList;

                } catch (IOException e) {
                    e.printStackTrace();
                    return Arrays.asList(e.toString());
                }
            }

        }
        NewLandRegisterRequestAsync newLandRegisterRequestAsync = new NewLandRegisterRequestAsync();
        newLandRegisterRequestAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR , null);
    }
}
