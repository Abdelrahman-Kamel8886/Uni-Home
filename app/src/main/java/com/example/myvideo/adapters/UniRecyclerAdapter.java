package com.example.myvideo.adapters;


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
import com.example.myvideo.models.BookModel;
import com.example.myvideo.models.UniversityModel;

import java.util.ArrayList;
import java.util.Collection;

public class UniRecyclerAdapter extends RecyclerView.Adapter<UniRecyclerAdapter.Holder> implements Filterable {


    ArrayList<UniversityModel> list = new ArrayList<>();
    ArrayList<UniversityModel> ListAll = new ArrayList<>();;

    public void setList(ArrayList<UniversityModel> list) {
        this.list = list;
        ListAll.clear();
        ListAll.addAll(list);
    }

    private OnItemClick onItemClick ;

    public void setOnItemClick (OnItemClick onItemClick){
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent , false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.title.setText(list.get(position).getName());

       Glide.with(holder.itemView.getContext())
                .load(list.get(position).getImage())
               .into(holder.img);


    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Filter getFilter() {
        return myFilter;
    }

    Filter myFilter = new Filter() {

        //Automatic on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            ArrayList<UniversityModel> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(ListAll);
            } else {
                for (UniversityModel d : ListAll) {
                        if (d.getName().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                            filteredList.add(d);
                        }

                }

            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        //Automatic on UI thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            list.clear();
            list.addAll((Collection<? extends UniversityModel>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    class Holder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView img;



        public Holder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.name_txt);
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

        void OnClick(UniversityModel universityModel);

    }
}
