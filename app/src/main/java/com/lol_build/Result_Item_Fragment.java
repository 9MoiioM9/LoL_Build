package com.lol_build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lol_build.api.Item;
import com.lol_build.infos.Info_item;

import coil.ImageLoader;
import coil.request.ImageRequest;

public class Result_Item_Fragment extends Fragment {

    private ImageView icon_Item;
    private TextView nameItem;
    private TextView descItem;
    private TextView tagsItem;
    private TextView itemGold;
    private TextView itemSell;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result_item_fragment, container, false);

        icon_Item = view.findViewById(R.id.icon_info_item);
        nameItem = view.findViewById(R.id.NameItem_Fragment);
        descItem = view.findViewById(R.id.desc);
        tagsItem = view.findViewById(R.id.tags);
        itemGold = view.findViewById(R.id.gold);
        itemSell = view.findViewById(R.id.sell);

        Item item = (Item) requireArguments().getSerializable("item_searched");

        String url = "https://ddragon.leagueoflegends.com/cdn/"+HomePage.VERSION+"/img/item/"+item.getFullFromImage();

        ImageLoader imageLoader = new ImageLoader.Builder(view.getContext()).build();
        imageLoader.enqueue(new ImageRequest.Builder(view.getContext())
                .data(url)
                .target(icon_Item)
                .build());
        nameItem.setText(item.getName());
        descItem.setText(item.getDescription());


        if(item.getTags() !=null) {
            StringBuilder tagsText = new StringBuilder();
            for (String tag : item.getTags()) {
                tagsText.append(tag).append(", ");
            }
            tagsText.replace(tagsText.lastIndexOf(","), tagsText.length(), "");
            tagsItem.setText(tagsText.toString());
        }

        itemGold.setText(String.valueOf(item.getTotalFromGold()));
        itemSell.setText(String.valueOf(item.getSellFromGold()));

        return view;
    }
}