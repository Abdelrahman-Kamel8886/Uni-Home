package com.example.myvideo.adapters;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myvideo.R;
import com.example.myvideo.models.CourseModel;

import java.util.ArrayList;

public class PdfsRecyclerAdapter extends RecyclerView.Adapter<PdfsRecyclerAdapter.Holder>{


    ArrayList<CourseModel.PdfsDTO> list = new ArrayList<>();

    public void setList(ArrayList<CourseModel.PdfsDTO> list) {
        this.list = list;
        Log.e("TAG", "setList: "+list.size() );
    }

    private OnItemClick onItemClick ;

    public void setOnItemClick (OnItemClick onItemClick){
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pdf, parent , false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {


        holder.title.setText(list.get(position).getTitle());


    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }



    class Holder extends RecyclerView.ViewHolder{

        TextView title;



        public Holder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.pdf_name);

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

        void OnClick(CourseModel.PdfsDTO pdf);

    }
}
