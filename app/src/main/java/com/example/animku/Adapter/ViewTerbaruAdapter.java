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
import com.example.animku.Model.TerbaruModel;
import com.example.animku.R;

import java.util.ArrayList;
import java.util.List;

public class ViewTerbaruAdapter extends RecyclerView.Adapter<ViewTerbaruAdapter.TerbaruHolder>{

    private List<AnimeModel> dataList;
    Context mContext;
    private List<TerbaruModel> terbaruModels;
    View viewku;

    public ViewTerbaruAdapter(Context mContext, List<AnimeModel> dataList, ArrayList<TerbaruModel> terbaruModels) {
        this.mContext = mContext;
        this.dataList = dataList;
        this.terbaruModels = terbaruModels;
    }

    @NonNull
    @Override
    public TerbaruHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        viewku = layoutInflater.inflate(R.layout.view_all, parent, false);
        return new TerbaruHolder(viewku);
    }

    @Override
    public void onBindViewHolder(@NonNull final TerbaruHolder holder, final int position) {
        holder.cvAnimeBaru.setVisibility(View.VISIBLE);
        holder.tvJudul.setText(dataList.get(Integer.parseInt(terbaruModels.get(position).getPosition())).getJudul());
        holder.tvEpisode.setText(dataList.get(Integer.parseInt(terbaruModels.get(position).getPosition())).getJmlepisode() + " episode");
        holder.tvType.setText(dataList.get(Integer.parseInt(terbaruModels.get(position).getPosition())).getTipe());
        Glide.with(holder.itemView.getContext()).load(dataList.get(Integer.parseInt(terbaruModels.get(position).getPosition())).getGambar()).into(holder.ivFoto);
        holder.cvAnimeBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(holder.itemView.getContext(), EpisodeAnime.class);
                in.putExtra("position", Integer.parseInt(terbaruModels.get(position).getPosition()));
                holder.itemView.getContext().startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return terbaruModels.size();
    }

    public class TerbaruHolder extends RecyclerView.ViewHolder{
        private TextView tvJudul, tvEpisode, tvType;
        CardView cvAnimeBaru;
        ImageView ivFoto;

        TerbaruHolder(View itemView) {
            super(itemView);
            ivFoto = itemView.findViewById(R.id.ivFoto);
            cvAnimeBaru = itemView.findViewById(R.id.cvAnime);
            tvJudul = itemView.findViewById(R.id.tvJudul);
            tvEpisode = itemView.findViewById(R.id.tvEpisode);
            tvType = itemView.findViewById(R.id.tvType);

        }
    }

}
