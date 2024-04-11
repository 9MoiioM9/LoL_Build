package com.lol_build.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MatchupDao {
    @Insert
    void insertAll(MatchupData... matchupData);
    @Insert
    void insert(MatchupData matchupData);

    @Delete
    void delete(MatchupData matchupData);

    @Query("SELECT * FROM matchup_data")
    List<MatchupData> getAll();


}
