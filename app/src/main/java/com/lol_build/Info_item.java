package com.lol_build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.lol_build.api.Item;

import java.util.ArrayList;
import java.util.List;

import coil.ImageLoader;
import coil.request.ImageRequest;
import okhttp3.OkHttpClient;

public class Info_item extends AppCompatActivity {

    public Spinner spinner_items;
    public ImageView icon_Item;
    public TextView nameItem;
    public TextView descItem;
    public TextView tagsItem;
    public TextView itemGold;
    public TextView itemSell;
    public SwitchCompat switch_items;
    private boolean switch_isActived = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_item);

        Log.w(HomePage.Tag, "Welcome in the Info_Item");

        spinner_items = findViewById(R.id.info_item_spinner);
        icon_Item = findViewById(R.id.icon_item);
        nameItem = findViewById(R.id.item_name);
        descItem = findViewById(R.id.desc);
        tagsItem = findViewById(R.id.tags);
        itemGold = findViewById(R.id.gold);
        itemSell = findViewById(R.id.sell);
        switch_items = findViewById(R.id.switch_info_item);

        changeListItem();

        spinner_items.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(switch_isActived){
                    loadItem(HomePage.items.get(position));
                }else loadItem(getItemFromName(spinner_items.getSelectedItem().toString()));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        switch_items.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch_isActived = isChecked;
                changeListItem();
            }
        });


    }

    private void changeListItem(){
        ArrayAdapter<String> adapter;
        if(switch_isActived){
            adapter = new ArrayAdapter<>(Info_item.this, android.R.layout.simple_spinner_item, HomePage.nameOfAllItems);
        }else {
            adapter = new ArrayAdapter<>(Info_item.this, android.R.layout.simple_spinner_item, HomePage.nameOfAllItemsPurchasable);
        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_items.setAdapter(adapter);
    }

    public void loadItem(Item item){
        new Thread(() -> {
            String jsonData;
            OkHttpClient client = new OkHttpClient();

            String url = "https://ddragon.leagueoflegends.com/cdn/"+HomePage.VERSION+"/img/item/"+item.getFullFromImage();

            runOnUiThread(() -> {
                ImageLoader imageLoader = new ImageLoader.Builder(getApplicationContext()).build();
                imageLoader.enqueue(new ImageRequest.Builder(getApplicationContext())
                        .data(url)
                        .target(icon_Item)
                        .build());

                nameItem.setText(item.getName());
                if(item.getPlaintext() != null)
                    descItem.setText(item.getPlaintext());

                tagsItem.setText(getString(item.getTags()));
                itemGold.setText(String.valueOf(item.getTotalFromGold()));
                itemSell.setText(String.valueOf(item.getSellFromGold()));

            });
        }).start();
    }

    private String getString(List<String> list){
        StringBuilder res = new StringBuilder();
        if(list != null){
            for(int i=0; i<list.size(); ++i){
                if(i != 0){
                    res.append(", ");
                }
                res.append(list.get(i));
            }
            return res.toString();
        }else return "None";

    }

    private Item getItemFromName(String name){
        Item res = null;
        for(Item item : HomePage.items){
            if(item.getName() == name){
                return item;
            }
            res = item;
        }
        return res;
    }

}