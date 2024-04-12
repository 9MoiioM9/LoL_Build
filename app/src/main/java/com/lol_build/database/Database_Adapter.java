package com.lol_build.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lol_build.HomePage;
import com.lol_build.R;

import java.util.List;

import coil.ImageLoader;
import coil.request.ImageRequest;

public class Database_Adapter extends RecyclerView.Adapter<Database_Adapter.MyViewHolder>{

    List<MatchupData> matchupData;

    public Database_Adapter(List<MatchupData> matchupData){
        this.matchupData = matchupData;
    }
    @NonNull
    @Override
    public Database_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_item_database, parent, false);
        return new Database_Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Database_Adapter.MyViewHolder holder, int position) {
        holder.champion_enemy.setText(matchupData.get(position).getChampionEnemy_name());
        holder.champion_player.setText(matchupData.get(position).getChampionPlayer_name());
        holder.role.setText(matchupData.get(position).getRole());

        //Management of all images
        String item_URL = "https://ddragon.leagueoflegends.com/cdn/"+ HomePage.VERSION+"/img/item/";
        String champion_URL = "https://ddragon.leagueoflegends.com/cdn/"+HomePage.VERSION+"/img/champion/";

        holder.loadImg(champion_URL+matchupData.get(position).getChampionPlayer_name()+".png", holder.img_champP, holder.itemView.getContext());
        holder.loadImg(champion_URL+matchupData.get(position).getChampionPlayer_name()+".png", holder.img_champP, holder.itemView.getContext());
        holder.loadImg(item_URL+matchupData.get(position).getItems_rec().get(0), holder.img_item1, holder.itemView.getContext());
        holder.loadImg(item_URL+matchupData.get(position).getItems_rec().get(1), holder.img_item2, holder.itemView.getContext());
        holder.loadImg(item_URL+matchupData.get(position).getItems_rec().get(2), holder.img_item3, holder.itemView.getContext());
        holder.loadImg(item_URL+matchupData.get(position).getItems_rec().get(3), holder.img_item4, holder.itemView.getContext());
        holder.loadImg(item_URL+matchupData.get(position).getItems_rec().get(4), holder.img_item5, holder.itemView.getContext());
        holder.loadImg(item_URL+matchupData.get(position).getItems_rec().get(5), holder.img_item6, holder.itemView.getContext());



    }

    @Override
    public int getItemCount() {
        return matchupData.size();
    }




    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView champion_player;
        private TextView champion_enemy;
        private TextView role;
        private ImageView img_champP;
        private ImageView img_champE;
        private ImageView img_item1;
        private ImageView img_item2;
        private ImageView img_item3;
        private ImageView img_item4;
        private ImageView img_item5;
        private ImageView img_item6;

        MyViewHolder(View itemView){
            super(itemView);
            champion_player = itemView.findViewById(R.id.championP_db);
            champion_enemy = itemView.findViewById(R.id.championE_db);
            role = itemView.findViewById(R.id.roleDB);
            img_champP = itemView.findViewById(R.id.icon_champP);
            img_champE = itemView.findViewById(R.id.icon_champE);
            img_item1 = itemView.findViewById(R.id.img_item1);
            img_item2 = itemView.findViewById(R.id.img_item2);
            img_item3 = itemView.findViewById(R.id.img_item3);
            img_item4 = itemView.findViewById(R.id.img_item4);
            img_item5 = itemView.findViewById(R.id.img_item5);
            img_item6 = itemView.findViewById(R.id.img_item6);


        }

        public void loadImg(String imgURL , ImageView imgTarget, Context context){
            ImageLoader imageLoader = new ImageLoader.Builder(context).build();
            imageLoader.enqueue(new ImageRequest.Builder(context)
                    .placeholder(R.drawable.lol_loading)
                    .error(R.drawable.ic_launcher_foreground)
                    .data(imgURL)
                    .target(imgTarget)
                    .build());
        }
    }
}
