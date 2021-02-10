package ir.hamedanmelk.hamedanmelk.ui.personalpage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Objects;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.models.LandModel;
import ir.hamedanmelk.hamedanmelk.recyclers.HomeRecyclerViewAdapter;
import ir.hamedanmelk.hamedanmelk.tools.MYSQlDBHelper;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserHistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserHistoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    ArrayList<LandModel> landModels;

    MYSQlDBHelper qlDBHelper;
    public UserHistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserHistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserHistoryFragment newInstance(String param1, String param2) {
        UserHistoryFragment fragment = new UserHistoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  v =inflater.inflate(R.layout.fragment_user_history, container, false);
        qlDBHelper = new MYSQlDBHelper(getContext());
        recyclerView = (RecyclerView) v.findViewById(R.id.user_history_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        landModels = qlDBHelper.GetAllHistoryLands();
        recyclerView.setAdapter(new HomeRecyclerViewAdapter(landModels, getActivity()));


        return v;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.clear_history:

                AlertDialog.Builder adb = new AlertDialog.Builder(getContext(),R.style.AlertDialogCustom);
                adb.setTitle(getResources().getString(R.string.my_hamedan_clear_history));
                adb.setIcon(getResources().getDrawable(R.drawable.ic_baseline_clear_24));
                adb.setPositiveButton(getResources().getString(R.string.alert_dialog_ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        qlDBHelper.DeleteHistoryLand();
                        landModels.clear();
                        recyclerView.setAdapter(new HomeRecyclerViewAdapter(landModels, getActivity()));
                    }
                });
                adb.setNegativeButton(getResources().getString(R.string.alert_dialog_cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                adb.show();


                return false;
            default:
                break;
        }

        return false;
    }
}