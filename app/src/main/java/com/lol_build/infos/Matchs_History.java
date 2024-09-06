package com.lol_build.infos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.lol_build.R;
import com.lol_build.api.Champions;

public class Matchs_History extends AppCompatActivity {
    private TextView player_name;
    private String player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matchs_history);

        player_name = findViewById(R.id.name_player_match_history);

        Intent intent = getIntent();
        player = intent.getStringExtra("player_name");

        player_name.setText(player);
    }
}