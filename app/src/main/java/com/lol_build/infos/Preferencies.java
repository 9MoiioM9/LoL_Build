package com.lol_build.infos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SharedMemory;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lol_build.HomePage;
import com.lol_build.R;

import org.intellij.lang.annotations.Language;

import java.util.ArrayList;
import java.util.List;

public class Preferencies extends AppCompatActivity {

    private String new_language;
    private RadioGroup choice_en;
    private RadioButton rb_fr;
    private RadioButton rb_en;
    private RadioButton rb_gb;
    private RadioButton rb_us;
    private RadioButton rb_it;
    private AutoCompleteTextView champion_choose;
    private Button btn_back;
    private Button btn_apply;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencies);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        choice_en = findViewById(R.id.choice_en);
        rb_fr = findViewById(R.id.rb_fr);
        rb_en = findViewById(R.id.rb_en);
        rb_gb = findViewById(R.id.rb_en_GB);
        rb_us = findViewById(R.id.rb_en_US);
        rb_it = findViewById(R.id.rb_it);
        champion_choose = findViewById(R.id.autoCplt_champion_param);
        btn_back = findViewById(R.id.pref_back);
        btn_apply = findViewById(R.id.pref_apply);

        languageChecked();

        rb_en.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    choice_en.setVisibility(View.VISIBLE);
                }else
                    choice_en.setVisibility(View.INVISIBLE);
            }
        });

        rb_fr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    new_language = "fr_FR";
                    Log.w(HomePage.Tag, "Langage is now : "+new_language);
                    btn_apply.setVisibility(View.VISIBLE);
                }
            }
        });

        rb_gb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    new_language = "en_GB";
                    Log.w(HomePage.Tag, "Langage is now : "+new_language);
                    btn_apply.setVisibility(View.VISIBLE);
                }
            }
        });

        rb_us.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    new_language = "en_US";
                    Log.w(HomePage.Tag, "Langage is now : "+new_language);
                    btn_apply.setVisibility(View.VISIBLE);
                }
            }
        });

        rb_it.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    new_language = "it_IT";
                    Log.w(HomePage.Tag, "Langage is now : "+new_language);
                    btn_apply.setVisibility(View.VISIBLE);
                }
            }
        });

        champion_choose.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String name_champion = s.toString().toLowerCase();
                List<String> filter_champion = new ArrayList<>();
                for(String champion : HomePage.nameOfAllChampions){
                    String champ = champion.replaceAll("[\\s']+", "");
                    if(champ.toLowerCase().contains(name_champion))
                        filter_champion.add(champion);
                }

                ArrayAdapter<String> filteredAdapter = new ArrayAdapter<>(Preferencies.this, android.R.layout.simple_dropdown_item_1line, filter_champion);
                champion_choose.setAdapter(filteredAdapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("language", new_language);
                editor.apply();

                finish();
            }
        });

    }

    public void languageChecked(){
        switch (HomePage.LANGUAGE){
            case "fr_FR" :
                rb_fr.setChecked(true);
                break;
            case "en_GB" :
                rb_gb.setChecked(true);
                break;
            case "en_US" :
                rb_us.setChecked(true);
                break;
            case "it_IT" :
                rb_it.setChecked(true);
                break;
            default :
                Log.w(HomePage.Tag, "Language not detected ... ");
                break;
        }
    }

}