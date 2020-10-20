package com.julie.photos.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.julie.photos.R;
import com.julie.photos.model.Photo;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    Context context;
    ArrayList<Photo> photoArrayList;

    public RecyclerViewAdapter(Context context, ArrayList<Photo> photoArrayList) {
        this.context = context;
        this.photoArrayList = photoArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Photo photo = photoArrayList.get(position);
        String title = photo.getTitle();
        String thumbnailUrl = photo.getThumbnailUrl();

        holder.txtTitle.setText(title);
        // 1. 핵심부분 : 이미지의 주소를 글라이드에 셋팅해준다.
        GlideUrl glideUrl = new GlideUrl(thumbnailUrl,
                new LazyHeaders.Builder().addHeader("User-Agent", "Android").build());
        Glide.with(context).load(glideUrl).into( holder.imgThumb  );
    }

    @Override
    public int getItemCount() {
        return photoArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtTitle;
        public ImageView imgThumb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            imgThumb = itemView.findViewById(R.id.imgThumb);
            imgThumb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 두번째 핵심 : url 을 ImgPhoto 클래스에 넘겨준다.
                    int index = getAdapterPosition();
                    Photo photo = photoArrayList.get(index);
                    String url = photo.getUrl();

                    Intent i = new Intent(context, ImgPhoto.class);
                    i.putExtra("url", url);
                    context.startActivity(i);
                }
            });
        }
    }


}
