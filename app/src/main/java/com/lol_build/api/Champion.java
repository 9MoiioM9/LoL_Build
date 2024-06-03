package com.lol_build.api;

import java.util.List;

public class Champion {
    private String id;
    private String key;
    private String name;
    private String title;
    private Image image;
    private List<Skin> skins;
    private String lore;
    private String blurb;
    private List<String> allytips;
    private List<String> enemytips;
    private List<String> tags;
    private String partype;
    private Info info;
    private Stats stats;
    private List<Spell> spells;
    private Passive passive;

    // Inner classes to represent nested objects
    public static class Image {
        private String full;
        private String group;
    }

    public static class Skin {
        private String id;
        private int num;
        private String name;
        private boolean chromas;
    }

    public static class Info {
        private int attack;
        private int defense;
        private int magic;
        private int difficulty;
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
        private double hpregen;
        private double hpregenperlevel;
        private int mpregen;
        private int mpregenperlevel;
        private int crit;
        private int critperlevel;
        private int attackdamage;
        private int attackdamageperlevel;
        private double attackspeedperlevel;
        private double attackspeed;
    }

    public static class Spell {
        private String id;
        private String name;
        private String description;
        private String tooltip;
        private LevelTip leveltip;
        private int maxrank;
        private List<Integer> cooldown;
        private String cooldownBurn;
        private List<Integer> cost;
        private String costBurn;
        private List<List<Integer>> effect;
        private List<String> effectBurn;
        private String costType;
        private int maxammo;
        private List<Integer> range;
        private String rangeBurn;
        private SpellImage image;
        private String resource;
    }

    public static class LevelTip {
        private List<String> label;
        private List<String> effect;
    }

    public static class SpellImage {
        private String full;
        private String group;
    }

    public static class Passive {
        private String name;
        private String description;
        private PassiveImage image;
    }

    public static class PassiveImage {
        private String full;
        private String group;

    }
}
