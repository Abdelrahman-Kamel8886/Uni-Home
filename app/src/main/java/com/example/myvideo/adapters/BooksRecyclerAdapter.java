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

import java.util.ArrayList;
import java.util.Collection;

public class BooksRecyclerAdapter extends RecyclerView.Adapter<BooksRecyclerAdapter.Holder> implements Filterable {


    ArrayList<BookModel> list = new ArrayList<>();
    ArrayList<BookModel> ListAll = new ArrayList<>();;

    public void setList(ArrayList<BookModel> list) {
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

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book2, parent , false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.title.setText(list.get(position).getBook_title());

       Glide.with(holder.itemView.getContext())
                .load(list.get(position).getImglink())
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

            ArrayList<BookModel> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(ListAll);
            } else {
                for (BookModel d : ListAll) {
                        if (d.getBook_title().toLowerCase().contains(charSequence.toString().toLowerCase())) {
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
            list.addAll((Collection<? extends BookModel>) filterResults.values);
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

        void OnClick(BookModel book);

    }
}
