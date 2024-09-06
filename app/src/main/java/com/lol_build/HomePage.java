package com.lol_build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lol_build.api.Champions;
import com.lol_build.api.Item;
import com.lol_build.database.AppDatabase;
import com.lol_build.infos.Info_Player;
import com.lol_build.infos.Info_champion;
import com.lol_build.infos.Info_item;
import com.lol_build.infos.Preferencies;
import com.lol_build.infos.User_History;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class HomePage extends AppCompatActivity {
    public static String Tag = "LolTag";
    public static List<Champions> champions = new ArrayList<>();
    public static List<Item> items = new ArrayList<>();
    public static List<Item> itemsPurchasable = new ArrayList<>();
    public static List<String> nameOfAllChampions = new ArrayList<>();
    public static List<String> nameOfAllItems = new ArrayList<>();
    public static List<String> nameOfAllItemsPurchasable = new ArrayList<>();

    public static String VERSION;
    public static String LANGUAGE;
    private Locale locale = Locale.getDefault();
    private Button history_button;
    private Button machup_button;
    private Button items_button;
    private Button champ_button;
    private Button quit_button;

    private ImageView img_homepage;

    public static AppDatabase database;

    private Button search_profil_button;
    private Button btn_param;
    private TextView patch_Note;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Log.w(Tag, "Welcome in the HomePage");

        //Management default parameters
        LANGUAGE = locale.getLanguage()+'_'+locale.getCountry();


        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, AppDatabase.DATABASE_NAME).build();


        history_button = findViewById(R.id.history_homepage);
        machup_button = findViewById(R.id.b_matchup);
        items_button = findViewById(R.id.b_item);
        champ_button = findViewById(R.id.b_champion);
        quit_button = findViewById(R.id.b_quit);
        btn_param = findViewById(R.id.param_homepage);
        patch_Note = findViewById(R.id.txt_patchNote);


        img_homepage = findViewById(R.id.img_homepage_lol);
        search_profil_button = findViewById(R.id.b_profil_search);


        new Thread(() -> {
            Log.w(Tag, "LANGUAGE : "+LANGUAGE);
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

            runOnUiThread(() -> {
                String patch_note = "Patch "+VERSION;
                patch_Note.setText(patch_note);
            });

            loadData();

        }).start();

        quit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        machup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, Selection_Role.class);
                startActivity(intent);
            }
        });

        items_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, Info_item.class);
                startActivity(intent);
            }
        });

        champ_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, Info_champion.class);
                startActivity(intent);
            }
        });

        search_profil_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, Info_Player.class);
                startActivity(intent);
            }
        });

        btn_param.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, Preferencies.class);
                startActivity(intent);
            }
        });

        history_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, User_History.class);
                startActivity(intent);
            }
        });
    }

    //onRestart to load all lists if new language detected (ref Preferencies/Parameters)
    @Override
    public void onRestart() {
        super.onRestart();

        Log.w(Tag, "Passe dans le Restart !");

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String selectedLanguage = sharedPreferences.getString("language", locale.getLanguage()+'_'+locale.getCountry());
        Log.w(Tag, "Selected Language : "+selectedLanguage);
        Log.w(Tag, "Language : "+LANGUAGE);
        if(!LANGUAGE.contentEquals(selectedLanguage)) {
            //Set language for all request in the project
            LANGUAGE = selectedLanguage;
            Log.w(Tag, "New Language : "+LANGUAGE);
            //Set locale language for all texte
            locale = new Locale(selectedLanguage);
            Locale.setDefault(locale);

            Configuration config = getResources().getConfiguration();
            config.setLocale(locale);
            Context context = createConfigurationContext(config);
            Resources resources = context.getResources();


            //Clear all list to reload them with data according to the new language
            champions.clear();
            nameOfAllChampions.clear();
            items.clear();
            itemsPurchasable.clear();
            nameOfAllItems.clear();
            nameOfAllItemsPurchasable.clear();

            new Thread(this::loadData).start();
        }else
            Log.w(Tag, "Nothing changed");
    }


    public void loadData(){


        Log.w(Tag, "LANGUAGE : " + LANGUAGE);

        String jsonData;
        OkHttpClient client = new OkHttpClient();

        //Request for champions
        Request request_champion = new Request.Builder()
                .url("https://ddragon.leagueoflegends.com/cdn/"+VERSION+"/data/"+LANGUAGE+"/champion.json")
                .build();

        Log.w(Tag, "URL CHampion  : " + request_champion.url());
        try (Response response = client.newCall(request_champion).execute()) {
            if (response.isSuccessful()) {
                jsonData = response.body().string();
                JsonObject jsonObject = JsonParser.parseString(jsonData).getAsJsonObject();
                JsonObject championsObject = jsonObject.getAsJsonObject("data");

                Log.w(Tag, "Deserialisation for Class Champion");
                //champions_name.add(null);

                Gson gson = new Gson();
                for (String championData : championsObject.keySet()) {
                    Champions champion = gson.fromJson(championsObject.get(championData), Champions.class);
                    champions.add(champion);

                    nameOfAllChampions.add(champion.getName());
                }
                Log.w(Tag, "Class Champions works !");

            } else {
                throw new IOException("Failed to fetch champions: " + response.code());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//=============================================================================================
        //Request for items
        Request request_items = new Request.Builder()
                .url("https://ddragon.leagueoflegends.com/cdn/"+VERSION+"/data/"+LANGUAGE+"/item.json")
                .build();

        Log.w(Tag, "URL CHampion  : " + request_items.url());
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
    }
}