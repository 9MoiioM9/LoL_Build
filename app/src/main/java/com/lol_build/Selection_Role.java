package com.lol_build;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Selection_Role extends AppCompatActivity {
    public static String Tag = "LolTag";
    private Button next_t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_role);

        next_t = findViewById(R.id.b_conf);

        next_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w(Tag, " SUIVANT -> Matchup ");
                go_Matchup(v);
            }
        });


    }

    public void go_Matchup(View view){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        Intent intent = new Intent(this, Matchup.class);
        startActivity(intent);
    }
}