package com.julie.youtube;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.julie.youtube.adapter.RecyclerViewAdapter;
import com.julie.youtube.model.Benz;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

        String youtubeUrl = "https://www.googleapis.com/youtube/v3/search?part=snippet&key=AIzaSyBbnT0X-K9587Ti2okL9JzOax3uB7gP3LA&maxResults=20&order=date&type=video&type=video&regionCode=KR";

        RequestQueue requestQueue;

        RecyclerView recyclerView;
        RecyclerViewAdapter recyclerViewAdapter;
        ArrayList<Benz> benzArrayList = new ArrayList<>();

        EditText editSearch;
        ImageView imgSearch;

        String nextPageToken;
        String pageToken = "";
        String searchUrl = "";


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

            //////////addOnScroll
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    int lastPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                    int totalCount = recyclerView.getAdapter().getItemCount();

                    if(lastPosition+1 == totalCount){
                        //아이템 추가 ! 입맛에 맞게 설정하시면됩니다.
                        Log.i("AAA", "맨 마지막 도착");

                        if(nextPageToken.compareTo(pageToken) != 0){
                            pageToken = nextPageToken;

                            String url = "";
                            if(searchUrl.isEmpty()){
                                 url = youtubeUrl+"&pageToken="+pageToken;
                            }else {
                                url = youtubeUrl+"&pageToken="+pageToken;
                            }

                            // 이 url로 네트워크 데이터 요청.
                            Log.i("AAA", url);
                            addNetworkData(url);
                        }
                    }

                }
            });

            editSearch = findViewById(R.id.editSearch);
            imgSearch = findViewById(R.id.imgSearch);
            imgSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String word = editSearch.getText().toString().trim();


                    if(word.isEmpty()){
                        searchUrl = youtubeUrl;
                    }else{
                        searchUrl = youtubeUrl + "&q=" + word;
                    }
                    //새로 바뀐 검색어로 데이터를 가져오기 위해서, 원래 들어있던 어레이리슽으의 데이터를 모두 지우고 , 새로 받아온다.
                    benzArrayList.clear();
                    getNetworkData(searchUrl);
                }
            });

            // 발리 라이브러리 이용해서, 호출
            // 로그 찍어본다.
            requestQueue = Volley.newRequestQueue(MainActivity.this);

        getNetworkData(youtubeUrl);
        }

        public void  getNetworkData(String url){
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    url,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i("AAA", response.toString());
                            try {
                                nextPageToken = response.getString("nextPageToken");
                                JSONArray items = response.getJSONArray("items");
                                for (int i = 0; i < items.length(); i++) {
                                    JSONObject jsonObject = items.getJSONObject(i);
                                    JSONObject id = jsonObject.getJSONObject("id");
                                    String videoId = id.getString("videoId");
                                    JSONObject snippet = jsonObject.getJSONObject("snippet");
                                    String title = snippet.getString("title");
                                    String desc = snippet.getString("description");
                                    JSONObject thumbnails = snippet.getJSONObject("thumbnails");
                                    JSONObject def = thumbnails.getJSONObject("medium");
                                    String url = def.getString("url");

                                    Benz benz = new Benz(title, desc, url, videoId);
                                    benzArrayList.add(benz);
                                    // Log.i("AAA", videoId +","+title+", "+desc+", "+url);
                                }
                                //데이터를 1번부터 다시 가지고 오지 않기 위해 기존과 다르게 작성 ! 이전 파싱과 비교교
                                recyclerViewAdapter = new RecyclerViewAdapter(
                                        MainActivity.this,benzArrayList);
                                recyclerView.setAdapter(recyclerViewAdapter);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }
            );
            requestQueue.add(jsonObjectRequest);
        }
    public void addNetworkData(String url){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("AAA", response.toString());
                        try {
                            nextPageToken = response.getString("nextPageToken");
                            JSONArray items = response.getJSONArray("items");
                            for(int i = 0; i < items.length(); i++){
                                JSONObject jsonObject = items.getJSONObject(i);
                                JSONObject id = jsonObject.getJSONObject("id");
                                String videoId = id.getString("videoId");
                                JSONObject snippet = jsonObject.getJSONObject("snippet");
                                String title = snippet.getString("title");
                                String desc = snippet.getString("description");
                                JSONObject thumbnails = snippet.getJSONObject("thumbnails");
                                JSONObject def = thumbnails.getJSONObject("medium");
                                String url = def.getString("url");

                                Benz benz = new Benz(title, desc, url, videoId);
                                benzArrayList.add(benz);
//  Log.i("AAA", videoId +","+title+", "+desc+", "+url);
                            }
//다시 1번부터 돌아가지 않고 멈췄다가 데이터 다시 받아오게 하기
                            recyclerViewAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }
}
