package com.lol_build.infos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lol_build.HomePage;
import com.lol_build.R;
import com.lol_build.api.Player;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Info_Player extends AppCompatActivity {
    public final static String RIOT_API_KEY = "RGAPI-43b70175-c1d2-422a-bb32-6f74542043b5";

    private EditText username;
    private EditText tag;
    private WebView webView;
    private Button btn_search;
    private Button btn_back;
    private Button btn_match_history;
    private Player player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_player);

        Log.w(HomePage.Tag, "Welcome in the Info_Player");

        username = findViewById(R.id.player_name);
        tag = findViewById(R.id.tag_player);
        webView = findViewById(R.id.info_player_web);
        btn_search = findViewById(R.id.info_player_search);
        btn_match_history = findViewById(R.id.info_player_matchs);
        btn_back = findViewById(R.id.info_player_back);
        btn_match_history.setEnabled(false);
        btn_search.setEnabled(false);

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                updateClickableSearch();
            }
        });

        tag.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                updateClickableSearch();
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText() != null && tag.getText() != null) {
                    new Thread(() -> {
                        String jsonData;
                        OkHttpClient client = new OkHttpClient();
                        Request request_version = new Request.Builder()
                                .url("https://europe.api.riotgames.com/riot/account/v1/accounts/by-riot-id/"
                                        + username.getText().toString() + "/"
                                        + tag.getText().toString() + "?api_key="
                                        + RIOT_API_KEY)
                                .build();

                        Log.w(HomePage.Tag, "URL : "+ request_version.url());
                        try (Response response = client.newCall(request_version).execute()) {
                            if (response.isSuccessful()) {
                                jsonData = response.body().string();
                                JsonObject playerObject = JsonParser.parseString(jsonData).getAsJsonObject();
                                Gson gson = new Gson();

                                Log.w(HomePage.Tag, "Get the Player's informations");

                                player = gson.fromJson(playerObject, Player.class);

                                Log.d(HomePage.Tag, "Informations player : " + "\n" + player.getPuuid() + "\n" + player.getGameName() + "\n" + player.getTagLine());

                            } else {
                                throw new IOException("Failed to fetch the Player's Informations: " + response.code());
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        runOnUiThread(()-> {
                            webView.loadUrl("https://mobalytics.gg/lol/profile/euw/"+player.getGameName()+"-"+player.getTagLine()+"/overview");
                            webView.setVisibility(View.VISIBLE);
                            //WebSettings webSettings = webView.getSettings();
                            //webSettings.setJavaScriptEnabled(true);
                        });

                    }).start();
                }else Log.w(HomePage.Tag, "Empty ... ");
            }
        });

        btn_match_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Info_Player.this, Matchs_History.class);
                intent.putExtra("player_name", player.getGameName());
                startActivity(intent);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void updateClickableSearch(){
        String username_player = username.getText().toString().trim();
        String tag_player = tag.getText().toString().trim();

        if(username_player != null && tag_player != null){
            btn_search.setEnabled(true);
            Log.w(HomePage.Tag, "Search is clickable !");
        }else {
            btn_search.setEnabled(false);
            Log.w(HomePage.Tag, "Search is not clickable !");
        }
    }
}