package com.example.animku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.animku.Adapter.EpisodeAdapter;
import com.example.animku.Model.AnimeModel;
import com.example.animku.Model.EpisodeModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class EpisodeAnime extends AppCompatActivity {

    private RecyclerView rvListEpisode;
    private Realm realm;
    private List<AnimeModel> mAnimeList;
    private EpisodeAdapter episodeAdapter;
    private ArrayList<EpisodeModel> listEpisodes;
    TextView tvJudul, tvInfo, tvSinopsis;
    ImageView ivFoto, ivBackArrow;
    ImageButton ibBookmark;
    boolean bookmark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode_anime);

        rvListEpisode = findViewById(R.id.rvListEpisode);
        tvJudul = findViewById(R.id.tvJudul);
        tvInfo = findViewById(R.id.tvInfo);
        tvSinopsis = findViewById(R.id.tvSinopsis);
        ivFoto = findViewById(R.id.ivFoto);
        ivBackArrow = findViewById(R.id.ivBackArrow);
        ibBookmark = findViewById(R.id.ibBookmark);

        ibBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekBookmark(!bookmark);
            }
        });

        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        if (mAnimeList == null)
            mAnimeList = new ArrayList<>();

        RealmResults<AnimeModel> homeModels = realm.where(AnimeModel.class).findAll();
        mAnimeList.addAll(homeModels);

        Bundle extras = getIntent().getExtras();
        final int position = extras.getInt("position");

        Log.e("TAG", "onCreate: " + mAnimeList.get(position).getGambar());
        tvJudul.setText(mAnimeList.get(position).getJudul());
        tvSinopsis.setText(mAnimeList.get(position).getSinopsis());
        Glide.with(this).load(mAnimeList.get(position).getGambar()).into(ivFoto);

        listEpisodes = new ArrayList<>();
        for (int i = 1; i <= Integer.parseInt(mAnimeList.get(position).getJmlepisode()); i++) {
            listEpisodes.add(new EpisodeModel(String.valueOf(i), mAnimeList.get(position).getJudul() + " eps " + i, mAnimeList.get(position).getJudul(), position));
        }

        episodeAdapter = new EpisodeAdapter(listEpisodes);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvListEpisode.setLayoutManager(layoutManager);
        rvListEpisode.setAdapter(episodeAdapter);

        ivBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EpisodeAnime.this, InfoAnime.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

    }

    private void cekBookmark(boolean active){
        bookmark = active;
        if(active){
            ibBookmark.setColorFilter(-256, PorterDuff.Mode.SRC_ATOP);
            return;
        }
        ibBookmark.clearColorFilter();
    }
}