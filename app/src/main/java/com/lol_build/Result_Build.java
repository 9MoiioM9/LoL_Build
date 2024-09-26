package com.lol_build;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lol_build.api.Champions;
import com.lol_build.database.MatchupDao;
import com.lol_build.database.MatchupData;

import coil.ImageLoader;
import coil.request.ImageRequest;

public class Result_Build extends AppCompatActivity {

    private ImageView background;
    protected TextView winRate;
    protected ImageView skill1;
    protected ImageView skill2;
    protected ImageView skill3;
    protected ImageView summonerSpell1;
    protected ImageView summonerSpell2;
    protected ImageView rune1;
    protected ImageView rune2;
    protected ImageView rune3;
    protected ImageView rune4;
    protected ImageView rune5;
    protected ImageView rune6;
    protected ImageView item1;
    protected ImageView item2;
    protected ImageView item3;
    protected ImageView item4;
    protected ImageView item5;
    protected ImageView item6;
    private Button back_button;
    private Button save_button;
    private Button share_button;
    public Champions player_champion;
    public Champions enemy_champion;
    private MatchupData matchupData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_build);
        Log.w(HomePage.Tag, "Welcome in the Result_Build");


        Intent intent = getIntent();
        player_champion = (Champions) intent.getSerializableExtra("player_champion");
        enemy_champion = (Champions) intent.getSerializableExtra("enemy_champion");

        matchupData = new MatchupData(Matchup.ROLE, player_champion.getName(), enemy_champion.getName(), ResulBuildRequest.items_rec);

        background = findViewById(R.id.background_img);
        winRate = findViewById(R.id.winRate);

        skill1 = findViewById(R.id.skill_1);
        skill2 = findViewById(R.id.skill_2);
        skill3 = findViewById(R.id.skill_3);
        summonerSpell1 = findViewById(R.id.summs_1);
        summonerSpell2 = findViewById(R.id.summs_2);
        rune1 = findViewById(R.id.rune_1);
        rune2 = findViewById(R.id.sous_rune1);
        rune3 = findViewById(R.id.sous_rune2);
        rune4 = findViewById(R.id.sous_rune3);
        rune5 = findViewById(R.id.sous_rune4);
        rune6 = findViewById(R.id.sous_rune5);
        item1 = findViewById(R.id.item_1);
        item2 = findViewById(R.id.item_2);
        item3 = findViewById(R.id.item_3);
        item4 = findViewById(R.id.item_4);
        item5 = findViewById(R.id.item_5);
        item6 = findViewById(R.id.item_6);
        save_button = findViewById(R.id.save_button);
        back_button = findViewById(R.id.back_btn);
        share_button = findViewById(R.id.btn_share);




        new Thread(() -> {
            if(player_champion != null){
                String url = "https://ddragon.leagueoflegends.com/cdn/img/champion/loading/"+player_champion.getId()+"_0.jpg";

                runOnUiThread(() -> {
                    ImageLoader imageLoader = new ImageLoader.Builder(getApplicationContext()).build();
                    imageLoader.enqueue(new ImageRequest.Builder(getApplicationContext())
                            .data(url)
                            .target(background)
                            .build());

                    background.setAlpha(0.3f);

                });
            }

        }).start();

        String url = "https://u.gg/lol/champions/"+player_champion.getId()+"/build?opp="+enemy_champion.getId()+"&rank=overall";

        ResulBuildRequest resulBuildRequest = new ResulBuildRequest();
        resulBuildRequest.resultBuild = this; // Passer l'instance de ResultBuild à ResulBuildRequest

        // Exécuter la tâche asynchrone
        resulBuildRequest.execute();


        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(() -> {
                    MatchupDao matchupDao = HomePage.database.matchupDao();
                    matchupDao.insert(matchupData);

                    for(MatchupData m : matchupDao.getAll()){
                        Log.w(HomePage.Tag, "Champion p : " + m.getChampionPlayer_name());
                        Log.w(HomePage.Tag, "Champion e : " + m.getChampionEnemy_name());
                        Log.w(HomePage.Tag, "Role  : " + m.getRole());
                        for(String s : m.getItems_rec()){
                            Log.w(HomePage.Tag, "Item  : " + s);
                        }
                    }

                }).start();

                Toast.makeText(getApplicationContext(), "Your Role, Your matchup and your items have been saved !", Toast.LENGTH_SHORT).show();
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        share_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://u.gg/lol/champions/"+player_champion.getId()+"/build?opp="+enemy_champion.getId()+"&rank=overall";
                Intent shareURL = new Intent(Intent.ACTION_SEND);
                shareURL.setType("text/plain");
                shareURL.putExtra(Intent.EXTRA_TEXT, url);
                startActivity(Intent.createChooser(shareURL, "Share the URL of the matchup"));
            }
        });

    }

}