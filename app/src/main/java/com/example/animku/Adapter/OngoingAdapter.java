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

public class OngoingAdapter extends RecyclerView.Adapter<OngoingAdapter.OngoingViewHolder> {

    private List<AnimeModel> dataList;
    Context mContext;
    View viewku;

    public OngoingAdapter(Context mContext, ArrayList<AnimeModel> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public OngoingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        viewku = layoutInflater.inflate(R.layout.ongoing_view, parent, false);
        return new OngoingAdapter.OngoingViewHolder(viewku);
    }

    @Override
    public void onBindViewHolder(@NonNull final OngoingViewHolder holder, final int position) {
        if (dataList.get(position).getStatus().equals("Ongoing")) {
            holder.cvAnimeBaru.setVisibility(View.VISIBLE);
            holder.tvJudul.setText(dataList.get(position).getJudul());
            holder.tvEpisode.setText(dataList.get(position).getJmlepisode() + " episode");
            holder.tvType.setText(dataList.get(position).getTipe());
            Glide.with(holder.itemView.getContext()).load(dataList.get(position).getGambar()).into(holder.ivFoto);
            holder.cvAnimeBaru.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in = new Intent(holder.itemView.getContext(), EpisodeAnime.class);
                    in.putExtra("position", position);
                    holder.itemView.getContext().startActivity(in);
                }
            });
        }else {
            holder.cvAnimeBaru.setVisibility(View.GONE);
        }    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class OngoingViewHolder extends RecyclerView.ViewHolder {
        private TextView tvJudul, tvEpisode, tvType;
        CardView cvAnimeBaru;
        ImageView ivFoto;

        OngoingViewHolder(View itemView) {
            super(itemView);
            ivFoto = itemView.findViewById(R.id.ivFoto);
            cvAnimeBaru = itemView.findViewById(R.id.cvAnime);
            tvJudul = itemView.findViewById(R.id.tvJudul);
            tvEpisode = itemView.findViewById(R.id.tvEpisode);
            tvType = itemView.findViewById(R.id.tvType);

        }
    }
}
