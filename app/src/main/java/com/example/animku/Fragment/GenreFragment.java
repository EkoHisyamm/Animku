package com.example.animku.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animku.Adapter.GenreAdapter;
import com.example.animku.Model.GenreModel;
import com.example.animku.R;
import com.example.animku.SearchAnime;

import java.util.ArrayList;

public class GenreFragment extends Fragment {

    RecyclerView rvGenre;
    GenreAdapter genreAdapter;
    ArrayList<GenreModel> genreArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_genre, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Animku");

        rvGenre = view.findViewById(R.id.rvGenre);

        genreArrayList = new ArrayList<>();
        genreArrayList.add(new GenreModel("1", "Comedy"));
        genreArrayList.add(new GenreModel("2","Sport"));
        genreArrayList.add(new GenreModel("3","Action"));
        genreArrayList.add(new GenreModel("4","Harem"));
        genreArrayList.add(new GenreModel("5","Isekai"));
        genreArrayList.add(new GenreModel("6","School life"));

        genreAdapter = new GenreAdapter(genreArrayList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        rvGenre.setLayoutManager(gridLayoutManager);
        rvGenre.setAdapter(genreAdapter);

        return view;
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
