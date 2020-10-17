package ir.hamedanmelk.hamedanmelk.ui.aboutus;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import ir.hamedanmelk.hamedanmelk.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FAQFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FAQFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    WebView mwebview;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FAQFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FAQFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FAQFragment newInstance(String param1, String param2) {
        FAQFragment fragment = new FAQFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_f_a_q, container, false);
        mwebview = (WebView) v.findViewById(R.id.webview_f_a_q);
        WebSettings webSettings=mwebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        //TODO Change FontFace https://stackoverflow.com/questions/3900658/how-to-change-font-face-of-webview-in-android
        mwebview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mwebview.loadUrl("javascript:(function() { " +
                        "document.getElementsByClassName('other_pages')[0].style.display='none'; " +
                        "document.getElementsByClassName('search_form')[0].style.display='none'; " +
                        "document.getElementById('footer_part1').style.display='none'; " +
                        "})()");
                progressDialog.dismiss();
            }
        });
        mwebview.loadUrl("https://hamedanmelk.ir/AboutUs");


        return v;
    }
}