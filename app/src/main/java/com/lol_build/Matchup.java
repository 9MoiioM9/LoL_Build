package com.lol_build;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lol_build.api.Champions;

import coil.ImageLoader;
import coil.request.ImageRequest;
import okhttp3.OkHttpClient;

public class Matchup extends AppCompatActivity {

    public static String ROLE = "";
    private RadioButton rb_top;
    private RadioButton rb_jungle;
    private RadioButton rb_mid;
    private RadioButton rb_adc;
    private RadioButton rb_support;

    private Spinner player_choice;
    private TextView name_of_player_champion;
    private TextView title_of_player_champion;
    private Spinner enemy_choice;
    private TextView name_of_enemy_champion;
    private TextView title_of_enemy_champion;
    private Button confirm_button;
    private Button back_button;
    private ImageView player_img;
    private ImageView enemy_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_matchup);
        Log.w(HomePage.Tag, "Welcome in the Matchup");

        // Management radio_button for roles
        rb_top = findViewById(R.id.rb_top);
        rb_jungle = findViewById(R.id.rb_jg);
        rb_mid = findViewById(R.id.rb_mid);
        rb_adc = findViewById(R.id.rb_adc);
        rb_support = findViewById(R.id.rb_supp);

        //Management of the choice of champions
        //Player choice
        player_choice = findViewById(R.id.spinner_player);
        name_of_player_champion = findViewById(R.id.name_of_player_champion);
        title_of_player_champion = findViewById(R.id.title_of_player_champion);
        player_img = findViewById(R.id.champion_player);

        //Enemy choice
        name_of_enemy_champion = findViewById(R.id.name_of_enemy_champion);
        title_of_enemy_champion = findViewById(R.id.title_of_enemy_champion);
        enemy_choice = findViewById(R.id.spinner_enemy);
        enemy_img = findViewById(R.id.champion_enemy);

        confirm_button = findViewById(R.id.b_confirm_matchup);
        back_button = findViewById(R.id.b_back_matchup);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(Matchup.this, android.R.layout.simple_spinner_item, HomePage.nameOfAllChampions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        player_choice.setAdapter(adapter);
        enemy_choice.setAdapter(adapter);


        //Management of roles
        rb_top.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ROLE = "Top";
                }
            }
        });

        rb_jungle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    ROLE = "Jungle";
                }
            }
        });

        rb_mid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ROLE = "Mid";
                }
            }
        });

        rb_adc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ROLE = "Adc";
                }
            }
        });

        rb_support.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ROLE = "Support";
                }
            }
        });


        //Management of the matchup between champions
        player_choice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                new Thread(() -> {

                    Champions selected_champion = HomePage.champions
                            .get(position); //+ 1

                    if(selected_champion != null) {

                        String img_url = "https://ddragon.leagueoflegends.com/cdn/"+HomePage.VERSION+"/img/champion/"+selected_champion.getId()+".png";

                        runOnUiThread(() -> {
                            ImageLoader imageLoader = new ImageLoader.Builder(getApplicationContext()).build();
                            imageLoader.enqueue(new ImageRequest.Builder(getApplicationContext())
                                    .data(img_url)
                                    .target(player_img)
                                    .build());

                            name_of_player_champion.setText(selected_champion.getName());
                            title_of_player_champion.setText(selected_champion.getTitle());
                        });
                    }

                }).start();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //DO nothing
            }
        });

        enemy_choice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                new Thread(() -> {

                    Champions selected_champion = HomePage.champions
                            .get(position); //+ 1

                    if(selected_champion != null) {

                        String img_url = "https://ddragon.leagueoflegends.com/cdn/"+HomePage.VERSION+"/img/champion/" + selected_champion.getId() + ".png";

                        runOnUiThread(() -> {
                            ImageLoader imageLoader = new ImageLoader.Builder(getApplicationContext()).build();
                            imageLoader.enqueue(new ImageRequest.Builder(getApplicationContext())
                                    .data(img_url)
                                    .target(enemy_img)
                                    .build());

                            name_of_enemy_champion.setText(selected_champion.getName());
                            title_of_enemy_champion.setText(selected_champion.getTitle());
                        });
                    }

                }).start();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //DO nothing
            }
        });

        player_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchItemChampion(HomePage
                        .champions
                        .get(player_choice.getSelectedItemPosition()));
            }
        });

        enemy_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchItemChampion(HomePage
                        .champions
                        .get(enemy_choice.getSelectedItemPosition()));
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w(HomePage.Tag, "Back to HomePage");
                finish();
            }
        });

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ROLE.isEmpty()){
                    Intent intent = new Intent(Matchup.this, Result_Build.class);
                    intent.putExtra("player_champion",
                            HomePage.champions
                                    .get(player_choice.getSelectedItemPosition()));

                    intent.putExtra("enemy_champion",
                            HomePage.champions
                                    .get(enemy_choice.getSelectedItemPosition()));

                    startActivity(intent);
                }else Toast.makeText(getApplicationContext(), "Warning : no position have been picked !", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void launchItemChampion(Champions selected_champion){
        Intent intent = new Intent(Matchup.this, champion_item.class);
        intent.putExtra("champion", selected_champion);
        startActivity(intent);
    }



}