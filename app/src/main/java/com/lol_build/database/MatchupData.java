package com.lol_build.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "matchup_data")
public class MatchupData {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String role;
    private String championPlayer_name;
    private String championEnemy_name;

    public MatchupData() {
    }

    public MatchupData(String role, String championPlayer, String championEnemy) {
        this.role = role;
        this.championPlayer_name = championPlayer;
        this.championEnemy_name = championEnemy;
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
}

