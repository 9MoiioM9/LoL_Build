package com.lol_build.infos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.lol_build.HomePage;
import com.lol_build.R;
import com.lol_build.Result_Item_Fragment;
import com.lol_build.api.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Info_item extends AppCompatActivity {

    public Spinner spinner_items;
    private Button btn_searchItem;
    //private boolean switch_isActived = false;
    //public SwitchCompat switch_items;
    private Button btn_back;
    private AutoCompleteTextView searchItem;
    private RadioGroup rg_specialMap;
    private RadioButton rb_all;
    private RadioButton rb_map11;
    private RadioButton rb_map12;
    private RadioButton rb_map21;
    private RadioButton rb_map22;
    private RadioButton rb_map30;
    private RadioButton rb_special_map;
    private List<Item> items_by_map = HomePage.items;
    private List<String> name_items_by_map = HomePage.nameOfAllItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_item);

        Log.w(HomePage.Tag, "Welcome in the Info_Item");


        spinner_items = findViewById(R.id.info_item_spinner);
        //switch_items = findViewById(R.id.switch_info_item);
        btn_back = findViewById(R.id.info_item_back);
        searchItem = findViewById(R.id.AC_search_item);
        btn_searchItem = findViewById(R.id.btn_searchItem);
        rg_specialMap = findViewById(R.id.rg_mode_map);
        rb_all = findViewById(R.id.rb_all_Items);
        rb_map11 = findViewById(R.id.rb_map11);
        rb_map12 = findViewById(R.id.rb_map12);
        rb_map21 = findViewById(R.id.rb_map21);
        rb_map22 = findViewById(R.id.rb_map22);
        rb_map30 = findViewById(R.id.rb_map30);
        rb_special_map = findViewById(R.id.rb_modeMap);

        changeListItem(name_items_by_map);

        rb_all.setChecked(true);

        rb_map11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    items_by_map = filterItemsByMap(HomePage.items, "11");
                    name_items_by_map = getNamesFromItemsList(items_by_map);
                    Log.d(HomePage.Tag, "Size of items_by_Map  : "+items_by_map.size());
                    changeListItem(name_items_by_map);
                }
            }
        });

        rb_map12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    items_by_map = filterItemsByMap(HomePage.items, "12");
                    name_items_by_map = getNamesFromItemsList(items_by_map);

                    Log.d(HomePage.Tag, "Size of items_by_Map  : "+items_by_map.size());
                    changeListItem(name_items_by_map);
                }
            }
        });

        rb_special_map.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    rg_specialMap.setVisibility(View.VISIBLE);
                }else rg_specialMap.setVisibility(View.INVISIBLE);
            }
        });

        rb_map21.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    items_by_map = filterItemsByMap(HomePage.items, "21");
                    name_items_by_map = getNamesFromItemsList(items_by_map);

                    Log.d(HomePage.Tag, "Size of items_by_Map  : "+items_by_map.size());
                    changeListItem(name_items_by_map);
                }
            }
        });

        rb_map22.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    items_by_map = filterItemsByMap(HomePage.items, "22");
                    name_items_by_map = getNamesFromItemsList(items_by_map);

                    Log.d(HomePage.Tag, "Size of items_by_Map  : "+items_by_map.size());
                    changeListItem(name_items_by_map);
                }
            }
        });

        rb_map30.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    items_by_map = filterItemsByMap(HomePage.items, "30");
                    name_items_by_map = getNamesFromItemsList(items_by_map);
                    Log.d(HomePage.Tag, "Size of items_by_Map  : "+items_by_map.size());
                    changeListItem(name_items_by_map);
                }

            }
        });

        spinner_items.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                searchItem.setText(items_by_map.get(position).getName());
                /*
                if(switch_isActived){
                    searchItem.setText(items_by_map.get(position).getName());
                }else searchItem.setText(getItemFromName(spinner_items.getSelectedItem().toString(), items_by_map).getName());
                 */
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
/*
        switch_items.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch_isActived = isChecked;
                changeListItem(items_by_map);
            }
        });

 */

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        searchItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String name_item_searched = s.toString().toLowerCase();
                List<String> filter_item = new ArrayList<>();
                List<String> listItems = name_items_by_map;


                for(String item : listItems){
                    String item_searched = item.replaceAll("[\\s']+", "");
                    if(item_searched.toLowerCase().contains(name_item_searched))
                        filter_item.add(item);
                }

                ArrayAdapter<String> filteredAdapter = new ArrayAdapter<>(Info_item.this, android.R.layout.simple_dropdown_item_1line, filter_item);
                searchItem.setAdapter(filteredAdapter);
            }
        });

        btn_searchItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item_searched = getItemFromName(searchItem.getText().toString(), items_by_map);

                if (savedInstanceState == null) {
                    if (item_searched != null) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("item_searched", item_searched);


                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.fragmentContainer_Result_Item, Result_Item_Fragment.class, bundle)
                                .commit();
                    }else
                        Toast.makeText(getApplicationContext(), "Item searched doesn't exist !", Toast.LENGTH_SHORT).show();

                }else Log.w(HomePage.Tag, "saveInstanceState is null, Fragment cannot be load");

            }
        });

    }

    private void changeListItem(List<String> items){
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(Info_item.this, android.R.layout.simple_spinner_item, items);
        /*
        if(switch_isActived){
            adapter = new ArrayAdapter<>(Info_item.this, android.R.layout.simple_spinner_item, items);
        }else {
            adapter = new ArrayAdapter<>(Info_item.this, android.R.layout.simple_spinner_item, );
        }

         */
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_items.setAdapter(adapter);
    }


    public static Item getItemFromName(String name, List<Item> items){
        Item res = null;
        for(Item item : items){
            if(Objects.equals(item.getName(), name)){
                return item;
            }
            res = item;
        }
        return null;
    }

    //Static method to filter items based on their availability on a specific map
    public static List<Item> filterItemsByMap(List<Item> items, String mapId) {
        Log.w(HomePage.Tag, "Size of items : " + items.size());
        return items.stream()
                .filter(item -> item.isAvailableOnMap(mapId))
                .collect(Collectors.toList());
    }

    public static List<String> getNamesFromItemsList(List<Item> items) {
        List<String> names_of_items = new ArrayList<>();

        for(Item i : items)
            names_of_items.add(i.getName());

        return names_of_items;
    }

}