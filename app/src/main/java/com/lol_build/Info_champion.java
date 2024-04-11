package com.lol_build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.lol_build.api.Champion;

import coil.ImageLoader;
import coil.request.ImageRequest;
import okhttp3.OkHttpClient;

public class Info_champion extends AppCompatActivity {

    private Spinner champion_spinner;
    private EditText champion_searched;
    private CardView item_champion;
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
        setContentView(R.layout.activity_info_champion);


        champion_spinner = findViewById(R.id.info_champion_spinner);
        champion_searched = findViewById(R.id.info_champion_editText);
        item_champion = findViewById(R.id.item_info_champion);

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

        ArrayAdapter<String> adapter = new ArrayAdapter<>(Info_champion.this, android.R.layout.simple_spinner_item, HomePage.nameOfAllChampions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        champion_spinner.setAdapter(adapter);

        champion_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Champion selected_champion = HomePage.champions.get(position);
                loadChampion(selected_champion);
                item_champion.setVisibility(View.VISIBLE);

                Intent intent = new Intent(Info_champion.this, champion_item.class);
                intent.putExtra("champion", selected_champion);
                //startActivity(intent);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void loadChampion(Champion champion){
        new Thread(() -> {

            String url = "https://ddragon.leagueoflegends.com/cdn/14.7.1/img/champion/"+champion.getId()+".png";

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