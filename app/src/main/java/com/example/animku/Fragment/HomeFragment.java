package com.example.animku.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.animku.Adapter.AnimeTerbaruAdapter;
import com.example.animku.Adapter.AnimeTerpopulerAdapter;
import com.example.animku.GetAnime;
import com.example.animku.Model.AnimeModel;
import com.example.animku.R;
import com.example.animku.SearchAnime;
import com.example.animku.ViewAllTerbaru;
import com.example.animku.ViewAllTerpopuler;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class HomeFragment extends Fragment {

    private Realm realm;
    private ArrayList mAnimeList;
    RecyclerView rvListTerbaru, rvListTerpopuler;
    AnimeTerbaruAdapter animeTerbaruAdapter;
    AnimeTerpopulerAdapter animeTerpopulerAdapter;
    SwipeRefreshLayout refresh;
    TextView viewAllTerbaru, viewAllTerpopuler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Animku");

        rvListTerbaru = view.findViewById(R.id.rvListTerbaru);
        rvListTerpopuler = view.findViewById(R.id.rvListTerpopuler);
        refresh = view.findViewById(R.id.refresh);
        viewAllTerbaru = view.findViewById(R.id.viewAllTerbaru);
        viewAllTerpopuler = view.findViewById(R.id.viewAllTerpopuler);

        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        if (mAnimeList == null)
            mAnimeList = new ArrayList<>();

        RealmResults<AnimeModel> homeModels = realm.where(AnimeModel.class).findAll();
        mAnimeList.addAll(homeModels);

        animebaru(mAnimeList);

        animePopuler(mAnimeList);

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAnimeList.clear();
                        realm.beginTransaction();
                        realm.deleteAll();
                        realm.commitTransaction();
                        GetAnime getAnime = new GetAnime();
                        getAnime.tes();
                        animebaru(mAnimeList);
                        animePopuler(mAnimeList);
                        refresh.setRefreshing(false);
                    }
                }, 3000);
            }
        });

        viewAllTerbaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ViewAllTerbaru.class);
                startActivity(intent);
            }
        });

        viewAllTerpopuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ViewAllTerpopuler.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void animebaru(ArrayList mAnimeList) {
        animeTerbaruAdapter = new AnimeTerbaruAdapter(getContext(), mAnimeList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvListTerbaru.setLayoutManager(layoutManager);
        rvListTerbaru.setAdapter(animeTerbaruAdapter);
    }

    private void animePopuler(ArrayList mAnimeList) {
        animeTerpopulerAdapter = new AnimeTerpopulerAdapter(getContext(), mAnimeList);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvListTerpopuler.setLayoutManager(linearLayoutManager);
        rvListTerpopuler.setAdapter(animeTerpopulerAdapter);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.dashboard_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mSearch){
            Intent intent = new Intent(getContext(), SearchAnime.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
