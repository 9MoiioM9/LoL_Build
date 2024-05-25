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
import android.widget.Spinner;
import android.widget.Toast;

import com.lol_build.HomePage;
import com.lol_build.R;
import com.lol_build.Result_Item_Fragment;
import com.lol_build.api.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Info_item extends AppCompatActivity {

    public Spinner spinner_items;
    public SwitchCompat switch_items;
    private Button btn_back;
    private Button btn_searchItem;
    private boolean switch_isActived = false;
    private AutoCompleteTextView searchItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_item);

        Log.w(HomePage.Tag, "Welcome in the Info_Item");

        spinner_items = findViewById(R.id.info_item_spinner);
        switch_items = findViewById(R.id.switch_info_item);
        btn_back = findViewById(R.id.info_item_back);
        searchItem = findViewById(R.id.AC_search_item);
        btn_searchItem = findViewById(R.id.btn_searchItem);

        changeListItem();

        spinner_items.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(switch_isActived){
                    searchItem.setText(HomePage.items.get(position).getName());
                }else searchItem.setText(getItemFromName(spinner_items.getSelectedItem().toString()).getName());

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
                List<String> listItems;
                if(switch_isActived)
                    listItems = HomePage.nameOfAllItems;
                else
                    listItems = HomePage.nameOfAllItemsPurchasable;


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
                Item item_searched = getItemFromName(searchItem.getText().toString());

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


    public static Item getItemFromName(String name){
        Item res = null;
        for(Item item : HomePage.items){
            if(Objects.equals(item.getName(), name)){
                return item;
            }
            res = item;
        }
        return res;
    }

}