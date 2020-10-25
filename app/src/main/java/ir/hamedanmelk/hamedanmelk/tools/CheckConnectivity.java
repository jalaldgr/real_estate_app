package ir.hamedanmelk.hamedanmelk.tools;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckConnectivity {
    Context cntx;
    public boolean isNetworkAvailable(Activity context) {
        cntx=context;
        ConnectivityManager connectivityManager = (ConnectivityManager) cntx.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
