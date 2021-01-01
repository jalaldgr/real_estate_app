package ir.hamedanmelk.hamedanmelk.ui.company;

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
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.CompanyModel;
import ir.hamedanmelk.hamedanmelk.models.micro.CompanyTypeModel;
import ir.hamedanmelk.hamedanmelk.models.myResponse;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.FilePath;
import ir.hamedanmelk.hamedanmelk.tools.MYSQlDBHelper;
import ir.hamedanmelk.hamedanmelk.tools.MultipartUtility;
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

public class NewCompanyFragment extends Fragment {

    private static final String TAG ="NewCompanyFragment";
    MYSQlDBHelper dbHelper;
    CompanyModel NewCompanyDataModel;

    String   UIDStr    ;
    String   childCompanyTypeIDStr;
    String   titleStr;
    String   addressStr;
    String   managerStr;
    String   phoneStr;
    String   logoStr;
    String   subCompanyId;
    String   selectedFileStr;
    Uri      selectedFileUri;
    Spinner  parentCompanySpnr;
    Spinner  subCompanySpnr ;
    EditText titleETxt ;
    EditText managerETxt;
    EditText addressETxt;
    EditText phoneETxt ;
    Button   submitBtn ;
    ImageView logoImg;
    HashMap<String, String> requestBodyTextFields = new HashMap<>();
    HashMap<String, String> requestBodyImageFields = new HashMap<>();
    ArrayList<CompanyTypeModel> parentCompanyModels ;
    List<String> parentCompanyTypeTitles= new ArrayList<String>();
    List<String> parentCompanyTypeIDs= new ArrayList<String>();
    ArrayList<CompanyTypeModel> subCompanyTypeModels;
    List<String> subCompanyTypeTitles= new ArrayList<String>();
    List<String> subCompanyTypeIDs= new ArrayList<String>();
    ArrayAdapter<String> subDataAdapter ;
    ArrayAdapter<String> parentDataAdapter;
    Boolean parentSpinnerFirstSelectionFLAG=false;
    public NewCompanyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        dbHelper = new MYSQlDBHelper(getContext());
        SharedPreferences user_pref = Objects.requireNonNull(getActivity()).getSharedPreferences(getString(R.string.user_shared_preference), Context.MODE_PRIVATE);
        UIDStr = user_pref.getString(Constants.USER_MODEL_ID,"0");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_company, container, false);

        parentCompanySpnr = view.findViewById(R.id.NewCompanyFragmentParentCompanyTypesSpnr);
        subCompanySpnr =view.findViewById(R.id.NewCompanyFragmentCompanyTypesSpnr);
        titleETxt = (EditText)view.findViewById(R.id.NewCompanyFragmentTitleEtxt);
        managerETxt = (EditText)view.findViewById(R.id.NewCompanyFragmenManagerEtxt);
        addressETxt = (EditText)view.findViewById(R.id.NewCompanyFragmenAddresstEtxt);
        phoneETxt = (EditText)view.findViewById(R.id.NewCompanyFragmentPhoneEtxt);
        submitBtn =(Button)view.findViewById(R.id.NewCompanyFragmentSubmitBtn);
        logoImg = (ImageView)view.findViewById(R.id.NewCompanyFragmentLogoImg);

//////////////////////Select parent Company////////////////////////////////
        parentCompanyModels =dbHelper.GetParentCompanyTypes();
        for(CompanyTypeModel companyItem : parentCompanyModels){
            parentCompanyTypeTitles.add(companyItem.getTitle());
            parentCompanyTypeIDs.add(companyItem.getId());
        }
        registerForContextMenu(logoImg);
        parentDataAdapter = new ArrayAdapter<String>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, parentCompanyTypeTitles);
        parentDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        parentCompanySpnr.setAdapter(parentDataAdapter);
        parentCompanySpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (parentSpinnerFirstSelectionFLAG){
                    subDataAdapter.clear();
                    subCompanyTypeModels.clear();
                    subCompanyTypeIDs.clear();
                }
                subCompanyTypeModels = dbHelper.GetCompanyTypesByParentID(parentCompanyTypeIDs.get(i));

                for(CompanyTypeModel companyItem : subCompanyTypeModels){
                    subCompanyTypeTitles.add(companyItem.getTitle());
                    subCompanyTypeIDs.add(companyItem.getId());
                }
                subCompanyId=subCompanyTypeIDs.get(i) ;
                subDataAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getContext()), android.R.layout.simple_spinner_item, subCompanyTypeTitles);
                subCompanySpnr.setAdapter(subDataAdapter);
                parentSpinnerFirstSelectionFLAG=true;//first selection passed
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        subCompanySpnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                childCompanyTypeIDStr=subCompanyTypeIDs.get(i);
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
                MakeModel();
                //AddCompanyRequest(getContext());
                try {
                    AddNEWCompanyRequest();
                } catch (JSONException e) {
                    Log.d("ThisTAG JSONException", "I Am Not Bayram JSONException : " + e.toString());
                }
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
                logoImg.setImageBitmap(bitmapImage);
            }
        }
        //Uri returnUri;
        //returnUri = data.getData();
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
                logoImg.setImageBitmap(null);
//                ImageView imageView =  (ImageView)findViewById(R.id.news_image_view);
//                imageView.setImageResource(0);
//                Button button = (Button)findViewById(R.id.news_image_button);
//                button.setVisibility(View.VISIBLE);
//                selectedFilePath=null;
//                selectedimage=null;
//                preNewsUpdateDataModelClass.setNews_MainPic_File(null);
            }
            default:
                return false;
        }
        return false;
    }

//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                //Write your logic here
////                selectedFilePath=null;
////                selectedimage=null;
////                this.finish();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }



    public void AddCompanyRequest (final Context context) {

        class AddCompanyRequestAsync extends AsyncTask<Void, Void, List<String>> {
            private final ProgressDialog dialog = new ProgressDialog(getContext());
            final File imageFile = new File(selectedFileStr);
            String requestUrl = Urls.getBaseURL() + Urls.getCompanyAdd();
            @Override
            protected void onPreExecute() {
                super.onPreExecute();;
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

                        if(requestJsonObject.getString(Constants.JSON_RESPONSE_DATA).contains("Success")) {
                            Toast.makeText(getContext(), "آگهی با موفقیت ثبت شد", Toast.LENGTH_LONG).show();
                            controller.navigate(R.id.navigation_home);
                        }
                    }
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
            }

            @Override
            protected List<String> doInBackground(Void... voids) {
                try {
                    MultipartUtility multipart = new MultipartUtility(requestUrl, "UTF-8");
                    multipart.addFormField(Constants.ADD_COMPANY_UID, UIDStr);
                    multipart.addFormField(Constants.ADD_COMPANY_TITLE, titleStr);
                    multipart.addFormField(Constants.ADD_COMPANY_MANAGER, managerStr);
                    multipart.addFormField(Constants.ADD_COMPANY_ADDRESS, addressStr);
                    multipart.addFormField(Constants.COMPANY_ADD_PHONE, phoneStr);
                    multipart.addFormField(Constants.ADD_COMPANY_SUB_COMPANY_TYPE_ID, childCompanyTypeIDStr);
                    multipart.addFilePart(Constants.ADD_COMPANY_LOGO, imageFile.getAbsoluteFile());
                    List<String> responseList = multipart.finish();
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
        AddCompanyRequestAsync addCompanyRequestAsync = new AddCompanyRequestAsync();
        addCompanyRequestAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR , null);
    }

    //**********************************************************************************************
    //UP;OAD NEW COMPANY BY RETROFIT
    private void AddNEWCompanyRequest() throws JSONException {

        final String ThisTAG = "AddNEWCompanyRequestTAG";
        String json = new Gson().toJson(NewCompanyDataModel);
        JSONObject JSONrequestBody = new JSONObject(json);
        File file = new File(NewCompanyDataModel.getLogo());
        RequestBody logoRequestBody = RequestBody.create(MediaType.parse("image/*"),file);
        MultipartBody.Part logoMultipartBodyPart =
                MultipartBody.Part.createFormData("Logo", file.getName(), logoRequestBody);
        Log.d(ThisTAG, "json string : " + json);

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
        Call<myResponse> uploadResponse = RI.AddCompanyRequest(NewCompanyDataModel.getMultipartBody(), logoMultipartBodyPart);
        uploadResponse.enqueue(new Callback<myResponse>() {
            @Override
            public void onResponse(Call<myResponse> call, retrofit2.Response<myResponse> response) {
                Log.d(ThisTAG, "I Am Bayram: " + response.toString());
                Log.d(ThisTAG, "onResponse data " + response.body().getData());
            }
            @Override
            public void onFailure(Call<myResponse> call, Throwable t) {
                Log.d(ThisTAG, "I Am Not Bayram: " + t.toString());
            }
        });
    }

    //**********************************************************************************************
    //FILL NEW COMPANY DATA MODEL CLASS FIELDS FOR SEND
    private void MakeModel(){

        SharedPreferences user_pref = Objects.requireNonNull(getActivity()).getSharedPreferences(getString(R.string.user_shared_preference), Context.MODE_PRIVATE);
        UIDStr = user_pref.getString(Constants.USER_MODEL_ID, "0");
        titleStr = titleETxt.getText().toString();
        managerStr = managerETxt.getText().toString();
        phoneStr = phoneETxt.getText().toString();
        addressStr = addressETxt.getText().toString();

        String DateTime = java.text.DateFormat.getDateTimeInstance().format(new Date());
        NewCompanyDataModel = new CompanyModel( "",
                titleStr,
                managerStr,
                phoneStr,
                addressStr,
                childCompanyTypeIDStr,
                UIDStr,
                "1",
                selectedFileStr,
                DateTime);
    }
//**********************************************************************************************


}
