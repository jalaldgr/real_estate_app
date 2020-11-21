package ir.hamedanmelk.hamedanmelk.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import ir.hamedanmelk.hamedanmelk.R;

public class Splash_Ads extends AppCompatActivity {
    Boolean clicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__ads);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        clicked=false;
        final Handler handler = new Handler();
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.SplashAdsMainLeanerLayout);
        final TextView numbertxt = (TextView)findViewById(R.id.numbercounter);
        TextView skipbutton = (TextView)findViewById(R.id.splash_skip_text);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked=true;
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://hamedanmelk.ir/"));
                startActivity(i);            }
        });
        skipbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i =new Intent(Splash_Ads.this, MainActivity.class);
                startActivity(i);
            }
        });


//        for(int i=4;i>=0;i--){
//            numbertxt.setText(""+i);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        handler.postDelayed(new Runnable(){
            @Override
            public void run(){

                Intent i =new Intent(Splash_Ads.this, MainActivity.class);
                startActivity(i);
                finish();
                if(!clicked)handler.removeCallbacksAndMessages(null); // TODO this not work
            }
        },100);
    }
}