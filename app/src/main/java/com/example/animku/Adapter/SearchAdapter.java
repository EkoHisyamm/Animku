package com.example.animku.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.animku.EpisodeAnime;
import com.example.animku.Model.AnimeModel;
import com.example.animku.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>{

    private List<AnimeModel> dataList;
    View viewku;

    public SearchAdapter(ArrayList<AnimeModel> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        viewku = layoutInflater.inflate(R.layout.search_view, parent, false);
        return new SearchAdapter.SearchViewHolder(viewku);
    }

    @Override
    public void onBindViewHolder(@NonNull final SearchViewHolder holder, final int position) {
        holder.tvJudul.setText(dataList.get(position).getJudul());
        holder.tvEpisode.setText(dataList.get(position).getJmlepisode() + " episode");
        holder.tvType.setText(dataList.get(position).getTipe());
        Glide.with(holder.itemView.getContext()).load(dataList.get(position).getGambar()).into(holder.ivFoto);
        holder.cvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(holder.itemView.getContext(), EpisodeAnime.class);
                in.putExtra("position", position);
                holder.itemView.getContext().startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder{
        private TextView tvJudul, tvEpisode, tvType;
        CardView cvSearch;
        ImageView ivFoto;

        SearchViewHolder(View itemView) {
            super(itemView);
            cvSearch = itemView.findViewById(R.id.cvSearch);
            tvJudul = itemView.findViewById(R.id.tvJudul);
            tvEpisode = itemView.findViewById(R.id.tvEpisode);
            tvType = itemView.findViewById(R.id.tvType);
            ivFoto = itemView.findViewById(R.id.ivFoto);
        }
    }
}
