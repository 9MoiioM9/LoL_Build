package com.lol_build;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Info_item extends AppCompatActivity {

    public EditText search_item;

    public TextView nameItem;
    public TextView descItem;
    public Button launch_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_item);

        search_item = findViewById(R.id.search_item);
        nameItem = findViewById(R.id.name_item);
        descItem = findViewById(R.id.desc_item);
        launch_search = findViewById(R.id.search);

        //Item item = getItem(search_item.getText().toString());

/*
        launch_search.setOnClickListener((v) -> {
            new Thread(() -> {

                Item item = getItem(search_item.getText().toString());
                String n = item.getName();
                String desc = item.getDescription();
                Log.w(HomePage.Tag, n);
                Log.w(HomePage.Tag, "" + desc);

                runOnUiThread(()->{
                    nameItem.setText(n);
                    descItem.setText(desc);
                });
            }).start();
        });


    }

    public Item getItem(String name){
        return Orianna.itemNamed(name).get();
    }

    
*/}

}