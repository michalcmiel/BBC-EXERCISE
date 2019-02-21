package com.example.bbcapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class FruitsArrayAdapter extends RecyclerView.Adapter<FruitsArrayAdapter.ViewHolder> {

    //All methods in this adapter are required for a bare minimum recyclerview adapter
    private int listItemLayout;
    public ArrayList<Fruit> fruitList;
    private Context context;
    // Constructor of the class
    public FruitsArrayAdapter(int layoutId, ArrayList<Fruit> fruitList, Context context) {
        listItemLayout = layoutId;
        this.fruitList = fruitList;
        this.context = context;
    }

    // get the size of the list
    @Override
    public int getItemCount() {
        return fruitList == null ? 0 : fruitList.size();
    }


    // specify the row layout file and click for each row
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(listItemLayout, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    // load data in each row element
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        TextView item = holder.item;
        item.setText(
            fruitList.get(listPosition).getFruit_name());
    }

    // Static inner class to initialize the views of rows
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView item;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            item = (TextView) itemView.findViewById(R.id.row_item);
        }
        @Override
        public void onClick(View view) {
            Intent fruitDetails = new Intent(context, FruitDetails.class);
            fruitDetails.putExtra("fruit", fruitList.get(getAdapterPosition()));
            context.startActivity(fruitDetails);
        }
    }
}


