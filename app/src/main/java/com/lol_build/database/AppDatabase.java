package com.lol_build.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {MatchupData.class}, version = 1)
@TypeConverters({MatchupData.class})
public abstract class AppDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "LoLBuild.db";
    public abstract MatchupDao matchupDao();

}
