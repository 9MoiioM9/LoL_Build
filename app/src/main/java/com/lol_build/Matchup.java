package com.lol_build;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Matchup extends AppCompatActivity {

    public static String Tag = "LolTag";

    private EditText name_input;
    private Button next_button;
    private Button exit_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matchup);

        name_input = findViewById(R.id.editTextText);
        next_button = findViewById(R.id.button);
        exit_button = findViewById(R.id.button2);

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