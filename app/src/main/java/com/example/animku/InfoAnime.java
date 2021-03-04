package com.example.animku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.animku.Model.AnimeModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class InfoAnime extends AppCompatActivity {

    private RecyclerView rvListEpisode;
    private Realm realm;
    private List<AnimeModel> mAnimeList;
    TextView tvJudul, tvIsiEpisode, tvIsiType, tvIsiDurasi, tvIsiStudio, tvIsiGenre, tvIsiSkor, tvIsiStatus, tvIsiTayang, tvSinopsis;
    ImageView ivClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_anime);

        tvJudul = findViewById(R.id.tvJudul);
        tvIsiEpisode = findViewById(R.id.tvIsiEpisode);
        tvIsiType = findViewById(R.id.tvIsiType);
        tvIsiDurasi = findViewById(R.id.tvIsiDurasi);
        tvIsiStudio = findViewById(R.id.tvIsiStudio);
        tvIsiGenre = findViewById(R.id.tvIsiGenre);
        tvIsiSkor = findViewById(R.id.tvIsiSkor);
        tvIsiStatus = findViewById(R.id.tvIsiStatus);
        tvIsiTayang = findViewById(R.id.tvIsiTayang);
        tvSinopsis = findViewById(R.id.tvSinopsis);
        ivClose = findViewById(R.id.ivClose);

        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        if (mAnimeList == null)
            mAnimeList = new ArrayList<>();

        RealmResults<AnimeModel> homeModels = realm.where(AnimeModel.class).findAll();
        mAnimeList.addAll(homeModels);

        Bundle extras = getIntent().getExtras();
        final int position = extras.getInt("position");

        tvJudul.setText(mAnimeList.get(position).getJudul());
        tvIsiEpisode.setText(": " + mAnimeList.get(position).getJmlepisode());
        tvIsiType.setText(": " + mAnimeList.get(position).getTipe());
        tvIsiDurasi.setText(": " + mAnimeList.get(position).getDurasi());
        tvIsiStudio.setText(": " + mAnimeList.get(position).getStudio());
        tvIsiGenre.setText(": " + mAnimeList.get(position).getGenre());
        tvIsiSkor.setText(": " + mAnimeList.get(position).getSkor());
        tvIsiStatus.setText(": " + mAnimeList.get(position).getStatus());
        tvIsiTayang.setText(": " + mAnimeList.get(position).getTayang());
        tvSinopsis.setText(mAnimeList.get(position).getSinopsis());

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}