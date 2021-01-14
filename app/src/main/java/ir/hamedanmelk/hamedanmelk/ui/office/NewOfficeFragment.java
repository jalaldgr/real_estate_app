package ir.hamedanmelk.hamedanmelk.ui.office;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.OfficeModel;
import ir.hamedanmelk.hamedanmelk.models.micro.AreaModel;
import ir.hamedanmelk.hamedanmelk.models.micro.CityModel;
import ir.hamedanmelk.hamedanmelk.models.micro.DistrictModel;
import ir.hamedanmelk.hamedanmelk.models.micro.ProvinceModel;
import ir.hamedanmelk.hamedanmelk.models.myResponse;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.FilePath;
import ir.hamedanmelk.hamedanmelk.tools.MYSQlDBHelper;
import ir.hamedanmelk.hamedanmelk.tools.RetrofitInterface;
import ir.hamedanmelk.hamedanmelk.tools.Urls;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class NewOfficeFragment extends Fragment {

    private static final String TAG ="NewCompanyFragment";
    MYSQlDBHelper dbHelper;
    OfficeModel officeModel;

    String   UIDStr    ;
    String   titleStr;
    String   addressStr;
    String   managerStr;
    String   phoneStr;
    String   faxStr;
    String   officeNOStr;
    String   provinceStr = "255";
    String   cityStr = "255";
    String   areaStr = "255";
    String   districtStr = "255";
    String   logoStr;
    String   childCompanyTypeIDStr;
    String   subCompanyId;
    String   selectedFileStr="";
    Uri      selectedFileUri;
    EditText titleETxt ;
    EditText managerETxt;
    EditText addressETxt;
    EditText phoneETxt ;
    EditText faxETxt;
    EditText officeNoETxt;
    Spinner provinceSpnr;
    Spinner citySpnr;
    Spinner areaSpnr;
    Spinner districtSpnr;
    Button   submitBtn ;
    Button  addPhotoBtn;
    ImageView clearImg;
    ImageView logoImg;
    LinearLayout mainlinearLayout;

    ArrayList<ProvinceModel> provinceModels ;
    List<String> provinceTitles= new ArrayList<String>();
    List<String> provinceIDs= new ArrayList<String>();
    ArrayAdapter<String> provinceAdapter ;
    LinearLayout imageLyt;

    ArrayList<CityModel> cityModels;
    List<String> cityTitles= new ArrayList<String>();
    List<String> cityIDs= new ArrayList<String>();
    ArrayAdapter<String> cityDataAdapter;

    ArrayList<AreaModel> areaModels;
    List<String> areaTitles= new ArrayList<String>();
    List<String> areaIDs= new ArrayList<String>();
    ArrayAdapter<String> areaDataAdapter;

    ArrayList<DistrictModel> districtModels;
    List<String> districtTitles= new ArrayList<String>();
    List<String> districtIDs= new ArrayList<String>();
    ArrayAdapter<String> districtDataAdapter;

    public NewOfficeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        dbHelper = new MYSQlDBHelper(getContext());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_office, container, false);

        provinceSpnr = view.findViewById(R.id.NewOfficeFragmentProvinceIDSpnr);
        citySpnr =view.findViewById(R.id.NewOfficeFragmentCitySpnr);
        areaSpnr = view.findViewById(R.id.NewOfficeFragmentAreaSpnr);
        districtSpnr = view.findViewById(R.id.NewOfficeFragmentDistrictSpnr);
        titleETxt = (EditText)view.findViewById(R.id.NewOfficeFragmentTitleEtxt);
        managerETxt = (EditText)view.findViewById(R.id.NewOfficeFragmenManagerEtxt);
        addressETxt = (EditText)view.findViewById(R.id.NewOfficeFragmenAddresstEtxt);
        phoneETxt = (EditText)view.findViewById(R.id.NewOfficeFragmentPhoneEtxt);
        faxETxt = (EditText)view.findViewById(R.id.NewOfficeFragmentFaxEtxt);
        officeNoETxt = (EditText)view.findViewById(R.id.NewOfficeFragmentNoEtxt);
        submitBtn =(Button)view.findViewById(R.id.NewOfficeFragmentSubmitBtn);
        logoImg = (ImageView)view.findViewById(R.id.NewOfficeFragmentLogoImg);
        addPhotoBtn = (Button)view.findViewById(R.id.NewofficeFragmentAddPhotoBtn);
        clearImg = (ImageView)view.findViewById(R.id.NewofficeFragmentClearImg);
        imageLyt = (LinearLayout)view.findViewById(R.id.NewofficeFragmentImageLyt);
        mainlinearLayout = (LinearLayout)view.findViewById(R.id.NewOfficemainLaouyt);

        mainlinearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                return false;
            }
        });
//////////////////////Select parent Company////////////////////////////////
        provinceModels =dbHelper.GetProvincesList();
        for(ProvinceModel item : provinceModels){
            provinceTitles.add(item.getTitle());
            provinceIDs.add(item.getId());
        }
        registerForContextMenu(logoImg);
        provinceAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, provinceTitles);
        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        provinceSpnr.setAdapter(provinceAdapter);
        provinceSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cityModels = dbHelper.GetCitiesByProvinceID(provinceIDs.get(i));
                cityIDs.clear();cityTitles.clear();
                for(CityModel item : cityModels){
                    cityTitles.add(item.getTitle());
                    cityIDs.add(item.getId());
                }
                cityDataAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getContext()), android.R.layout.simple_spinner_item, cityTitles);
                citySpnr.setAdapter(cityDataAdapter);
                provinceStr = provinceIDs.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        citySpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cityStr = cityIDs.get(i);
                areaModels = dbHelper.GetAreasByCityID(cityIDs.get(i));
                areaIDs.clear();areaTitles.clear();
                for(AreaModel item : areaModels){
                    areaTitles.add(item.getTitle());
                    areaIDs.add(item.getId());
                }
                areaDataAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getContext()), android.R.layout.simple_spinner_item, areaTitles);
                areaSpnr.setAdapter(areaDataAdapter);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        areaSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                areaStr = areaIDs.get(i);
                districtTitles.clear();districtIDs.clear();
                districtModels = dbHelper.GetDistrictsByAreaID(areaIDs.get(i));
                for(DistrictModel item : districtModels){
                    districtTitles.add(item.getTitle());
                    districtIDs.add(item.getId());
                }
                districtDataAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getContext()), android.R.layout.simple_spinner_item, districtTitles);
                districtSpnr.setAdapter(districtDataAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        districtSpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                districtStr = districtIDs.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//////////////////////////PickUp Image///////////////////////////////////
        logoImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoImg.showContextMenu();
            }
        });

        logoImg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(titleETxt.getText().toString())) {
                    titleETxt.setError(getResources().getString(R.string.title_input_error_msg));
                    titleETxt.requestFocus();
                    return;
                }
                if(selectedFileStr.isEmpty()){
                    Toast.makeText(getContext(),getResources().getString(R.string.photo_input_error_msg),Toast.LENGTH_SHORT).show();
                    return;
                }
                MakeModel();
                //AddCompanyRequest(getContext());
                try {
                    AddNEWCompanyRequest();
                } catch (JSONException e) {
                    Log.d("ThisTAG JSONException", "I Am Not Bayram JSONException : " + e.toString());
                }
            }
        });
        addPhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGallery();
            }
        });
        clearImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageLyt.setVisibility(View.GONE);
                addPhotoBtn.setVisibility(View.VISIBLE);
                selectedFileStr="";
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
                selectedFileStr = FilePath.getPath(getContext(), selectedFileUri);
                Bitmap bitmapImage = null;
                try {
                    bitmapImage = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(getActivity()).getContentResolver(), selectedFileUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                logoImg.setImageBitmap(bitmapImage);
                addPhotoBtn.setVisibility(View.GONE);
                imageLyt.setVisibility(View.VISIBLE);
            }

        }
    }



    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Write your logic here
//                selectedFilePath=null;
//                selectedimage=null;
//                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




    //**********************************************************************************************
    //UPLOAD NEW COMPANY BY RETROFIT
    private void AddNEWCompanyRequest() throws JSONException {

        final String ThisTAG = "AddNEWOffice";
        String json = new Gson().toJson(officeModel);
        JSONObject JSONrequestBody = new JSONObject(json);
        File file = new File(officeModel.getLogo());
        RequestBody logoRequestBody = RequestBody.create(MediaType.parse("image/*"),file);
        MultipartBody.Part logoMultipartBodyPart =
                MultipartBody.Part.createFormData("Logo", file.getName(), logoRequestBody);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d(ThisTAG, "log log: " + message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        final Retrofit myRetrofit = new Retrofit.Builder().baseUrl("https://hamedanmelk.ir")
                .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();
        RetrofitInterface RI = myRetrofit.create(RetrofitInterface.class);
        Call<myResponse> uploadResponse = RI.AddOfficeRequest(officeModel.getMultipartBody(), logoMultipartBodyPart);
        uploadResponse.enqueue(new Callback<myResponse>() {
            @Override
            public void onResponse(Call<myResponse> call, retrofit2.Response<myResponse> response) {
                if(response.body().getData().contains("success")){
                    Toast.makeText(getContext(),getResources().getString(R.string.success_office_msg),Toast.LENGTH_LONG).show();
                    NavController controller = Navigation.findNavController(Objects.requireNonNull(getActivity()),R.id.nav_host_fragment);
                    controller.navigate(R.id.navigation_home);
                }

            }
            @Override
            public void onFailure(Call<myResponse> call, Throwable t) {
                Log.d(ThisTAG, "I Am Not : " + t.toString());
            }
        });
    }

    //**********************************************************************************************
    //FILL NEW OFFICE DATA MODEL CLASS FIELDS FOR SEND
    private void MakeModel(){

        SharedPreferences user_pref = Objects.requireNonNull(getActivity()).getSharedPreferences(getString(R.string.user_shared_preference), Context.MODE_PRIVATE);
        UIDStr = user_pref.getString(Constants.USER_MODEL_ID, "0");
        titleStr = titleETxt.getText().toString();
        managerStr = managerETxt.getText().toString();
        phoneStr = phoneETxt.getText().toString();
        addressStr = addressETxt.getText().toString();
        officeNOStr = officeNoETxt.getText().toString();

        officeModel = new OfficeModel( "",
                titleStr,
                managerStr,
                officeNOStr,
                addressStr,
                selectedFileStr,
                phoneStr,
                faxStr,
                "",
                 provinceStr,
                cityStr,
                areaStr,
                districtStr,
                UIDStr,
                "");
    }


}
