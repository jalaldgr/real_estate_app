package ir.hamedanmelk.hamedanmelk.ui.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.recyclers.HomeRecyclerViewAdapter;
import ir.hamedanmelk.hamedanmelk.recyclers.HomeVerticalRecyclerViewAdapter;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.HTTPRequestHandlre;
import ir.hamedanmelk.hamedanmelk.tools.MYSQlDBHelper;
import ir.hamedanmelk.hamedanmelk.tools.Urls;
import ir.hamedanmelk.hamedanmelk.tools.ViewPagerAdapter;
import ir.hamedanmelk.hamedanmelk.models.LandModel;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    RecyclerView HorizantalrecyclerView;
    RecyclerView VerticalrecyclerView;
    ArrayList<LandModel> landModels;
    ArrayList<LandModel> featuredLandModels;
    MYSQlDBHelper dbHelper;
    Button salebtn;
    Button rentbtn;
    Button assignmentbtn;
    Button resalebtn;
    Button agentbtn;
    Button servicesbtn;
    Button lawinstiutebtn;
    Button inquirybtn;
    TextView featuredTxt;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final NavController controller= Navigation.findNavController(Objects.requireNonNull(getActivity()),R.id.nav_host_fragment);
        dbHelper = new MYSQlDBHelper(getContext());
//        TotalLandRequest(getContext());
        salebtn = (Button)root.findViewById(R.id.HomeFragmentSaleButton);
        rentbtn=(Button)root.findViewById(R.id.HomeFragmentRentButton);
        assignmentbtn=(Button)root.findViewById(R.id.HomeFragmentAssignmentButton);
        resalebtn=(Button)root.findViewById(R.id.HomeFragmentResaleButton);
//        exchangebtn=(Button)root.findViewById(R.id.HomeFragmentExchangeButton);
        agentbtn=(Button)root.findViewById(R.id.HomeFragmentAgentButton);
        servicesbtn=(Button)root.findViewById(R.id.HomeFragmentServicesButton);
        lawinstiutebtn=(Button)root.findViewById(R.id.HomeFragmentLawInstiuteButton);
        inquirybtn=(Button)root.findViewById(R.id.HomeFragmentInquiryButton);
        featuredTxt = (TextView)root.findViewById(R.id.HomeFragmentFeaturedTxt);
//        morebtn=(Button)root.findViewById(R.id.HomeFragmentMoreButton);

        salebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.saleFragment);
            }
        });
        rentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.rentFragment);
            }
        });
        assignmentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.assingmentFragment);
            }
        });
        resalebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.presaleFragment);
            }
        });
//        exchangebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                controller.navigate(R.id.exchangeFragment);
//            }
//        });
        agentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.agentFragment);
            }
        });
        servicesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.servicesFragment);
            }
        });
        lawinstiutebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.tablayoutLawyersOfficesFragment);
            }
        });
        inquirybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.linksFragment);
            }
        });
//        morebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                controller.navigate(R.id.moreFragment);
//            }
//        });
        featuredTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.featuredLandFragment);

            }
        });

        HorizantalrecyclerView = (RecyclerView) root.findViewById(R.id.HomeFrgmntHrzntlRcyclVw);
        VerticalrecyclerView  = (RecyclerView) root.findViewById(R.id.HomeFrgmntVerticalRcyclVw);
        RecyclerView.LayoutManager laymngr =  new LinearLayoutManager(this.getContext());
        HorizantalrecyclerView.setLayoutManager(laymngr);
        RecyclerView.LayoutManager VRLaymngr = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL,false);
        HorizantalrecyclerView.setLayoutManager(laymngr);
        VerticalrecyclerView.setLayoutManager(VRLaymngr);

        landModels = dbHelper.GetAllLands();
        featuredLandModels = dbHelper.GetAllFeatured20Lands();
        Log.d(TAG, "onCreateView: "+landModels.size());
        HorizantalrecyclerView.setAdapter(new HomeRecyclerViewAdapter(landModels,getActivity()));
        VerticalrecyclerView.setAdapter(new HomeVerticalRecyclerViewAdapter(featuredLandModels,getActivity()));

//
//        ViewPager viewPager;
////        viewPager = (ViewPager) root.findViewById(R.id.viewpager);
//        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getContext());
//        viewPager.setAdapter(viewPagerAdapter);

        return root;
    }

    public void TotalLandRequest(final Context context){
        class TotalLandRequestAsync extends AsyncTask<Void ,Void, String> {
            private final ProgressDialog progressDialog=new ProgressDialog(context);
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog.setMessage(getResources().getString(R.string.loading_message));
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (progressDialog.isShowing())progressDialog.dismiss();
                try {
                    JSONObject reader = new JSONObject(s);
                    JSONObject ResponseData = new JSONObject(reader.getString(Constants.JSON_RESPONSE_DATA));
                    JSONArray LandList = new JSONArray(ResponseData.getString("data"));
                    JSONObject LandItem;
                    JSONArray imagesArray;
                    if (reader.getInt(Constants.JSON_RESPONSE_STATE)==1)
                    {
                        ArrayList<LandModel> landtemp=new ArrayList<LandModel>();
                        ArrayList<LandModel> featuredlandtemp=new ArrayList<LandModel>();

                        for(int i=0; i < LandList.length();i++)
                        {
                            LandItem = LandList.getJSONObject(i);
                            imagesArray =new JSONArray( LandItem.getString(Constants.SALE_MODEL_IMAGES));
                            LandModel landModel = new LandModel(
                                    LandItem.getString(Constants.LAND_MODEL_ID),
                                    LandItem.getString(Constants.LAND_MODEL_TOTAL_PRICE),
                                    LandItem.getString(Constants.LAND_MODEL_TOTAL_MORTGAGE_PRICE),
                                    LandItem.getString(Constants.LAND_MODEL_TOTAL_RENT_PRICE),
                                    LandItem.getString(Constants.LAND_MODEL_TITLE),
                                    LandItem.getString(Constants.LAND_MODEL_LAND_STATE_ID),
                                    LandItem.getString(Constants.LAND_MODEL_CREATED_AT),
                                    LandItem.getString(Constants.LAND_MODEL_LAND_SITUATION_ID),
                                    LandItem.getString(Constants.LAND_MODEL_VIEW),
                                    imagesArray.get(0).toString(),
                                    LandItem.getString(Constants.LAND_MODEL_LANDSTATETITLE),
                                    LandItem.getString(Constants.USER_LAND_MODEL_DISTRICT_ID),
                                    LandItem.getString(Constants.LAND_MODEL_LANDSITUATIONTITLE),
                                    LandItem.getString(Constants.LAND_MODEL_LANDSITUATIONCOLOR),
                                    LandItem.getString(Constants.LAND_MODEL_FIRST_NAME),
                                    LandItem.getString(Constants.LAND_MODEL_LAST_NAME),
                                    LandItem.getString(Constants.LAND_MODEL_LAND_CASE_ID)


                            );

                            landtemp.add(landModel);

                            if(Integer.parseInt(landModel.getLand_case_id())>1 && i<20   ){
                                featuredlandtemp.add(landModel);
                            };
                        }
                        landModels=landtemp;
                        featuredLandModels = featuredlandtemp;
//                        Log.d(TAG, "onPostExecute rentModels: "+landModels.toString());
                        HorizantalrecyclerView.setAdapter(new HomeRecyclerViewAdapter(landModels,getActivity()));
                        VerticalrecyclerView.setAdapter(new HomeVerticalRecyclerViewAdapter(featuredLandModels,getActivity()));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(TAG, "onPostExecute exception: "+e.toString());
                }

            }

            @Override
            protected String doInBackground(Void... voids) {
                HTTPRequestHandlre httpRequestHandlre = new HTTPRequestHandlre();
                HashMap<String,String> params = new HashMap<>();
                params.put(Constants.CONTENT_TYPE,Constants.APPLICATION_JSON);
                return httpRequestHandlre.sendGetRequest(Urls.getBaseURL()+Urls.getTotalLands(),params);
            }
        }
        TotalLandRequestAsync totalRentRequestAsync = new TotalLandRequestAsync();
        totalRentRequestAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR , null);
    }

}