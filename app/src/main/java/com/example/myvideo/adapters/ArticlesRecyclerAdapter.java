package com.example.myvideo.adapters;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myvideo.R;
import com.example.myvideo.models.ArticleModel;
import com.example.myvideo.models.BookModel;

import java.util.ArrayList;
import java.util.Collection;

public class ArticlesRecyclerAdapter extends RecyclerView.Adapter<ArticlesRecyclerAdapter.Holder> {


    ArrayList<ArticleModel> list = new ArrayList<>();

    public void setList(ArrayList<ArticleModel> list) {
        this.list = list;

    }

    private OnItemClick onItemClick ;

    public void setOnItemClick (OnItemClick onItemClick){
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent , false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.title.setText(list.get(position).getTitle());
        holder.date.setText(list.get(position).getDate());
        holder.des.setText(list.get(position).getDisc());

       Glide.with(holder.itemView.getContext())
                .load(list.get(position).getImage())
               .into(holder.img);

       holder.add.setOnClickListener(new View.OnClickListener() {
           @SuppressLint("ResourceType")
           @Override
           public void onClick(View view) {
               holder.add.setColorFilter(Color.RED);
           }
       });


    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    class Holder extends RecyclerView.ViewHolder{

        TextView title , date , des;
        ImageView img , add;



        public Holder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.name_txt);
            date = itemView.findViewById(R.id.date_txt);
            des = itemView.findViewById(R.id.over_txt);
            add = itemView.findViewById(R.id.add);
            img = itemView.findViewById(R.id.img);

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

        void OnClick(ArticleModel article);

    }
}
