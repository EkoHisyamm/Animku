package com.example.animku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.animku.Adapter.AnimeTerbaruAdapter;
import com.example.animku.Adapter.AnimeTerpopulerAdapter;
import com.example.animku.Adapter.SearchAdapter;
import com.example.animku.Model.AnimeModel;
import com.example.animku.R;

import java.util.ArrayList;
import java.util.Timer;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class SearchAnime extends AppCompatActivity {

    private Realm realm;
    private RecyclerView rvListSearch;
    private SearchAdapter searchAdapter;
    private ArrayList mAnimeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_anime);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" ");

        rvListSearch = findViewById(R.id.rvListSearch);

        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        if (mAnimeList == null)
            mAnimeList = new ArrayList<>();

        RealmResults<AnimeModel> homeModels = realm.where(AnimeModel.class).findAll();
        mAnimeList.addAll(homeModels);

        searchAdapter = new SearchAdapter(mAnimeList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvListSearch.setLayoutManager(layoutManager);
        rvListSearch.setAdapter(searchAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.mSearchMenu);
        final SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAnimeList.clear();
                RealmResults<AnimeModel> produkModel = realm.where(AnimeModel.class).contains("judul",query, Case.INSENSITIVE).findAll();
                mAnimeList.addAll(produkModel);
                searchAdapter.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    mAnimeList.clear();
                    RealmResults<AnimeModel> produkModel = realm.where(AnimeModel.class).findAll();
                    mAnimeList.addAll(produkModel);
                    searchAdapter.notifyDataSetChanged();
                    return true;
                }
                mAnimeList.clear();
                RealmResults<AnimeModel> produkModel = realm.where(AnimeModel.class).contains("judul",newText, Case.INSENSITIVE).findAll();
                mAnimeList.addAll(produkModel);
                searchAdapter.notifyDataSetChanged();
                return false;
            }
        });
        return true;
    }
}