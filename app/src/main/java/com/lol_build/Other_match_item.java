package com.lol_build;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Other_match_item {

    private ImageView icon_champion;
    private ListView runes;
    private ImageView summoner1;
    private ImageView summoner2;
    private TextView gameMode;
    private TextView dateGame;
    private TextView timeGame;
    private TextView kda;
    private ListView items;
    private ListView MatchMacking;
    private String test;

    Other_match_item(String test){
        this.test = test;
    }

    public String getTest(){return test;}


}