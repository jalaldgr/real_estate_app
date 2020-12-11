package ir.hamedanmelk.hamedanmelk.ui.personalpage;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import ir.hamedanmelk.hamedanmelk.R;
import ir.hamedanmelk.hamedanmelk.tools.Constants;
import ir.hamedanmelk.hamedanmelk.tools.Urls;

public class ChatFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    WebView mwebview;

    // TODO: Rename and change types of parameters
     String chatUID;
     String chatTo;

    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            chatUID = getArguments().getString(Constants.START_CHAT_UID);
            chatTo = getArguments().getString(Constants.START_CHAT_TO);
        }

    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_chat, container, false);
        mwebview = (WebView) v.findViewById(R.id.webview_chat);
        String postData = null;
        try {
            postData = Constants.START_CHAT_UID+ "=" +
                    URLEncoder.encode(chatUID, "UTF-8") + "&"+
                    Constants.START_CHAT_TO+"=" + URLEncoder.encode(chatTo, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        assert postData != null;
        mwebview.postUrl(Urls.getBaseURL()+Urls.getStartUserChat(),postData.getBytes());
        return v;
    }
}