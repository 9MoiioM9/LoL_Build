package com.lol_build;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.lol_build.api.Champions;

import coil.ImageLoader;
import coil.request.ImageRequest;
import okhttp3.OkHttpClient;

public class Matchup extends AppCompatActivity {

    private Spinner player_choice;
    private Spinner enemy_choice;
    private Button confirm_button;
    private Button back_button;
    private ImageView player_img;
    private ImageView enemy_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_matchup);
        Log.w(HomePage.Tag, "Welcome in the Matchup");


        player_choice = findViewById(R.id.spinner_player);
        enemy_choice = findViewById(R.id.spinner_enemy);
        confirm_button = findViewById(R.id.b_confirm_matchup);
        back_button = findViewById(R.id.b_back_matchup);

        player_img = findViewById(R.id.champion_player);
        enemy_img = findViewById(R.id.champion_enemy);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(Matchup.this, android.R.layout.simple_spinner_item, HomePage.nameOfAllChampions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        player_choice.setAdapter(adapter);
        enemy_choice.setAdapter(adapter);



        player_choice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                new Thread(() -> {

                    String jsonData;
                    OkHttpClient client = new OkHttpClient();
                    String selected_champion = HomePage.champions
                            .get(position) //+ 1)
                            .getId();

                    if(selected_champion != null) {

                        String img_url = "https://ddragon.leagueoflegends.com/cdn/"+HomePage.VERSION+"/img/champion/"+selected_champion+".png";

                        runOnUiThread(() -> {
                            ImageLoader imageLoader = new ImageLoader.Builder(getApplicationContext()).build();
                            imageLoader.enqueue(new ImageRequest.Builder(getApplicationContext())
                                    .data(img_url)
                                    .target(player_img)
                                    .build());
                        });
                    }

                }).start();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //DO nothing
            }
        });

        enemy_choice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                new Thread(() -> {

                    String jsonData;
                    OkHttpClient client = new OkHttpClient();
                    String selected_champion = HomePage.champions
                            .get(position) //+ 1)
                            .getId();

                    if(selected_champion != null) {

                        String img_url = "https://ddragon.leagueoflegends.com/cdn/"+HomePage.VERSION+"/img/champion/" + selected_champion + ".png";

                        runOnUiThread(() -> {
                            ImageLoader imageLoader = new ImageLoader.Builder(getApplicationContext()).build();
                            imageLoader.enqueue(new ImageRequest.Builder(getApplicationContext())
                                    .data(img_url)
                                    .target(enemy_img)
                                    .build());
                        });
                    }

                }).start();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //DO nothing
            }
        });

        player_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchItemChampion(HomePage
                        .champions
                        .get(player_choice.getSelectedItemPosition()));
            }
        });

        enemy_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchItemChampion(HomePage
                        .champions
                        .get(enemy_choice.getSelectedItemPosition()));
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Matchup.this, Result_Build.class);
                intent.putExtra("player_champion",
                        HomePage.champions
                                .get(player_choice.getSelectedItemPosition()));

                intent.putExtra("enemy_champion",
                        HomePage.champions
                                .get(enemy_choice.getSelectedItemPosition()));

                startActivity(intent);
            }
        });
    }

    public void launchItemChampion(Champions selected_champion){
        Intent intent = new Intent(Matchup.this, champion_item.class);
        intent.putExtra("champion", selected_champion);
        startActivity(intent);
    }



}