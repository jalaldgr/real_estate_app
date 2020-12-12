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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.android.gms.maps.MapView;
import com.google.android.material.checkbox.MaterialCheckBox;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.micro.CompanyTypeModel;
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
    String[] newLandUseTypeIDStrArr;
    String[] newLandEquipmentStrArr;
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
    String newLandLandTypeID;
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
    Spinner landTypeSpnr;
    Spinner landUseTypeSpnr;
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
    Spinner equipmentSpnr;
    Spinner floorCoveringSpnr;
    Spinner kitchenServicesSpnr;
    Spinner directionSpnr;
    Spinner adTypeSpnr;
    Spinner waterSpnr;
    Spinner gasSpnr;
    Spinner electricitySpnr;
    Spinner phoneSpnr;
    EditText descriptionETxt;
    MapView mapView;
    Button   submitBtn;


    ArrayList<CompanyTypeModel> parentCompanyModels ;
    List<String> parentCompanyTypeTitles= new ArrayList<String>();
    List<String> parentCompanyTypeIDs= new ArrayList<String>();
    ArrayList<CompanyTypeModel> subCompanyTypeModels;
    List<String> subCompanyTypeTitles= new ArrayList<String>();
    List<String> subCompanyTypeIDs= new ArrayList<String>();
    ArrayAdapter<String> subDataAdapter ;
    ArrayAdapter<String> parentDataAdapter;
    Boolean parentSpinnerFirstSelectionFLAG=false;
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
        newLandUIDStr = user_pref.getString(Constants.USER_MODEL_ID,"0");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_land, container, false);

//////////////////////////////////////Find Elements///////////////////////////////////////////////
        titleEtx=(EditText) view.findViewById(R.id.NewLandFragmentTitleTxt);
        buildingConditionSpnr=(Spinner) view.findViewById(R.id.NewLandFragmentBuildingConditionSpnr);
        landTypeSpnr=(Spinner)view.findViewById(R.id.NewLandFragmentLandTypeSpnr);
        landUseTypeSpnr =(Spinner)view.findViewById(R.id.NewLandFragmentLandUseTypeSpnr);
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
        equipmentSpnr=(Spinner)view.findViewById(R.id.NewLandFragmentEquipmentsSpnr);
        floorCoveringSpnr =(Spinner)view.findViewById(R.id.NewLandFragmentFloorCoveringSpnr);
        kitchenServicesSpnr =(Spinner)view.findViewById(R.id.NewLandFragmentKitchenServicesSpnr);
        directionSpnr=(Spinner)view.findViewById(R.id.NewLandFragmentDirectionSpnr);
        adTypeSpnr=(Spinner)view.findViewById(R.id.NewLandFragmentAdTypeSpnr);
        waterSpnr=(Spinner)view.findViewById(R.id.NewLandFragmentWaterSpnr);
        gasSpnr=(Spinner)view.findViewById(R.id.NewLandFragmentGasSpnr);
        electricitySpnr=(Spinner)view.findViewById(R.id.NewLandFragmentElectricitySpnr);
        phoneSpnr=(Spinner)view.findViewById(R.id.NewLandFragmentPhoneSpnr);
        descriptionETxt=(EditText) view.findViewById(R.id.NewLandFragmentDescriptionTxt);
        mapView=(MapView) view.findViewById(R.id.NewLandFragmentMapView);
        submitBtn=(Button) view.findViewById(R.id.NewLandFragmentSubmitBtn);

//////////////////////Select parent Company////////////////////////////////
//        parentCompanyModels =dbHelper.GetParentCompanyTypes();
//        for(CompanyTypeModel companyItem : parentCompanyModels){
//            parentCompanyTypeTitles.add(companyItem.getTitle());
//            parentCompanyTypeIDs.add(companyItem.getId());
//        }
//        registerForContextMenu(logoImg);
//        parentDataAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, parentCompanyTypeTitles);
//        parentDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        parentCompanySpnr.setAdapter(parentDataAdapter);
//        parentCompanySpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if (parentSpinnerFirstSelectionFLAG){
//                    subDataAdapter.clear();
//                    subCompanyTypeModels.clear();
//                    subCompanyTypeIDs.clear();
//                }
//                subCompanyTypeModels = dbHelper.GetCompanyTypesByParentID(parentCompanyTypeIDs.get(i));
//
//                for(CompanyTypeModel companyItem : subCompanyTypeModels){
//                    subCompanyTypeTitles.add(companyItem.getTitle());
//                    subCompanyTypeIDs.add(companyItem.getId());
//                }
//                subCompanyId=subCompanyTypeIDs.get(i) ;
//                subDataAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getContext()), android.R.layout.simple_spinner_item, subCompanyTypeTitles);
//                subCompanySpnr.setAdapter(subDataAdapter);
//                parentSpinnerFirstSelectionFLAG=true;//first selection passed
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//            }
//        });
//        subCompanySpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                childCompanyTypeIDStr=subCompanyTypeIDs.get(i);
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//            }
//        });
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
                    multipart.addFormField(Constants.NEW_LAND_IMAGE_FILE , newLandTitleStr);
                    multipart.addFormField(Constants.NEW_LAND_USE_TYPE_ID , "12");
                    multipart.addFormField(Constants.NEW_LAND_EQUIPMENT , "15");
                    multipart.addFormField(Constants.NEW_LAND_LAND_STATE_ID , newLandLandStateIDStr);
                    multipart.addFormField(Constants.NEW_LAND_CHK_EXCHANGED , newLandChkExchangedStr);
                    multipart.addFormField(Constants.NEW_LAND_DONG , newLandDongStr);
                    multipart.addFormField(Constants.NEW_LAND_UID , newLandUIDStr);
                    multipart.addFormField(Constants.NEW_LAND_VOUCHER_TYPE_ID , newLandVoucherTypeIDStr);
                    multipart.addFormField(Constants.NEW_LAND_SALE_TOTAL_PRICE , newLandSaleTotalPriceStr);
                    multipart.addFormField(Constants.NEW_LAND_PRE_DONG , newLandPreDongStr);
                    multipart.addFormField(Constants.NEW_LAND_PRE_VOUCHER_TYPE_ID , newLandPreVoucherTypeIDStr);
                    multipart.addFormField(Constants.NEW_LAND_DELIVERY_DATE , newLandDeliveryDateStr);
                    multipart.addFormField(Constants.NEW_LAND_PRE_SALE_TOTAL_PRICE , newLandPreSaleTotalPriceStr);
                    multipart.addFormField(Constants.NEW_LAND_EX_DONG , newLandExDongStr);
                    multipart.addFormField(Constants.NEW_LAND_EX_VOUCHER_TYPE_ID , newLandExVoucherTypeIDStr);
                    multipart.addFormField(Constants.NEW_LAND_DESCRIPTION , newLandDescriptionStr);
                    multipart.addFormField(Constants.NEW_LAND_ADDRESS , newLandAddressStr);
                    multipart.addFormField(Constants.NEW_LAND_PROVINCE_ID , newLandProvinceIDStr);
                    multipart.addFormField(Constants.NEW_LAND_CITY_ID , newLandCityIDStr);
                    multipart.addFormField(Constants.NEW_LAND_AREA_ID , newLandAreaIDStr);
                    multipart.addFormField(Constants.NEW_LAND_DISTRICT_ID , newLandDistrictIDStr);
                    multipart.addFormField(Constants.NEW_LAND_LAND_TYPE_ID , newLandLandTypeID);
                    multipart.addFormField(Constants.NEW_LAND_BUILDING_CONDITION_ID , newLandBuildingConditionIDStr);
                    multipart.addFormField(Constants.NEW_LAND_BUILDING_YEAR , newLandBuildingYearStr);
                    multipart.addFormField(Constants.NEW_LAND_DEBT_TOTAL_PRICE , newLandDebtTotalPriceStr);
                    multipart.addFormField(Constants.NEW_LAND_MORTGAGE_TOTAL_PRICE , newLandMortgageTotalPriceStr);
                    multipart.addFormField(Constants.NEW_LAND_RENTAL_TOTAL_PRICE , newLandRentalPreferenceIDStr);
                    multipart.addFormField(Constants.NEW_LAND_PRE_PAY , newLandPrePayPriceStr);
                    multipart.addFormField(Constants.NEW_LAND_RENTAL_PREFERENCE , newLandRentalPreferenceIDStr);
                    multipart.addFormField(Constants.NEW_LAND_RESIDENT_OWNER , newLandResidentOwnerStr);
                    multipart.addFormField(Constants.NEW_LAND_FOUNDATION_SPACE , newLandFoundationSpaceStr);
                    multipart.addFormField(Constants.NEW_LAND_DIRECTION_ID , newLandDirectionIDStr);
                    multipart.addFormField(Constants.NEW_LAND_LAND_VIEW_ID , newLandLandViewIDStr);
                    multipart.addFormField(Constants.NEW_LAND_FLOOR_COVERING_ID , newLandFloorCoveringIDStr);
                    multipart.addFormField(Constants.NEW_LAND_KITCHEN_SERVICES , newLandKitchenServiceIDStr);
                    multipart.addFormField(Constants.NEW_LAND_ROOM_COUNT , newLandRoomCountStr);
                    multipart.addFormField(Constants.NEW_LAND_FLOOR_COUNT , newLandFloorCountStr);
                    multipart.addFormField(Constants.NEW_LAND_UNIT_IN_FLOOR , newLandUnitInFloorStr);
                    multipart.addFormField(Constants.NEW_LAND_FLOOR , newLandFloorStr);
                    multipart.addFormField(Constants.NEW_LAND_LAND_CASE_ID , newLandLandCaseIDStr);
                    multipart.addFormField(Constants.NEW_LAND_LOAN_TYPE_ID , newLandLoanTypeIDStr);
                    multipart.addFormField(Constants.NEW_LAND_WATER , newLandWaterStr);
                    multipart.addFormField(Constants.NEW_LAND_GAS , newLandGasStr);
                    multipart.addFormField(Constants.NEW_LAND_ELECTRICITY , newLandElectricityStr);
                    multipart.addFormField(Constants.NEW_LAND_PHONE , newLandPhoneStr);

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
