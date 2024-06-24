package com.example.myvideo.adapters;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myvideo.R;
import com.example.myvideo.models.CourseModel;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class CatsRecyclerAdapter extends RecyclerView.Adapter<CatsRecyclerAdapter.Holder>{


    ArrayList<String> list = new ArrayList<>();

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    private OnItemClick onItemClick ;

    public void setOnItemClick (OnItemClick onItemClick){
        this.onItemClick = onItemClick;
    }

    int row_index = -1;

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cat, parent , false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, @SuppressLint("RecyclerView") int position) {


        holder.title.setText(list.get(position));

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = position;
                notifyDataSetChanged();
            }
        });

        if(row_index == position){
            holder.title.setBackgroundColor(holder.itemView.getResources().getColor(R.color.background2));
            if(onItemClick != null){

                onItemClick.OnClick(list.get(position));
            }
        }
        else{
            holder.title.setBackgroundColor(holder.itemView.getResources().getColor(R.color.grey));
        }




    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }



    class Holder extends RecyclerView.ViewHolder{

         MaterialButton title;



        public Holder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.cat_button);



        }
    }

    public interface OnItemClick{

        void OnClick(String category);

    }
}
