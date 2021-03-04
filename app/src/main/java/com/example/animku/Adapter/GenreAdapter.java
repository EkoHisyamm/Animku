package com.example.animku.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animku.Model.GenreModel;
import com.example.animku.R;
import com.example.animku.ViewGenre;

import java.util.ArrayList;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreViewHolder>{

    private ArrayList<GenreModel> dataList;
    View viewku;

    public GenreAdapter(ArrayList<GenreModel> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        viewku = layoutInflater.inflate(R.layout.genre_view, parent, false);
        return new GenreAdapter.GenreViewHolder(viewku);
    }

    @Override
    public void onBindViewHolder(@NonNull final GenreViewHolder holder, final int position) {
        holder.tvGenre.setText(dataList.get(position).getGenre());
        holder.cvGenre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(holder.itemView.getContext(), ViewGenre.class);
                in.putExtra("genre", dataList.get(position).getGenre());
                holder.itemView.getContext().startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class GenreViewHolder extends RecyclerView.ViewHolder{
        private TextView tvGenre;
        CardView cvGenre;

        GenreViewHolder(View itemView) {
            super(itemView);
            cvGenre = itemView.findViewById(R.id.cvGenre);
            tvGenre = itemView.findViewById(R.id.tvGenre);

        }
    }

}
