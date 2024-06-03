package com.lol_build.api;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Item implements Serializable {
    private String id;
    private String name;
    private String description;
    private String plaintext;
    private List<String> into;
    private Image image;
    private Gold gold;
    private List<String> tags;
    private Map<String, Boolean> maps;

    //Getter used
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getDescription() {
        //\n to decorate the description
        description = description.replaceAll("<br>", "\n");
        //Transforme String in version HTML
        description = description.replace("\\u003C", "<").replace("\\u003E", ">");
        description = description.replaceAll("\\<.*?\\>", "");

        return description;
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

    public Map<String, Boolean> getMaps() {
        return maps;
    }

    // Method to check if the item is available on a specific map
    public boolean isAvailableOnMap(String mapId) {
        return maps.getOrDefault(mapId, false);
    }

}

