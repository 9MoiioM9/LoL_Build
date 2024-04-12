package com.lol_build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class Search_profil extends AppCompatActivity {

    private EditText pseudo;
    private Button search_pseudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_profil);

        Log.w(HomePage.Tag, "Welcome in the Search_Profil");

        pseudo = findViewById(R.id.in_pseudo);
        search_pseudo = findViewById(R.id.b_search_pseudo);

    }
}