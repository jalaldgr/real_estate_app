package ir.hamedanmelk.hamedanmelk.ui.lawyer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.CompanyModel;
import ir.hamedanmelk.hamedanmelk.models.LawyerModel;
import ir.hamedanmelk.hamedanmelk.models.myResponse;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.FilePath;
import ir.hamedanmelk.hamedanmelk.tools.MYSQlDBHelper;
import ir.hamedanmelk.hamedanmelk.tools.RetrofitInterface;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class NewLawyerFragment extends Fragment {

    private static final String TAG ="NewLawyerFragment";
    MYSQlDBHelper dbHelper;
    LawyerModel lawyerModel;

    String   UIDStr    ;
    String   fullNameStr;
    String   descriptionStr;
    String   phoneStr;
    String   selectedFileStr="";
    Uri      selectedFileUri;
    EditText fullNameETxt;
    EditText managerETxt;
    EditText descriptionETxt;
    EditText phoneETxt ;
    Button   submitBtn ;
    Button   addPhotoBtn;
    ImageView clearImg;
    ImageView logoImg;
    LinearLayout imageLyt;
    LinearLayout mainlinearLayout;

    public NewLawyerFragment() {
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
        View view = inflater.inflate(R.layout.fragment_new_lawyer, container, false);

        fullNameETxt = (EditText)view.findViewById(R.id.NewLawyerFragmentFullNameEtxt);
        descriptionETxt = (EditText)view.findViewById(R.id.NewLawyerFragmentDescriptionEtxt);
        phoneETxt = (EditText)view.findViewById(R.id.NewLawyerFragmentPhoneEtxt);
        submitBtn =(Button)view.findViewById(R.id.NewLawyerFragmentSubmitBtn);
        logoImg = (ImageView)view.findViewById(R.id.NewLawyerFragmentLogoImg);
        clearImg = (ImageView)view.findViewById(R.id.NewLawyerFragmentClearImg);
        addPhotoBtn = (Button)view.findViewById(R.id.NewLawyerFragmentAddPhotoBtn);
        imageLyt = (LinearLayout)view.findViewById(R.id.NewLawyerFragmentImageLyt);
        mainlinearLayout = (LinearLayout)view.findViewById(R.id.NewLawyermainLaouyt);



        mainlinearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                return false;
            }
        });
//////////////////////Select parent Company////////////////////////////////
 /////////////////////////PickUp Image///////////////////////////////////


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(fullNameETxt.getText().toString())) {
                    fullNameETxt.setError(getResources().getString(R.string.firstname_input_error_msg));
                    fullNameETxt.requestFocus();
                    return;
                }
//                if (TextUtils.isEmpty(descriptionETxt.getText().toString())) {
//                    descriptionETxt.setError(getResources().getString(R.string.address_input_error_msg));
//                    descriptionETxt.requestFocus();
//                    return;
//                }


                if (TextUtils.isEmpty(phoneETxt.getText().toString())) {
                    phoneETxt.setError(getResources().getString(R.string.phone_input_error_msg));
                    phoneETxt.requestFocus();
                    return;
                }
                if(selectedFileStr.isEmpty()){
                    Toast.makeText(getContext(),getResources().getString(R.string.photo_input_error_msg),Toast.LENGTH_SHORT).show();
                    return;
                }
                MakeModel();
                //AddCompanyRequest(getContext());
                try {
                    AddNEWLawyerRequest();
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
                selectedFileStr = FilePath.getPath(getContext(),selectedFileUri);
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
        //Uri returnUri;
        //returnUri = data.getData();
    }

//////////////////////////////////Context Menu///////////////////////////////////

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
    //UP;OAD NEW COMPANY BY RETROFIT
    private void AddNEWLawyerRequest() throws JSONException {

        final String ThisTAG = "AddNewLawyerRequestTAG";
        String json = new Gson().toJson(lawyerModel);
        JSONObject JSONrequestBody = new JSONObject(json);
        File file = new File(lawyerModel.getImage());
        RequestBody logoRequestBody = RequestBody.create(MediaType.parse("image/*"),file);
        MultipartBody.Part logoMultipartBodyPart =
                MultipartBody.Part.createFormData("Image", file.getName(), logoRequestBody);
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
        Call<myResponse> uploadResponse = RI.AddLawyerRequest(lawyerModel.getMultipartBody(), logoMultipartBodyPart);
        uploadResponse.enqueue(new Callback<myResponse>() {
            @Override
            public void onResponse(Call<myResponse> call, retrofit2.Response<myResponse> response) {
                if(response.body().getData().contains("success")){
                    Toast.makeText(getContext(),getResources().getString(R.string.success_lawyer_msg),Toast.LENGTH_LONG).show();
                    NavController controller = Navigation.findNavController(Objects.requireNonNull(getActivity()),R.id.nav_host_fragment);
                    controller.navigate(R.id.navigation_home);
                }
                else {
                    Toast.makeText(getContext(),getResources().getString(R.string.fail_msg),Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<myResponse> call, Throwable t) {
                Toast.makeText(getContext(),getResources().getString(R.string.fail_msg),Toast.LENGTH_LONG).show();
            }
        });
    }

    //**********************************************************************************************
    //FILL NEW COMPANY DATA MODEL CLASS FIELDS FOR SEND
    private void MakeModel(){

        SharedPreferences user_pref = Objects.requireNonNull(getActivity()).getSharedPreferences(getString(R.string.user_shared_preference), Context.MODE_PRIVATE);
        UIDStr = user_pref.getString(Constants.USER_MODEL_ID, "0");
        fullNameStr = fullNameETxt.getText().toString();
        descriptionStr = descriptionETxt.getText().toString();
        phoneStr = phoneETxt.getText().toString();

        String DateTime = java.text.DateFormat.getDateTimeInstance().format(new Date());
        lawyerModel = new LawyerModel( "",
                fullNameStr,
                selectedFileStr,
                descriptionStr,
                "",
                phoneStr,
                UIDStr,
                "");
    }
//**********************************************************************************************


}
