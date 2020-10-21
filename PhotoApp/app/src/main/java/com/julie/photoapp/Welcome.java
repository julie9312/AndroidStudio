package com.julie.photoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.julie.photoapp.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Welcome extends AppCompatActivity {

    Button btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. 쉐어드 프리퍼런스에 저장되어있는 토큰을 가져온다.
                SharedPreferences sharedPreferences =
                        getSharedPreferences(Utils.PREFERENCES_NAME, MODE_PRIVATE);
                final String token = sharedPreferences.getString("token", null);
                Log.i("AAA", token);

                JsonObjectRequest request = new JsonObjectRequest(
                        Request.Method.DELETE,
                        Utils.BASE_URL + "/api/v1/users/logout",
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.i("AAA", "logout : " + response.toString());
                                try {
                                    boolean success = response.getBoolean("success");
                                    if(success == true){
                                        // 토큰을 지워줘야 한다.
                                        SharedPreferences sharedPreferences =
                                                getSharedPreferences(Utils.PREFERENCES_NAME,MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("token", null);
                                        editor.apply();

                                        Intent i = new Intent(Welcome.this, Login.class);
                                        startActivity(i);
                                        finish();
                                    }else{
                                        Toast.makeText(Welcome.this, "실패", Toast.LENGTH_SHORT).show();
                                        // 토스트 띄운다.
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Welcome.this, "로그아웃실패", Toast.LENGTH_SHORT).show();
                                // 토스트 띄운다. 로그아웃 실패라고 토스트.
                            }
                        }
                ){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("Authorization", "Bearer "+token);
                        return params;
                    }
                };

                Volley.newRequestQueue(Welcome.this).add(request);

            }
        });

    }


}