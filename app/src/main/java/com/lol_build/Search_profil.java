package com.lol_build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class Search_profil extends AppCompatActivity {

    private EditText pseudo;
    private RecyclerView match_pseudo;
    private Button search_pseudo;
    private List<Other_match_item> other_match;
    private Other_match_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_profil);

        pseudo = findViewById(R.id.in_pseudo);
        search_pseudo = findViewById(R.id.b_search_pseudo);
        match_pseudo = findViewById(R.id.recyclerViewMatch);

        other_match = new ArrayList<>();

        other_match.add(new Other_match_item("test1"));
        other_match.add(new Other_match_item("test2"));
        other_match.add(new Other_match_item("test3"));
        other_match.add(new Other_match_item("test4"));
        other_match.add(new Other_match_item("test5"));
        other_match.add(new Other_match_item("test6"));
        other_match.add(new Other_match_item("test7"));

        adapter = new Other_match_adapter(other_match);

        match_pseudo.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        match_pseudo.setAdapter(adapter);

    }
}