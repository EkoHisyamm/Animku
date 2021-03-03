package com.example.animku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.animku.Adapter.ViewTerbaruAdapter;
import com.example.animku.Model.AnimeModel;
import com.example.animku.Model.TerbaruModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class ViewAllTerbaru extends AppCompatActivity {

    private Realm realm;
    private List<AnimeModel> mAnimeList;
    private ArrayList<TerbaruModel> terbaruModels;
    RecyclerView rvViewAll;
    ViewTerbaruAdapter viewTerbaruAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_terbaru);

        Toolbar toolbar = findViewById(R.id.toolbar);
        rvViewAll = findViewById(R.id.rvViewAll);

        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        if (mAnimeList == null)
            mAnimeList = new ArrayList<>();

        RealmResults<AnimeModel> homeModels = realm.where(AnimeModel.class).findAll();
        mAnimeList.addAll(homeModels);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Anime Terbaru");

        terbaruModels = new ArrayList<>();
        for (int i = 0; i < mAnimeList.size(); i++) {
            if (mAnimeList.get(i).getStatus().equals("Ongoing") || mAnimeList.get(i).getStatus().equals("Finished Airing")){
                terbaruModels.add(new TerbaruModel(mAnimeList.get(i).getId(), String.valueOf(i)));
            }
        }

        viewTerbaruAdapter = new ViewTerbaruAdapter(this, mAnimeList, terbaruModels);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rvViewAll.setLayoutManager(gridLayoutManager);
        rvViewAll.setAdapter(viewTerbaruAdapter);


    }
}