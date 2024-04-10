package com.lol_build;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lol_build.api.Champion;
import com.lol_build.api.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class HomePage extends AppCompatActivity {
    public static String Tag = "LolTag";
    public static List<Champion> champions = new ArrayList<>();
    public static List<Item> items = new ArrayList<>();
    public static List<Item> itemsPurchasable = new ArrayList<>();
    public static List<String> nameOfAllChampions = new ArrayList<>();
    public static List<String> nameOfAllItems = new ArrayList<>();
    public static List<String> nameOfAllItemsPurchasable = new ArrayList<>();

    public static String VERSION;
    private Button pref_button;
    private Button machup_button;
    private Button items_button;
    private Button champ_button;
    private Button quit_button;
    private Button search_profil_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        pref_button = findViewById(R.id.preferencies);
        machup_button = findViewById(R.id.b_matchup);
        items_button = findViewById(R.id.b_item);
        champ_button = findViewById(R.id.b_champion);
        quit_button = findViewById(R.id.b_quit);
        search_profil_button = findViewById(R.id.b_profil_search);

        new Thread(() -> {
            //Request for version
            String jsonData;
            OkHttpClient client = new OkHttpClient();
            Request request_version = new Request.Builder()
                    .url("https://ddragon.leagueoflegends.com/api/versions.json")
                    .build();

            try (Response response = client.newCall(request_version).execute()) {
                if (response.isSuccessful()) {
                    jsonData = response.body().string();
                    JsonArray versionArray = JsonParser.parseString(jsonData).getAsJsonArray();

                    Log.w(Tag, "Get the latest Version ...");

                    if (versionArray.size() > 0)
                        VERSION = versionArray.get(0).getAsString();

                    Log.d(Tag, "Latest version : " + VERSION);

                } else {
                    throw new IOException("Failed to fetch the Version: " + response.code());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
//=============================================================================================
            //Request for champions
            Request request_champion = new Request.Builder()
                    .url("https://ddragon.leagueoflegends.com/cdn/"+VERSION+"/data/fr_FR/champion.json")
                    .build();

            try (Response response = client.newCall(request_champion).execute()) {
                if (response.isSuccessful()) {
                    jsonData = response.body().string();
                    JsonObject jsonObject = JsonParser.parseString(jsonData).getAsJsonObject();
                    JsonObject championsObject = jsonObject.getAsJsonObject("data");

                    Log.w(Tag, "Deserialisation for Class Champion");
                    //champions_name.add(null);

                    Gson gson = new Gson();
                    for (String championData : championsObject.keySet()) {
                        Champion champion = gson.fromJson(championsObject.get(championData), Champion.class);
                        champions.add(champion);


                        nameOfAllChampions.add(champion.getName());
                    }

                    Log.w(Tag, "Class Champion works !");

                } else {
                    throw new IOException("Failed to fetch champions: " + response.code());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
//=============================================================================================
            //Request for items
            Request request_items = new Request.Builder()
                    .url("https://ddragon.leagueoflegends.com/cdn/"+VERSION+"/data/fr_FR/item.json")
                    .build();

            try (Response response = client.newCall(request_items).execute()) {
                if (response.isSuccessful()) {
                    jsonData = response.body().string();
                    JsonObject jsonObject = JsonParser.parseString(jsonData).getAsJsonObject();
                    JsonObject itemsObject = jsonObject.getAsJsonObject("data");

                    Log.w(Tag, "Deserialisation for Class Item");

                    Gson gson = new Gson();
                    for (String itemData : itemsObject.keySet()) {
                        Item item = gson.fromJson(itemsObject.get(itemData), Item.class);
                        items.add(item);

                        nameOfAllItems.add(item.getName());
                        if(item.getGold().isPurchasable()){
                            itemsPurchasable.add(item);
                            nameOfAllItemsPurchasable.add(item.getName());
                        }
                    }

                    Log.w(Tag, "Class Item works !");

                } else {
                    throw new IOException("Failed to fetch champions: " + response.code());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).start();

        quit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        pref_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go_preferencie(v);
            }
        });

        machup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w(Tag, " avant le go_selecRole");
                go_selecRole(v);
            }
        });

        items_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go_item(v);
            }
        });

        champ_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go_champ(v);
            }
        });

        search_profil_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go_search_player(v);
            }
        });
    }

    public void go_preferencie(View view){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        Intent intent = new Intent(this, Preferencies.class);
        startActivity(intent);
    }

    public void go_selecRole(View view){
        Log.w(Tag, " dans le go_selecRole au debut");
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        Intent intent = new Intent(this, Selection_Role.class);
        Log.w(Tag, " dans le go_selecRole avant start activity");
        startActivity(intent);
    }

    public void go_item(View view){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        Intent intent = new Intent(this, Info_item.class);
        startActivity(intent);
    }

    public void go_champ(View view){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        Intent intent = new Intent(this, Info_champion.class);
        startActivity(intent);
    }

    public void go_search_player(View view){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        Intent intent = new Intent(this, Search_profil.class);
        startActivity(intent);
    }
}