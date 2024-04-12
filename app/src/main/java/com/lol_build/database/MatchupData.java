package com.lol_build.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

@Entity(tableName = "matchup_data")
public class MatchupData {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String role;
    private String championPlayer_name;
    private String championEnemy_name;
    private List<String> items_rec;

    public MatchupData() {
    }

    public MatchupData(String role, String championPlayer, String championEnemy,List<String> items_rec) {
        this.role = role;
        this.championPlayer_name = championPlayer;
        this.championEnemy_name = championEnemy;
        this.items_rec = items_rec;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getChampionPlayer_name() {
        return championPlayer_name;
    }

    public void setChampionPlayer_name(String championPlayer_name) {
        this.championPlayer_name = championPlayer_name;
    }

    public String getChampionEnemy_name() {
        return championEnemy_name;
    }

    public void setChampionEnemy_name(String championEnemy_name) {
        this.championEnemy_name = championEnemy_name;
    }
    public List<String> getItems_rec() {
        return items_rec;
    }

    public void setItems_rec(List<String> items_rec) {
        this.items_rec = items_rec;
    }

    @TypeConverter
    public static List<String> fromString(String value) {
        Type listType = new TypeToken<List<String>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromList(List<String> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}

