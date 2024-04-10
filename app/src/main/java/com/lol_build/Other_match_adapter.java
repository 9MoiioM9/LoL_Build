package com.lol_build;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Other_match_adapter extends RecyclerView.Adapter<Other_match_adapter.MyViewHolder> {
    List<Other_match_item> other_matchs;

    Other_match_adapter(List<Other_match_item> other_matchs){
        this.other_matchs = other_matchs;
    }
    @NonNull
    @Override
    public Other_match_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_other_match_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Other_match_adapter.MyViewHolder holder, int position) {
        holder.display(other_matchs.get(position));
    }

    @Override
    public int getItemCount() {
        return other_matchs.size();
    }




    class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView icon_champion;
        private ListView runes;
        private ImageView summoner1;
        private ImageView summoner2;
        private TextView gamemode;
        private TextView dateGame;
        private TextView timeGame;
        private TextView kda;
        private ListView items;
        private ListView matchMacking;

        //appel de tout les attributs de other_match_item
        MyViewHolder(View itemView){
            super(itemView);
            icon_champion = itemView.findViewById(R.id.icon_champ);
            runes = itemView.findViewById(R.id.listRunes);
            summoner1 = itemView.findViewById(R.id.summoner1);
            summoner2 = itemView.findViewById(R.id.summoner2);
            gamemode = itemView.findViewById(R.id.gamemode);
            dateGame = itemView.findViewById(R.id.dateGame);
            timeGame = itemView.findViewById(R.id.timeGame);
            kda = itemView.findViewById(R.id.kda);

        }

        void display(Other_match_item other_matchs){

            kda.setText(other_matchs.getTest());

        }
    }
}

