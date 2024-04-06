package com.lol_build.api;
import java.util.List;

public class Champion {
    private String version;
    private String id;
    private String key;
    private String name;
    private String title;
    private String blurb;
    private Info info;
    private Image image;
    private List<String> tags;
    private String partype;
    private Stats stats;

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }


    public static class Info {
        private int attack;
        private int defense;
        private int magic;
        private int difficulty;

    }

    public static class Image {
        private String full;
        private String sprite;
        private String group;
        private int x;
        private int y;
        private int w;
        private int h;

    }

    public static class Stats {
        private int hp;
        private int hpperlevel;
        private int mp;
        private int mpperlevel;
        private int movespeed;
        private int armor;
        private double armorperlevel;
        private int spellblock;
        private double spellblockperlevel;
        private int attackrange;
        private int hpregen;
        private int hpregenperlevel;
        private int mpregen;
        private int mpregenperlevel;
        private int crit;
        private int critperlevel;
        private int attackdamage;
        private int attackdamageperlevel;
        private double attackspeedperlevel;
        private double attackspeed;

    }
}

