package com.lol_build.api;

import java.util.List;
import java.util.Map;

public class Item {
    private String id;
    private String name;
    private String description;
    private String plaintext;
    private List<String> into;
    private Image image;
    private Gold gold;
    private List<String> tags;

    //Getter used
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPlaintext() {
        return plaintext;
    }
    public List<String> getInto() {
        return into;
    }
    public List<String> getTags() {
        return tags;
    }

    public Gold getGold() {
        return gold;
    }
    public int getTotalFromGold(){
        return gold.getTotal();
    }
    public int getSellFromGold(){
        return gold.getSell();
    }

    public Image getImage(){
        return image;
    }
    public String getFullFromImage(){
        return image.getFull();
    }

    //Class Image
    public static class Image {
        private String full;

        public String getFull(){
            return full;
        }
    }

    //Class Gold
    public static class Gold {
        private int base;
        private boolean purchasable;
        private int total;
        private int sell;

        public int getTotal() {
            return total;
        }
        public int getSell(){
            return sell;
        }

        public boolean isPurchasable() {
            return purchasable;
        }
    }
}

