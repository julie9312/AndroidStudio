package com.julie.todos.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.julie.todos.R;
import com.julie.todos.model.Todos;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Context context;
    ArrayList<Todos> todosArrayList;

    public RecyclerViewAdapter(Context context, ArrayList<Todos> todosArrayList) {
        this.context = context;
        this.todosArrayList = todosArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Todos todos = todosArrayList.get(position);
        String title = todos.getTitle();
        int UserId = todos.getUserId();
        boolean completed = todos.isCompleted();

        holder.txtTitle.setText(title);
        holder.txtUserId.setText("User Id : " + UserId);

        if (completed) {
            holder.txtCompleted.setText("completed : 성공");
        } else {
            holder.txtCompleted.setText("completed : 실패");
        }
    }

    @Override
    public int getItemCount() {
        return todosArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtTitle;
        public TextView txtUserId;
        public TextView txtCompleted;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtUserId = itemView.findViewById(R.id.txtUserId);
            txtCompleted = itemView.findViewById(R.id.txtCompleted);
        }
    }
}





