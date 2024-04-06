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
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lol_build.api.Champion;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import coil.Coil;
import coil.ImageLoader;
import coil.request.ImageRequest;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http.StatusLine;

public class Matchup extends AppCompatActivity {

    private Spinner player_choice;
    private Spinner enemy_choice;
    private Button next_button;
    private Button exit_button;
    private Button load_button;
    private TextView test;
    private ImageView player_img;
    private ImageView enemy_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.w(HomePage.Tag, "Ici on est dans matchup");
        setContentView(R.layout.activity_matchup);


        player_choice = findViewById(R.id.spinner_player);
        enemy_choice = findViewById(R.id.spinner_enemy);
        next_button = findViewById(R.id.b_next);
        exit_button = findViewById(R.id.b_exit);

        load_button = findViewById(R.id.b_load);
        test = findViewById(R.id.txt_test);

        player_img = findViewById(R.id.champion_player);
        enemy_img = findViewById(R.id.champion_enemy);

        List<Champion> champions = new ArrayList<>();

        load_button.setOnClickListener((v) -> {
            new Thread(() -> {

                List<String> champions_name = new ArrayList<>();

                String jsonData;
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://ddragon.leagueoflegends.com/cdn/14.7.1/data/fr_FR/champion.json")
                        .build();

                try (Response response = client.newCall(request).execute()) {
                    if (response.isSuccessful()) {
                        jsonData = response.body().string();
                        JsonObject jsonObject = JsonParser.parseString(jsonData).getAsJsonObject();
                        JsonObject championsObject = jsonObject.getAsJsonObject("data");

/*
                        championsObject.keySet().forEach(championKey -> {
                            String name_champion = championsObject.get(championKey).getAsJsonObject().get("name").getAsString();
                            champions_name.add(name_champion);
                        });
 */
                        Log.w(HomePage.Tag, " Désérialisation du json pour la classe Champion");

                        Gson gson = new Gson();
                        for (String championName : championsObject.keySet()) {
                            Champion champion = gson.fromJson(championsObject.get(championName), Champion.class);
                            champions.add(champion);
                            champions_name.add(champion.getName());
                        }

                        Log.w(HomePage.Tag, " Classe Champion opérationnelle !");

                    } else {
                        throw new IOException("Failed to fetch champions: " + response.code());
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                runOnUiThread(()->{

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(Matchup.this, android.R.layout.simple_spinner_item, champions_name);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    player_choice.setAdapter(adapter);
                    enemy_choice.setAdapter(adapter);

                    test.setText(jsonData);

                });
                Log.w(HomePage.Tag, " Start THREAD ");
            }).start();
        });

        player_choice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                new Thread(() -> {

                    String jsonData;
                    OkHttpClient client = new OkHttpClient();
                    String champion_choice = player_choice.getSelectedItem().toString();

                    if(champion_choice != null || champion_choice == "") {

                        String img_url = "https://ddragon.leagueoflegends.com/cdn/14.7.1/img/champion/" + champion_choice + ".png";

                        runOnUiThread(() -> {
                            ImageLoader imageLoader = new ImageLoader.Builder(getApplicationContext())
                                    .build();
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

        exit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO recuperer le nom via le site
                go_machup(v);
            }
        });
    }

    public void go_machup(View v){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        Intent intent = new Intent(this, Result_Build.class);
        startActivity(intent);
    }

}