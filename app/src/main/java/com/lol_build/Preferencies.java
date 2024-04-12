package com.lol_build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lol_build.database.Database_Adapter;
import com.lol_build.database.MatchupDao;
import com.lol_build.database.MatchupData;

import java.util.ArrayList;

public class Preferencies extends AppCompatActivity {
    private Button back;
    private Button loadDB;
    public RecyclerView items;
    public ArrayList<MatchupData> matchupData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencies);

        Log.w(HomePage.Tag, "Welcome in the Preferencies");

        back = findViewById(R.id.pref_back);
        loadDB = findViewById(R.id.pref_load);

        items = findViewById(R.id.recycler_pref);

        loadDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(() -> {
                    MatchupDao matchupDao = HomePage.database.matchupDao();
                    matchupData = new ArrayList<>(matchupDao.getAll());

                    Database_Adapter adapter = new Database_Adapter(matchupData);

                    runOnUiThread(() -> {
                        items.setAdapter(adapter);
                        items.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    });
                }).start();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}