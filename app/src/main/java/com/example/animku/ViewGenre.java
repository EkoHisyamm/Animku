package com.example.animku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.animku.Adapter.SearchAdapter;
import com.example.animku.Adapter.ViewGenreAdapter;
import com.example.animku.Model.AnimeModel;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class ViewGenre extends AppCompatActivity {

    private Realm realm;
    private RecyclerView rvViewGenre;
    private ViewGenreAdapter viewGenreAdapter;
    private ArrayList mAnimeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_genre);

        Toolbar toolbar = findViewById(R.id.toolbar);

        Bundle extras = getIntent().getExtras();
        final String genre = extras.getString("genre");

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(genre);

        rvViewGenre = findViewById(R.id.rvViewGenre);

        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        if (mAnimeList == null)
            mAnimeList = new ArrayList<>();

        RealmResults<AnimeModel> homeModels = realm.where(AnimeModel.class).findAll();
        mAnimeList.addAll(homeModels);

        viewGenreAdapter = new ViewGenreAdapter(mAnimeList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvViewGenre.setLayoutManager(layoutManager);
        rvViewGenre.setAdapter(viewGenreAdapter);

    }
}