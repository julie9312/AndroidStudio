package com.julie.youtube.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.MediaStore.Video;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.julie.youtube.R;
import com.julie.youtube.model.Benz;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    Context context;
    ArrayList<Benz> benzArrayList;

    public RecyclerViewAdapter(Context context, ArrayList<Benz> benzArrayList) {
        this.context = context;
        this.benzArrayList = benzArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.benz_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Benz benz = benzArrayList.get(position);
        String title = benz.getTitle();
        String desc = benz.getDesc();
        String imgUrl = benz.getImgUrl();

        holder.txtTitle.setText(title);
        holder.txtDesc.setText(desc);

        // 1. 핵심부분 : 이미지의 주소를 글라이드에 셋팅해준다.

        Glide.with(context).load(imgUrl).into( holder.img );

    }

    @Override
    public int getItemCount() {
        return benzArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtTitle;
        public TextView txtDesc;
        public ImageView img;
        public CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDesc = itemView.findViewById(R.id.txtDesc);
            img = itemView.findViewById(R.id.img);
            cardView = itemView.findViewById(R.id.cardView);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = getAdapterPosition();
                    Benz benz = benzArrayList.get(index);
                    String url = "https://www.youtube.com/watch?v="+benz.getVideoId();
                    openWebpage(url);
                }
            });


        }
        public void openWebpage(String url) {
            Uri webpage = Uri.parse(url);
            Intent i = new Intent(Intent.ACTION_VIEW, webpage);
            if (i.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(i);

            }
        }
    }
}



