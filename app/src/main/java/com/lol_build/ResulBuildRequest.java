package com.lol_build;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import coil.ImageLoader;
import coil.request.ImageRequest;

public class ResulBuildRequest extends AsyncTask<Void,Integer,Void> {

    public Result_Build resultBuild;
    private String winRate;
    private List<String> skillsOrders = new ArrayList<>();
    private List<String> skillsOrdersLetters = new ArrayList<>();
    private List<String> runes = new ArrayList<>();
    public static List<String> items_rec = new ArrayList<>();
    private List<String> summonner_spells = new ArrayList<>();

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            String url = "https://u.gg/lol/champions/"
                    +resultBuild.player_champion.getId()
                    +"/build?opp="
                    +resultBuild.enemy_champion.getId()
                    +"&rank=overall";

            Document document = Jsoup.connect(url).get();

            Element winrate_Info = document.selectFirst("div.win-rate");
            if (winrate_Info != null) {
                winRate = winrate_Info.selectFirst(".value").text();
            } else {
                Log.w(HomePage.Tag, "Win Rate not found");
            }

            //Methode to load every elements used
            loadSkillsOrder();
            loadRune();
            loadItemsFromWeb();
            loadSummonnerSpells();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid){
        super.onPostExecute(aVoid);

        resultBuild.winRate.setText(winRate);

        String ChampionSpell_URL = "https://ddragon.leagueoflegends.com/cdn/"+HomePage.VERSION+"/img/spell/";
        String item_URL = "https://ddragon.leagueoflegends.com/cdn/"+HomePage.VERSION+"/img/item/";
        String summonerSpells_URL = "https://ddragon.leagueoflegends.com/cdn/"+HomePage.VERSION+"/img/spell/";

        Log.d(HomePage.Tag, "Loading All Images");
        Log.d(HomePage.Tag, "Skill 1 / Skill2 / Skill3");
        loadImg(ChampionSpell_URL+ skillsOrders.get(0) +".png", resultBuild.skill1);
        loadImg(ChampionSpell_URL+skillsOrders.get(1)+".png", resultBuild.skill2);
        loadImg(ChampionSpell_URL+skillsOrders.get(2)+".png", resultBuild.skill3);
        Log.d(HomePage.Tag, "Rune ");
        loadImg(runes.get(0), resultBuild.rune1);
        loadImg(runes.get(1), resultBuild.rune2);
        loadImg(runes.get(2), resultBuild.rune3);
        loadImg(runes.get(3), resultBuild.rune4);
        loadImg(runes.get(4), resultBuild.rune5);
        loadImg(runes.get(5), resultBuild.rune6);
        Log.d(HomePage.Tag, "Items ...");
        loadImg(item_URL+items_rec.get(0)+".png", resultBuild.item1);
        loadImg(item_URL+items_rec.get(1)+".png", resultBuild.item2);
        loadImg(item_URL+items_rec.get(2)+".png", resultBuild.item3);
        loadImg(item_URL+items_rec.get(3)+".png", resultBuild.item4);
        loadImg(item_URL+items_rec.get(4)+".png", resultBuild.item5);
        loadImg(item_URL+items_rec.get(5)+".png", resultBuild.item6);
        Log.d(HomePage.Tag, "Summoner Spells");

        loadImg(summonerSpells_URL+summonner_spells.get(0), resultBuild.summonerSpell1);
        loadImg(summonerSpells_URL+summonner_spells.get(1), resultBuild.summonerSpell2);

        Log.d(HomePage.Tag, "Fin Loading Images");

    }

    public void loadImg(String imgURL ,ImageView imgTarget){
        ImageLoader imageLoader = new ImageLoader.Builder(resultBuild.getApplicationContext()).build();
        imageLoader.enqueue(new ImageRequest.Builder(resultBuild.getApplicationContext())
                .placeholder(R.drawable.lol_loading)
                .error(R.drawable.ic_launcher_foreground)
                .data(imgURL)
                .target(imgTarget)
                .build());
    }

    public void loadSkillsOrder(){
        try {
            String url = "https://u.gg/lol/champions/"
                    +resultBuild.player_champion.getId()
                    +"/build?opp="
                    +resultBuild.enemy_champion.getId()
                    +"&rank=overall";

            Log.w(HomePage.Tag, url);

            Document document = Jsoup.connect(url).get();

            Log.w(HomePage.Tag, "Element skill orders ");
            Element skillOrders_info = document.selectFirst("div.skill-priority_content");
            Elements skillsInfos = skillOrders_info.select("div.skill-priority-path");;

            for (Element skill : skillsInfos) {
                Elements skills_label = skill.select("div.champion-skill-with-label");
                for (Element infoSpell : skills_label) {
                    Elements skillLabel = infoSpell.select("div img");

                    //Traitement of the src url to take only the name of the skill
                    String txt_spell = skillLabel.attr("src");
                    int indexSkills = txt_spell.lastIndexOf('/');
                    String indexID = txt_spell.substring(indexSkills + 1);
                    String skills = indexID.substring(0, indexID.lastIndexOf('.'));
                    skillsOrders.add(skills);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadRune(){
        try {
            String url = "https://u.gg/lol/champions/"
                    +resultBuild.player_champion.getId()
                    +"/build?opp="
                    +resultBuild.enemy_champion.getId()
                    +"&rank=overall";

            Document document = Jsoup.connect(url).get();

            Log.w(HomePage.Tag, "Rune :  ");

            Log.w(HomePage.Tag, "Primary Runes : ");
            //Primary Runes
            Elements div_PrimaryRune = document.select("div.rune-tree_v2.primary-tree");
            //Take the first elements to take only the 4 runes needed because in the HTML page there are two Element with the same id
            Elements img_elements = div_PrimaryRune.get(0).select("div.perk-row div.perks div.perk.perk-active img");
            for (Element img_element : img_elements) {
                String src = img_element.attr("src");

                runes.add(src);
            }
            Log.w(HomePage.Tag, "Secondary Runes : ");
            //Secondary Runes
            Elements div_SecondaryRune = document.select("div.secondary-tree div.rune-tree_v2");
            //Take the first elements to take only the 4 runes needed because in the HTML page there are two Element with the same id
            img_elements = div_SecondaryRune.get(0).select("div.perk-row div.perks div.perk.perk-active img");
            for (Element img_element : img_elements) {
                String src = img_element.attr("src");

                runes.add(src);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadItemsFromWeb(){
        try {
            items_rec.clear();
            String url = "https://mobalytics.gg/lol/champions/"
                    +resultBuild.player_champion.getId()
                    +"/build?rank=All";

            Document document = Jsoup.connect(url).get();
            Log.w(HomePage.Tag, "URL : " + url);

            Log.w(HomePage.Tag, "Items Orders ");
            Element div_ItemsUsed = document.selectFirst("div.m-1s2625c div.m-cvbsy5:nth-child(2) div.m-owe8v3:nth-child(2) div.m-l9l2ov div.m-yare96:nth-child(4)");

            if(div_ItemsUsed != null) {
                Elements div_ListItems = div_ItemsUsed.select("div.m-yhe5ws");
                for (Element itemInfo : div_ListItems) {
                    List<Elements> list_items = new ArrayList<>();
                    list_items.add(div_ItemsUsed.select("div.m-rfjm2i"));
                    list_items.add(div_ItemsUsed.select("div.m-juatvp"));
                    list_items.add(div_ItemsUsed.select("div.m-3ygoqp"));
                    list_items.add(div_ItemsUsed.select("div.m-1rjh3a6"));
                    list_items.add(div_ItemsUsed.select("div.m-1egdgno"));
                    list_items.add(div_ItemsUsed.select("div.m-8x1gh3"));

                    for(Elements it : list_items){
                        Elements item = it.select("div img");
                        String itemURL = item.attr("src");

                        int indexItem = itemURL.lastIndexOf('/');
                        String indexID = itemURL.substring(indexItem + 1);
                        String itemID = indexID.substring(0, indexID.lastIndexOf('.'));

                        items_rec.add(itemID);
                    }

                }
            }else
                Log.w(HomePage.Tag, "ELEMENT NULL ");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadSummonnerSpells(){
        try{
            String url = "https://u.gg/lol/champions/"
                    +resultBuild.player_champion.getId()
                    +"/build?opp="
                    +resultBuild.enemy_champion.getId()
                    +"&rank=overall";

            Document document = Jsoup.connect(url).get();
            Log.w(HomePage.Tag, "Management of Summoner Spells");

            Element div_entete_summs = document.selectFirst("div.content-section_content.summoner-spells");
            if (div_entete_summs != null) {
                Elements div_summs = div_entete_summs.select("div.flex img");
                for(Element summs_info : div_summs){

                    //Elements img_element = summs_info.select("div img");
                    String summs_src = summs_info.attr("src");
                    int indexSumms = summs_src.lastIndexOf('/');
                    String indexID = summs_src.substring(indexSumms + 1);
                    String summs = indexID.substring(0, indexID.lastIndexOf('.'));
                    summonner_spells.add(summs+".png");

                }
            } else {
                Log.w(HomePage.Tag, "Div Summs not found");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
