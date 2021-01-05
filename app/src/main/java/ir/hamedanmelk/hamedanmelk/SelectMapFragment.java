package ir.hamedanmelk.hamedanmelk;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedInputStream;
import java.security.spec.MGF1ParameterSpec;
import java.util.Objects;

import ir.hamedanmelk.hamedanmelk.tools.Constants;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectMapFragment extends Fragment   implements OnMapReadyCallback {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "Latitude";
    private static final String ARG_PARAM2 = "Longitude";
    GoogleMap mgoogleMap;
    LatLng mapLatLng;
    MapView landMapView;
    Button  selectBtn;
    SharedPreferences.Editor editor;


    // TODO: Rename and change types of parameters
    private Double mParam1;
    private Double mParam2;

    public SelectMapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SelectMapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectMapFragment newInstance(LatLng latLng) {
        SelectMapFragment fragment = new SelectMapFragment();
        Bundle args = new Bundle();
        args.putDouble(ARG_PARAM1, latLng.latitude);
        args.putDouble(ARG_PARAM2, latLng.longitude);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getDouble(ARG_PARAM1);
            mParam2 = getArguments().getDouble(ARG_PARAM2);
             mapLatLng=new LatLng(mParam1,mParam2);
            }
        editor = Objects.requireNonNull(getActivity()).getSharedPreferences(getString(R.string.new_land_pref), MODE_PRIVATE).edit();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_map, container, false);
        landMapView = (MapView) view.findViewById(R.id.SelectMapFragmentMapview);
        selectBtn = (Button)view.findViewById(R.id.SelectMapFragmentSelectBtn);
        landMapView.onCreate(null);
        landMapView.onResume();
        landMapView.getMapAsync(this);

        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController controller = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
//                editor.apply();
//                editor.commit();
                controller.navigateUp();
            }
        });
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(Objects.requireNonNull(getContext()));
        mgoogleMap = googleMap;
//        mgoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation,17f));
        mgoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mapLatLng, 15.5f));
        UiSettings uiSettings = mgoogleMap.getUiSettings();

        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setZoomGesturesEnabled(true);
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setCompassEnabled(true);
        uiSettings.setMapToolbarEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);

        mgoogleMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                mapLatLng = mgoogleMap.getCameraPosition().target;
                editor.putString(Constants.NEW_LAND_LATITIUDE,Double.toString(mapLatLng.latitude));
                editor.putString(Constants.NEW_LAND_LONGITUDE,Double.toString(mapLatLng.longitude));
                editor.apply();
                editor.commit();

            }
        });
    }
}