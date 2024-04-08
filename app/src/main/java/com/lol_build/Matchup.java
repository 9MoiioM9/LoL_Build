package com.lol_build;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lol_build.api.Champion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import coil.ImageLoader;
import coil.request.ImageRequest;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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

        Log.w(HomePage.Tag, "Ici on est dans matchup");
        setContentView(R.layout.activity_matchup);


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

                        String img_url = "https://ddragon.leagueoflegends.com/cdn/14.7.1/img/champion/" + selected_champion + ".png";

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
                // TODO recuperer le nom via le site
                go_result_build(v);
            }
        });
    }

    public void launchItemChampion(Champion selected_champion){
        Intent intent = new Intent(Matchup.this, champion_item.class);
        intent.putExtra("champion", selected_champion);
        startActivity(intent);
    }

    public void go_result_build(View v){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        Intent intent = new Intent(this, Result_Build.class);
        startActivity(intent);
    }

}