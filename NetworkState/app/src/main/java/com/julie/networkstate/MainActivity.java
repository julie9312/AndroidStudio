package com.julie.networkstate;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;
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
                // 1 은 wifi
                if(statusInfo == 1){
                    img.setImageResource(R.drawable.ic_launcher_background);
                }else if(statusInfo == 0){
                    // 0은 모바일 (4G / 5G )
                    img.setImageResource(R.drawable.ic_launcher_foreground);
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public int getNetworkStatus2(Context context){
        ConnectivityManager connectivityManager =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return 0;
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return 1;
                }  else{
                    return 3;
                }
            }
        } else {
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
            }else{
                return 1000;
            }
        }
        return 1000;
    }

}