package com.julie.secondphoto;


import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.julie.secondphoto.adapter.RecyclerViewAdapter;
import com.julie.secondphoto.model.Photo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // volley 에서 사용하는 클래스.
    RequestQueue requestQueue;
    ArrayList<Photo> photoArrayList = new ArrayList<>();
    public static final String URL = "https://jsonplaceholder.typicode.com/photos";

    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        requestQueue = Volley.newRequestQueue(MainActivity.this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i("AAA", response.toString());
                        for(int i = 0; i < response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String title = jsonObject.getString("title");
                                String url = jsonObject.getString("url");
                                String thumbnailUrl = jsonObject.getString("thumbnailUrl");

                                Photo photo = new Photo(title, url, thumbnailUrl);
                                photoArrayList.add(photo);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        recyclerViewAdapter =
                                new RecyclerViewAdapter(MainActivity.this, photoArrayList);
                        recyclerView.setAdapter(recyclerViewAdapter);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(jsonArrayRequest);

    }
}
