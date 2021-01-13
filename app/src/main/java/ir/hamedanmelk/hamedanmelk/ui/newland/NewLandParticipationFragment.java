package ir.hamedanmelk.hamedanmelk.ui.newland;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.darsh.multipleimageselect.activities.AlbumSelectActivity;
import com.darsh.multipleimageselect.models.Image;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import ir.hamedanmelk.hamedanmelk.models.micro.LandCaseTypeModel;
import ir.hamedanmelk.hamedanmelk.models.micro.LandDirectionModel;
import ir.hamedanmelk.hamedanmelk.models.micro.LandTypeModel;
import ir.hamedanmelk.hamedanmelk.models.micro.ProvinceModel;
import ir.hamedanmelk.hamedanmelk.models.micro.UseTypeModel;
import ir.hamedanmelk.hamedanmelk.models.myResponse;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.ExpandableHeightGridView;
import ir.hamedanmelk.hamedanmelk.tools.MYSQlDBHelper;
import ir.hamedanmelk.hamedanmelk.tools.RetrofitInterface;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import saman.zamani.persiandate.PersianDate;

public class NewLandParticipationFragment extends Fragment  implements OnMapReadyCallback, DatePickerDialog.OnDateSetListener{
//    private RequestQueue myRequestQueue;
//    private JsonObjectRequest myJsonObjectRequest;
    public static final int PICK_IMAGES = 5;
    private static final String TAG = "NewLandParticipation";
    public List<String> selectedUseTypes = new ArrayList<>();
    public List<String> selectedEquipments = new ArrayList<>();
    NewLandModel requestNewModel= new NewLandModel();
    String   UID;
    EditText titleEtx;
    EditText spaceFoundationETxt;
    Spinner landCaseSpnr;
    Spinner landTypeSpnr;
    MultiSelectSpinner landUseTypeSpnr;
    Spinner provinceSpnr;
    Spinner citySpnr;
    Spinner areaSpnr;
    Spinner districtSpnr;
    Spinner directionSpnr;
    Spinner buildingConditionSpnr;
    Spinner waterSpnr;
    Spinner gasSpnr;
    Spinner electricitySpnr;
    Spinner phoneSpnr;
    EditText addressETxt;
    EditText descriptionETxt;
    MultiSelectSpinner  equipmentSpnr;
    ExpandableHeightGridView selectedImagesExpandableGrid;
    Button addPhotoBtn;
    Button addMapBtn;
    MapView landMapView;
    DatePickerDialog datePicker;
    Date grgDate;
    PersianDate persianDate;
    PersianCalendar persianCalendar = new PersianCalendar();
    Button   submitBtn;
    GoogleMap mgoogleMap;
    LatLng mapLatLng;

    boolean firstSelectionProvinceSpinner=false;
    boolean firstSelectionCitySpinner=false;
    boolean firstSelectionAreaSpinner=false;
    boolean firstSelectionDistrictSpinner=false;
    boolean firstSelectionDirectionSpinner=false;
    boolean firstSelectionBuildingConditionSpinner=false;
    boolean firstSelectionWaterSpinner=false;
    boolean firstSelectionGasSpinner=false;
    boolean firstSelectionElectricitySpinner=false;
    boolean firstSelectionPhoneSpinner=false;
    boolean firstSelectionLandTypeSpinner=false;
    boolean firstSelectionLandCaseSpinner=false;
    boolean selectedProvinceSpinner=false;
    boolean selectedCitySpinner=false;
    boolean selectedAreaSpinner=false;
    boolean selectedDistrictSpinner=false;
    boolean selectedDirectionSpinner=false;
    boolean selectedBuildingConditionSpinner=false;
    boolean selectedWaterSpinner=false;
    boolean selectedGasSpinner=false;
    boolean selectedElectricitySpinner=false;
    boolean selectedPhoneSpinner=false;
    boolean selectedLandTypeSpinner=false;


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

    List<ImageModel> imageModels = new ArrayList<>();
    List<String> imagesStr = new ArrayList<>();
    MYSQlDBHelper dbHelper;

    public NewLandParticipationFragment() {
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


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_participation, container, false);

//////////////////////////////////////Find Elements///////////////////////////////////////////////
        titleEtx = (EditText) view.findViewById(R.id.NewLandParticipationFragmentTitleTxt);
        landCaseSpnr = (Spinner) view.findViewById(R.id.NewLandParticipationFragmentLandCaseSpnr);
        landTypeSpnr = (Spinner) view.findViewById(R.id.NewLandParticipationFragmentLandTypeSpnr);
        landUseTypeSpnr = (MultiSelectSpinner) view.findViewById(R.id.NewLandParticipationFragmentLandUseTypeMltSpnr);
        spaceFoundationETxt = (EditText) view.findViewById(R.id.NewLandParticipationFragmentFoundationSpaceTxt);
        provinceSpnr = (Spinner) view.findViewById(R.id.NewLandParticipationFragmentProvinceSpnr);
        citySpnr = (Spinner) view.findViewById(R.id.NewLandParticipationFragmentCitySpnr);
        areaSpnr = (Spinner) view.findViewById(R.id.NewLandParticipationFragmentAreaSpnr);
        districtSpnr = (Spinner) view.findViewById(R.id.NewLandParticipationFragmentDistrictSpnr);
        addressETxt = (EditText) view.findViewById(R.id.NewLandParticipationFragmentAddressTxt);
        equipmentSpnr = (MultiSelectSpinner) view.findViewById(R.id.NewLandParticipationFragmentEquipmentsSpnr);
        directionSpnr = (Spinner)view.findViewById(R.id.NewLandParticipationFragmentDirectionSpnr);
        buildingConditionSpnr = (Spinner)view.findViewById(R.id.NewLandParticipationFragmentBuildingConditionSpnr);
        descriptionETxt = (EditText) view.findViewById(R.id.NewLandParticipationFragmentDescriptionTxt);
        waterSpnr = (Spinner) view.findViewById(R.id.NewLandParticipationFragmentWaterSpnr);
        gasSpnr = (Spinner) view.findViewById(R.id.NewLandParticipationFragmentGasSpnr);
        electricitySpnr = (Spinner) view.findViewById(R.id.NewLandParticipationFragmentElectricitySpnr);
        phoneSpnr = (Spinner) view.findViewById(R.id.NewLandParticipationFragmentPhoneSpnr);
        landMapView = (MapView) view.findViewById(R.id.mapView);
        submitBtn = (Button) view.findViewById(R.id.NewLandParticipationFragmentSubmitBtn);
        persianDate = new PersianDate();
        datePicker = new DatePickerDialog();
        datePicker.setMinDate(persianCalendar);
        selectedImagesExpandableGrid = (ExpandableHeightGridView)view.findViewById(R.id.NewLandParticipationFragmentGalleryExpandableGrid);
        addPhotoBtn = (Button)view.findViewById(R.id.NewLandParticipationFragmentAddPhotoBtn);
        addMapBtn= (Button)view.findViewById(R.id.NewLandParticipationFragmentAddMapBtn);

///////////////////////////Load map//////////////////////////////////////////////
        loadMap();

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
                if (firstSelectionBuildingConditionSpinner)selectedBuildingConditionSpinner=true;
                firstSelectionBuildingConditionSpinner=true;
                Log.d(TAG, "onItemSelected: "+selectedBuildingConditionSpinner);
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
                if(firstSelectionLandCaseSpinner)firstSelectionLandCaseSpinner=true;
                firstSelectionLandCaseSpinner=true;
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
                if(firstSelectionLandTypeSpinner)selectedLandTypeSpinner=true;
                firstSelectionLandTypeSpinner=true;
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
                if(firstSelectionProvinceSpinner)selectedProvinceSpinner=true;
                firstSelectionProvinceSpinner=true;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        citySpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                requestNewModel.setCityID( cityIDs.get(i));
                if(firstSelectionCitySpinner)selectedCitySpinner=true;
                firstSelectionCitySpinner=true;
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
                if(firstSelectionAreaSpinner)selectedAreaSpinner=true;
                firstSelectionAreaSpinner=true;
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
                if(firstSelectionDistrictSpinner)selectedDistrictSpinner=true;
                selectedDistrictSpinner=true;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });




        ////////////////////// landDirection Spinner////////////////////////////////
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
                if(firstSelectionDirectionSpinner)selectedDirectionSpinner=true;
                selectedDirectionSpinner=true;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        /////////////////////////////water gas eletricy phone spinner///////////////////////////////
        waterSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(firstSelectionWaterSpinner)selectedWaterSpinner=true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        gasSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(firstSelectionGasSpinner)selectedGasSpinner=true;
                firstSelectionGasSpinner=true;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        electricitySpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(firstSelectionElectricitySpinner)selectedElectricitySpinner=true;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        phoneSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(firstSelectionPhoneSpinner)selectedPhoneSpinner=true;
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
                        selectedUseTypes.clear();
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
                        selectedEquipments.clear();
                        for (int i=0 ; i<selected.length;i++){
                            if(selected[i]) selectedEquipments.add(equipmentIDs.get(i));
                        }
                        requestNewModel.setEquipment(selectedEquipments);
                    }
                });

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
            }
        });

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
                if (TextUtils.isEmpty(spaceFoundationETxt.getText().toString())) {
                    spaceFoundationETxt.setError(getResources().getString(R.string.foundation_space_input_error_msg));
                    spaceFoundationETxt.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(addressETxt.getText().toString())) {
                    addressETxt.setError(getResources().getString(R.string.address_input_error_msg));
                    addressETxt.requestFocus();
                    return;
                }
                

//                if(!selectedProvinceSpinner){
//                    provinceSpnr.setFocusableInTouchMode(true);
//                    provinceSpnr.requestFocus();
//                    TextView errorText = (TextView)provinceSpnr.getSelectedView();
//                    errorText.setTextColor(Color.RED);
//                    errorText.setText(getResources().getString(R.string.required_spinner_select));
//                }
//                if(!selectedCitySpinner){
//                    citySpnr.setFocusableInTouchMode(true);
//                    citySpnr.requestFocus();
//                    TextView errorText = (TextView)citySpnr.getSelectedView();
//                    errorText.setTextColor(Color.RED);
//                    errorText.setText(getResources().getString(R.string.required_spinner_select));
//                }
//                if(!selectedAreaSpinner){
//                    areaSpnr.setFocusableInTouchMode(true);
//                    areaSpnr.requestFocus();
//                    TextView errorText = (TextView)areaSpnr.getSelectedView();
//                    errorText.setTextColor(Color.RED);
//                    errorText.setText(getResources().getString(R.string.required_spinner_select));
//                }
//
//                if(!selectedDistrictSpinner){
//                    districtSpnr.setFocusableInTouchMode(true);
//                    directionSpnr.requestFocus();
//                    TextView errorText = (TextView)districtSpnr.getSelectedView();
//                    errorText.setTextColor(Color.RED);
//                    errorText.setText(getResources().getString(R.string.required_spinner_select));
//                }
//
//                if(!selectedDirectionSpinner){
//                    directionSpnr.setFocusableInTouchMode(true);
//                    directionSpnr.requestFocus();
//                    TextView errorText = (TextView)directionSpnr.getSelectedView();
//                    errorText.setTextColor(Color.RED);
//                    errorText.setText(getResources().getString(R.string.required_spinner_select));
//                }
//
//                if(!selectedBuildingConditionSpinner){
//                    buildingConditionSpnr.setFocusableInTouchMode(true);
//                    buildingConditionSpnr.requestFocus();
//                    TextView errorText = (TextView)buildingConditionSpnr.getSelectedView();
//                    errorText.setTextColor(Color.RED);
//                    errorText.setText(getResources().getString(R.string.required_spinner_select));
//                }
//
//
//                if(!selectedWaterSpinner){
//                    waterSpnr.setFocusableInTouchMode(true);
//                    waterSpnr.requestFocus();
//                    TextView errorText = (TextView)waterSpnr.getSelectedView();
//                    errorText.setTextColor(Color.RED);
//                    errorText.setText(getResources().getString(R.string.required_spinner_select));
//                }
//
//                if(!selectedGasSpinner){
//                    gasSpnr.setFocusableInTouchMode(true);
//                    gasSpnr.requestFocus();
//                    TextView errorText = (TextView)gasSpnr.getSelectedView();
//                    errorText.setTextColor(Color.RED);
//                    errorText.setText(getResources().getString(R.string.required_spinner_select));
//                }
//
//                if(!selectedElectricitySpinner){
//                    electricitySpnr.setFocusableInTouchMode(true);
//                    electricitySpnr.requestFocus();
//                    TextView errorText = (TextView)electricitySpnr.getSelectedView();
//                    errorText.setTextColor(Color.RED);
//                    errorText.setText(getResources().getString(R.string.required_spinner_select));
//                }
//
//                if(!selectedPhoneSpinner){
//                    phoneSpnr.setFocusableInTouchMode(true);
//                    phoneSpnr.requestFocus();
//                    TextView errorText = (TextView)phoneSpnr.getSelectedView();
//                    errorText.setTextColor(Color.RED);
//                    errorText.setText(getResources().getString(R.string.required_spinner_select));
//                }



                requestNewModel.setTitle(titleEtx.getText().toString());
                requestNewModel.setDescription(descriptionETxt.getText().toString());
                requestNewModel.setFoundationSpace(spaceFoundationETxt.getText().toString());
                requestNewModel.setAddress(addressETxt.getText().toString());
                requestNewModel.setLandStateID("5");
                requestNewModel.setUID(UID);
                requestNewModel.setWater(Long.toString(waterSpnr.getSelectedItemId()));
                requestNewModel.setGas(Long.toString(gasSpnr.getSelectedItemId()));
                requestNewModel.setElectricy(Long.toString(electricitySpnr.getSelectedItemId()));
                requestNewModel.setPhone(Long.toString(phoneSpnr.getSelectedItemId()));

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
        mgoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mapLatLng, 15.5f));
        mgoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
        mgoogleMap.getUiSettings().setZoomControlsEnabled(true);
        mgoogleMap.getUiSettings().setZoomGesturesEnabled(true);
        
    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Log.d(TAG, "onDateSet: ");
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


    //**********************************************************************************************
    //THIS METHOD POST REQUEST USING RETROFIT LIBRARY
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



    //THIS METHOD SEND REQUEST USING VOLLEY LIBRARY
//    private void HomeFragmentPOSTRequest(Context Cntx) throws JSONException {
//
//        Map<String, String> postParam= new HashMap<String, String>();
//        Gson gson = new Gson();
//        JSONObject obj=null;
//        try {
//            String json = new Gson().toJson(requestNewModel);
//            Log.d(TAG, "HomeFragmentPOSTRequest Gson>>>: "+json);
//            obj = new JSONObject(json);
//        }catch (Exception e){
//            Log.d(TAG, "HomeFragmentPOSTRequest: "+e.toString());
//        }
//
//        final ProgressDialog progressDialog=new ProgressDialog(Cntx);
//        progressDialog.setMessage(getResources().getString(R.string.loading_message));
//        progressDialog.setCanceledOnTouchOutside(false);
//        progressDialog.show();
//        myRequestQueue = Volley.newRequestQueue(Cntx);
//        myJsonObjectRequest = new JsonObjectRequest(Request.Method.POST
//                , Urls.getBaseURL()+Urls.getRegisterLand(), obj,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        progressDialog.dismiss();
//                        Log.d(TAG, "onResponse: "+response.toString());
//                        try {
//                            if(response.getString(Constants.JSON_RESPONSE_DATA).contains("Success")) {
//                                Toast.makeText(getContext(), "آگهی با موفقیت ثبت شد", Toast.LENGTH_LONG).show();
//                                final NavController controller= Navigation.findNavController(Objects.requireNonNull(getActivity()),R.id.nav_host_fragment);
//                                controller.navigate(R.id.navigation_home);
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener(){
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getContext(),
//                                "Response ERRRRRor :" + error.toString(), Toast.LENGTH_LONG).show();
//                        if (progressDialog.isShowing())
//                            progressDialog.dismiss();
//                    }
//
//                }){
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Content-Type", "application/json; charset=utf-8");
//                return headers;
//            }
//        };
//
//        myRequestQueue.add(myJsonObjectRequest);
//    }


}
