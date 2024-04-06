package com.lol_build;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import coil.ImageLoader;
import coil.request.ImageRequest;

public class Selection_Role extends AppCompatActivity {
    private Button confirm_button;

    private Button back_button;
    private ImageView map_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_role);

        confirm_button = findViewById(R.id.b_confirm_role);
        back_button = findViewById(R.id.b_back_role);
        map_img = findViewById(R.id.i_map);

        new Thread(() -> {
            String img_url = "https://ddragon.leagueoflegends.com/cdn/6.8.1/img/map/map11.png";

            runOnUiThread(() -> {
                ImageLoader imageLoader = new ImageLoader.Builder(getApplicationContext()).build();
                imageLoader.enqueue(new ImageRequest.Builder(getApplicationContext())
                        .data(img_url)
                        .target(map_img)
                        .build());
            });
        }).start();

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w(HomePage.Tag, " SUIVANT -> Matchup ");
                go_Matchup(v);
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w(HomePage.Tag, "Back to HomePage");
                finish();
            }
        });




    }

    public void go_Matchup(View view){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        Intent intent = new Intent(this, Matchup.class);
        startActivity(intent);
    }
}