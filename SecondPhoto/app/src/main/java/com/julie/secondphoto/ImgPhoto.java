package com.julie.secondphoto;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;

public class ImgPhoto extends AppCompatActivity {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_photo);

        img = findViewById(R.id.img);

        String url = getIntent().getStringExtra("url");

        GlideUrl glideUrl = new GlideUrl(url,
                new LazyHeaders.Builder().addHeader("User-Agent", "Android").build());
        Glide.with(ImgPhoto.this).load(glideUrl).into(img);

    }
}