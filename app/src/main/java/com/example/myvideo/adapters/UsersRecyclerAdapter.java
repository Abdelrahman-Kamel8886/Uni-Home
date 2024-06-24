package com.example.myvideo.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myvideo.R;
import com.example.myvideo.models.CommentModel;
import com.example.myvideo.models.PostModel;
import com.example.myvideo.models.RegModel;

import java.util.ArrayList;

public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.Holder> {


    ArrayList<RegModel> list = new ArrayList<>();

    public void setList(ArrayList<RegModel> list) {
        this.list = list;

    }
    private UsersRecyclerAdapter.OnItemClick onItemClick ;

    public void setOnItemClick (UsersRecyclerAdapter.OnItemClick onItemClick){
        this.onItemClick = onItemClick;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_users, parent , false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.writer.setText(list.get(position).getUsername());

        Glide.with(holder.itemView.getContext())
                .load(list.get(position).getImage())
               .into(holder.img);


    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    class Holder extends RecyclerView.ViewHolder{

        TextView writer ;
        ImageView img ;



        public Holder(@NonNull View itemView) {
            super(itemView);


            writer = itemView.findViewById(R.id.name);
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

        void OnClick(RegModel user);

    }


}
