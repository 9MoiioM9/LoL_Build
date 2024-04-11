package com.lol_build;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import coil.ImageLoader;
import coil.request.ImageRequest;

public class Selection_Role extends AppCompatActivity {
    public static String ROLE = "";
    private Button confirm_button;

    private Button back_button;
    private ImageView map_img;
    private RadioGroup rb_grp_role;
    private RadioGroup rb_grp_bot;
    private RadioButton rb_top;
    private RadioButton rb_jungle;
    private RadioButton rb_mid;
    private RadioButton rb_bottom;
    private RadioButton rb_adc;
    private RadioButton rb_support;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_role);

        confirm_button = findViewById(R.id.b_confirm_role);
        back_button = findViewById(R.id.b_back_role);
        map_img = findViewById(R.id.i_map);

        rb_grp_role = findViewById(R.id.radioGrp_Role);
        rb_grp_bot = findViewById(R.id.radioGrp_Bot);
        rb_top = findViewById(R.id.rb_top);
        rb_jungle = findViewById(R.id.rb_jg);
        rb_mid = findViewById(R.id.rb_mid);
        rb_bottom = findViewById(R.id.rb_bot);
        rb_adc = findViewById(R.id.rb_adc);
        rb_support = findViewById(R.id.rb_supp);

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

        rb_bottom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    rb_grp_bot.setVisibility(View.VISIBLE);
                }else rb_grp_bot.setVisibility(View.INVISIBLE);
            }
        });

        rb_top.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ROLE = "top";
                }
            }
        });

        rb_jungle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    ROLE = "jungle";
                }
            }
        });

        rb_mid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ROLE = "middle";
                }
            }
        });

        rb_adc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ROLE = "bot";
                }
            }
        });

        rb_support.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ROLE = "supporter";
                }
            }
        });

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ROLE.length() > 0){
                    Log.w(HomePage.Tag, " SUIVANT -> Matchup ");
                    go_Matchup(v);
                }else Toast.makeText(getApplicationContext(), "Warning : no position have been picked !", Toast.LENGTH_SHORT).show();
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