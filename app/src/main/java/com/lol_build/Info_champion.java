package com.lol_build;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.lol_build.api.Champion;

public class Info_champion extends AppCompatActivity {

    private Spinner champion_spinner;
    private EditText champion_searched;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_champion);


        champion_spinner = findViewById(R.id.info_champion_spinner);
        champion_searched = findViewById(R.id.info_champion_editText);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(Info_champion.this, android.R.layout.simple_spinner_item, HomePage.nameOfAllChampions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        champion_spinner.setAdapter(adapter);

        champion_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Champion selected_champion = HomePage.champions.get(position);
                Intent intent = new Intent(Info_champion.this, champion_item.class);

                intent.putExtra("champion", selected_champion);
                startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}