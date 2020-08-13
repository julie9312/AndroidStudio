package com.julie.networkstate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.img);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int statusInfo = getNetworkStatus(MainActivity.this);

                if(statusInfo == 1){
                    img.setImageResource(R.drawable.wireless);
                }else if(statusInfo == 0){
                    img.setImageResource(R.drawable.ic_launcher_background);
                }
            }
        });
    }

    public int getNetworkStatus(Context context){
    ConnectivityManager connectivityManager =
            (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    android.net.NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
    if(networkInfo != null){
        switch (networkInfo.getType()){
            case ConnectivityManager.TYPE_WIFI:
                return 1;
            case ConnectivityManager.TYPE_MOBILE:
                return 0;
            default:
                return 3;

            }
        }else {
        return 1000;
    }
   }
}