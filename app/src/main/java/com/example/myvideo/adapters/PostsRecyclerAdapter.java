package com.example.myvideo.adapters;


import android.annotation.SuppressLint;
import android.graphics.Color;
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
import com.example.myvideo.models.ArticleModel;
import com.example.myvideo.models.PostModel;

import java.util.ArrayList;

public class PostsRecyclerAdapter extends RecyclerView.Adapter<PostsRecyclerAdapter.Holder> {


    ArrayList<PostModel> list = new ArrayList<>();

    public void setList(ArrayList<PostModel> list) {
        this.list = list;

    }

    private OnItemClick onItemClick ;

    public void setOnItemClick (OnItemClick onItemClick){
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent , false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.writer.setText(list.get(position).getWriter());
        //holder.date.setText(list.get(position).getDate() + "\n"+ list.get(position).getTime());
        holder.des.setText(list.get(position).getPost());

       Glide.with(holder.itemView.getContext())
                .load(list.get(position).getImg())
               .into(holder.img);

       if (list.get(position).getPhoto().isEmpty()){
           holder.cardView.setVisibility(View.GONE);
       }
       else{
           Glide.with(holder.itemView.getContext())
                   .load(list.get(position).getPhoto())
                   .into(holder.photo);
       }



    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    class Holder extends RecyclerView.ViewHolder{

        TextView writer , date , des;
        ImageView img , photo;

        CardView cardView;



        public Holder(@NonNull View itemView) {
            super(itemView);


            writer = itemView.findViewById(R.id.writer);
            date = itemView.findViewById(R.id.date_txt);
            des = itemView.findViewById(R.id.over_txt);

            photo = itemView.findViewById(R.id.photo);
            img = itemView.findViewById(R.id.img);

            cardView = itemView.findViewById(R.id.card1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onItemClick != null){

                        onItemClick.OnClick(list.get(getLayoutPosition()));
                    }
                }
            });

        }
    }

    public interface OnItemClick{

        void OnClick(PostModel post);

    }
}
