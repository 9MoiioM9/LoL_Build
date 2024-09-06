package com.lol_build.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lol_build.R;

public class Match_Adapter extends RecyclerView.Adapter<Match_Adapter.MyViewHolderMatch>{

    @NonNull
    @Override
    public Match_Adapter.MyViewHolderMatch onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_item_match, parent, false);
        return new Match_Adapter.MyViewHolderMatch(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Match_Adapter.MyViewHolderMatch holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyViewHolderMatch extends RecyclerView.ViewHolder{

        public MyViewHolderMatch(View itemView) {
            super(itemView);
        }
    }
}
