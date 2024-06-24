package com.example.myvideo.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myvideo.R;
import com.example.myvideo.models.CommentModel;
import com.example.myvideo.models.PostModel;

import java.util.ArrayList;

public class CommentsRecyclerAdapter extends RecyclerView.Adapter<CommentsRecyclerAdapter.Holder> {


    ArrayList<CommentModel> list = new ArrayList<>();

    public void setList(ArrayList<CommentModel> list) {
        this.list = list;

    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent , false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.writer.setText(list.get(position).getName());
        holder.des.setText(list.get(position).getComment());
        Glide.with(holder.itemView.getContext())
                .load(list.get(position).getImg())
               .into(holder.img);


    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    class Holder extends RecyclerView.ViewHolder{

        TextView writer ,des;
        ImageView img ;



        public Holder(@NonNull View itemView) {
            super(itemView);


            writer = itemView.findViewById(R.id.name);
            des = itemView.findViewById(R.id.comment_txt);
            img = itemView.findViewById(R.id.img);



        }
    }


}
