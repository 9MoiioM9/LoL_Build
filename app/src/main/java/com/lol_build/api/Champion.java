package com.lol_build.api;
import java.util.List;

public class Champion {
    private String id;
    private String key;
    private String name;
    private String title;
    private ImageData image;
    private List<SkinData> skins;
    private String lore;
    private List<String> allyTips;
    private List<String> enemyTips;
    private List<String> tags;
    private String partype;
    private InfoData info;
    private StatsData stats;
    private List<SpellData> spells;
    private PassiveData passive;

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public ImageData getImage() {
        return image;
    }

    public List<SkinData> getSkins() {
        return skins;
    }

    public String getLore() {
        return lore;
    }

    public List<String> getAllyTips() {
        return allyTips;
    }

    public List<String> getEnemyTips() {
        return enemyTips;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getPartype() {
        return partype;
    }

    public InfoData getInfo() {
        return info;
    }

    public StatsData getStats() {
        return stats;
    }

    public List<SpellData> getSpells() {
        return spells;
    }

    public PassiveData getPassive() {
        return passive;
    }
}

class ImageData {
    private String full;
    private String sprite;
    private String group;

    public String getFull() {
        return full;
    }

    public String getSprite() {
        return sprite;
    }

    public String getGroup() {
        return group;
    }
}

class SkinData {
    private String id;
    private int num;
    private String name;
    private boolean chromas;

    public String getId() {
        return id;
    }

    public int getNum() {
        return num;
    }

    public String getName() {
        return name;
    }

    public boolean isChromas() {
        return chromas;
    }
}

class InfoData {
    private int attack;
    private int defense;
    private int magic;
    private int difficulty;

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getMagic() {
        return magic;
    }

    public int getDifficulty() {
        return difficulty;
    }
}

class StatsData {
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

    public int getHpperlevel() {
        return hpperlevel;
    }

    public int getMp() {
        return mp;
    }

    public int getMpperlevel() {
        return mpperlevel;
    }

    public int getMovespeed() {
        return movespeed;
    }

    public int getArmor() {
        return armor;
    }

    public double getArmorperlevel() {
        return armorperlevel;
    }

    public int getSpellblock() {
        return spellblock;
    }

    public double getSpellblockperlevel() {
        return spellblockperlevel;
    }

    public int getAttackrange() {
        return attackrange;
    }

    public int getHpregen() {
        return hpregen;
    }

    public int getHpregenperlevel() {
        return hpregenperlevel;
    }

    public int getMpregen() {
        return mpregen;
    }

    public int getMpregenperlevel() {
        return mpregenperlevel;
    }

    public int getCrit() {
        return crit;
    }

    public int getCritperlevel() {
        return critperlevel;
    }

    public int getAttackdamage() {
        return attackdamage;
    }

    public int getAttackdamageperlevel() {
        return attackdamageperlevel;
    }

    public double getAttackspeedperlevel() {
        return attackspeedperlevel;
    }

    public double getAttackspeed() {
        return attackspeed;
    }
}

class SpellData {
    private String id;
    private String name;
    private String description;
    private String tooltip;
    private List<String> leveltip;
    private String cooldownBurn;
    private String costBurn;
    private List<String> effectBurn;
    private String costType;
    private String rangeBurn;
    private ImageData image;
    private String resource;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getTooltip() {
        return tooltip;
    }

    public List<String> getLeveltip() {
        return leveltip;
    }

    public String getCooldownBurn() {
        return cooldownBurn;
    }

    public String getCostBurn() {
        return costBurn;
    }

    public List<String> getEffectBurn() {
        return effectBurn;
    }

    public String getCostType() {
        return costType;
    }

    public String getRangeBurn() {
        return rangeBurn;
    }

    public ImageData getImage() {
        return image;
    }

    public String getResource() {
        return resource;
    }
}

class PassiveData {
    private String name;
    private String description;
    private ImageData image;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ImageData getImage() {
        return image;
    }
}

