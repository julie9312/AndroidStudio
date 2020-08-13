package com.julie.todos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.julie.todos.adapter.RecyclerViewAdapter;
import com.julie.todos.model.Todos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    public static final String URL = "https://jsonplaceholder.typicode.com/todos";
    ArrayList<Todos> todosArrayList = new ArrayList<>();

    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(Request.Method.GET,
                        URL,
                        null,
                        new Response.Listener<JSONArray>() {

                            @Override
                            public void onResponse(JSONArray response) {
                                Log.i("AAA", response.toString());
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        JSONObject jsonObject = response.getJSONObject(i);
                                        //userId,id,title,completed을 가져온다
                                        int userId = jsonObject.getInt("userId");
                                        int id = jsonObject.getInt("id");
                                        String title = jsonObject.getString("title");
                                        boolean completed = jsonObject.getBoolean("completed");
                                        // 위의 4개 다 가져왔으면 클래스 하나 만들어서 객체안에 저장.
                                        Todos todos = new Todos(userId, id, title, completed);
                                        todosArrayList.add(todos);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                                recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, todosArrayList);
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

