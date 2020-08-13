package com.test.naverapi2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    String baseUrl = "https://openapi.naver.com/v1/papago/n2mt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("AAA", "create");
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        //문자열로 JSON을 받아오는 클래스 : StringRequest

        //헤더에 데이터를 넣어서 요청하는 방법
        StringRequest request = new StringRequest(
                Request.Method.POST,
                baseUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("AAA", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            // translatedText 항목울 뽑아 올 수 있습니다.

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("AAA", error.toString());

                    }
                }
        ){
            // 네이버 API의 헤더 셋팅 부분을 여기에 작성한다.
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                params.put("X-Naver-Client-Id", "Fhgq2ldFVbwkpHSEWOf6");
                params.put("X-Naver-Client-Secret", "l1iEjZprm3");
                return params;
            }
            // 네이버에 요청할 파라미터를 셋팅한다.
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("source", "ko");
                params.put("target", "zh-CN");
                params.put("text", "안녕");
                return params;

            }
        };

        requestQueue.add(request);

    }
}