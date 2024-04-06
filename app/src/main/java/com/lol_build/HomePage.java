package com.lol_build;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class HomePage extends AppCompatActivity {
    public static String Tag = "LolTag";
    private Button pref_button;
    private Button machup_button;
    private Button items_button;
    private Button champ_button;
    private Button quit_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //Configuration de Orianna pour utilisation
        //Orianna.setRiotAPIKey("RGAPI-43b70175-c1d2-422a-bb32-6f74542043b5");

        //Orianna.Configuration config = new Orianna.Configuration();
        //config.setDefaultPlatform(Platform.EUROPE_WEST);


        //Orianna.setDefaultRegion(Region.EUROPE_WEST);
        //config.setCurrentVersionExpiration(ExpirationPeriod.create(6, TimeUnit.HOURS));

        //Orianna.loadConfiguration(config);


        pref_button = findViewById(R.id.preferencies);
        machup_button = findViewById(R.id.b_matchup);
        items_button = findViewById(R.id.b_item);
        champ_button = findViewById(R.id.b_champion);
        quit_button = findViewById(R.id.b_quit);

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
}