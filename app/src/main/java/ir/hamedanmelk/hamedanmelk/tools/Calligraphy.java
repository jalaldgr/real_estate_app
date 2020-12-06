package ir.hamedanmelk.hamedanmelk.tools;

import android.app.Application;

import ir.hamedanmelk.hamedanmelk.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class Calligraphy extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/yekan.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

    }

}