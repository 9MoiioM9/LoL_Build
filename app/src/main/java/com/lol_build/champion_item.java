package com.lol_build;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.lol_build.api.Champions;

import coil.ImageLoader;
import coil.request.ImageRequest;

public class champion_item extends AppCompatActivity {
    private ImageView icon_champion;
    private TextView name;
    private TextView title;
    private TextView hp;
    private TextView mana;
    private TextView ad;
    private TextView ap;
    private TextView armor;
    private TextView resistance_magique;
    private TextView as;
    private TextView speed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champion_item);

        Log.w(HomePage.Tag, "Welcome in the champion_item");

        icon_champion = findViewById(R.id.icon_champion);
        name = findViewById(R.id.champion_name);
        title = findViewById(R.id.champion_title);
        hp = findViewById(R.id.hp_champion);
        mana = findViewById(R.id.mana_champion);
        ad = findViewById(R.id.atk);
        ap = findViewById(R.id.ap);
        armor = findViewById(R.id.armor);
        resistance_magique = findViewById(R.id.rm);
        as = findViewById(R.id.atkSpeed);
        speed = findViewById(R.id.speed);


        Intent intent = getIntent();
        Champions champion_spinner = (Champions) intent.getSerializableExtra("champion");



        if(champion_spinner != null){
            Log.w(HomePage.Tag, champion_spinner.toString());
            loadChampion(champion_spinner);
        }

    }

    public void loadChampion(Champions champion){
        new Thread(() -> {

            String url = "https://ddragon.leagueoflegends.com/cdn/"+HomePage.VERSION+"/img/champion/"+champion.getId()+".png";

            runOnUiThread(() -> {
                ImageLoader imageLoader = new ImageLoader.Builder(getApplicationContext()).build();
                imageLoader.enqueue(new ImageRequest.Builder(getApplicationContext())
                        .data(url)
                        .target(icon_champion)
                        .build());

                name.setText(champion.getName());
                title.setText(champion.getTitle());
                hp.setText(String.valueOf(champion.getStats().getHp()));
                mana.setText(String.valueOf(champion.getStats().getMp()));
                ad.setText(String.valueOf(champion.getStats().getAttackdamage()));
                armor.setText(String.valueOf(champion.getStats().getArmor()));
                resistance_magique.setText(String.valueOf(champion.getStats().getSpellblock()));
                as.setText(String.valueOf(champion.getStats().getAttackspeed()));
                speed.setText(String.valueOf(champion.getStats().getMovespeed()));

                switch (champion.getPartype()){
                    case "Mana":
                        mana.setBackgroundColor(Color.parseColor("#03A9F4"));
                        break;
                    case "Énergie":
                        mana.setBackgroundColor(Color.parseColor("#FFC107"));
                        break;
                    case "Fureur":
                        mana.setBackgroundColor(Color.parseColor("#F44336"));
                        break;
                    case "Rage":
                        mana.setBackgroundColor(Color.parseColor("#F38004"));
                        break;
                    default :
                        mana.setBackgroundColor(Color.GRAY);
                        break;
                }

            });


        }).start();
    }
}