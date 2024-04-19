package com.lol_build.api;
import java.io.Serializable;
import java.util.List;

public class Champions implements Serializable {
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
    public Stats getStats() {
        return stats;
    }
    public String getTitle() {
        return title;
    }
    public String getPartype(){
        return partype;
    }
    public String getFullFromImage(){
        return image.getFull();
    }


    public static class Info implements Serializable{
        private int attack;
        private int defense;
        private int magic;
        private int difficulty;

    }

    public static class Image implements Serializable{
        private String full;
        private String sprite;
        private String group;
        private int x;
        private int y;
        private int w;
        private int h;

        public String getFull(){
            return full;
        }

    }

    public static class Stats implements Serializable{
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

        public int getHp() {
            return hp;
        }

        public int getMp() {
            return mp;
        }

        public int getMovespeed() {
            return movespeed;
        }

        public int getArmor() {
            return armor;
        }

        public int getSpellblock() {
            return spellblock;
        }

        public double getAttackspeed() {
            return attackspeed;
        }

        public int getAttackdamage() {
            return attackdamage;
        }
    }
}

