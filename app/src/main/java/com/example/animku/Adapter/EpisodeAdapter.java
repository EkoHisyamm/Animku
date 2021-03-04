package com.example.animku.Adapter;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.animku.Model.EpisodeModel;
import com.example.animku.R;
import com.example.animku.VideoPlayer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class                                                                                                       EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.ViewHolder>{

    private ArrayList<EpisodeModel> dataList;
    View viewku;
    String p720, p480, p360;

    public EpisodeAdapter(ArrayList<EpisodeModel> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        viewku = layoutInflater.inflate(R.layout.episode_view, parent, false);
        return new EpisodeAdapter.ViewHolder(viewku);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.tvEpisode.setText(dataList.get(position).getEpisode());
        if (position % 2 == 1){
            holder.linear.setBackgroundColor(Color.parseColor("#000000"));
        }else {
            holder.linear.setBackgroundColor(Color.parseColor("#1B1B1B"));
        }
        holder.cvEpisode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AndroidNetworking.post("https://animendo.000webhostapp.com/API/episodelist.php")
                        .addBodyParameter("judul", dataList.get(position).getJudul())
                        .addBodyParameter("episode", dataList.get(position).getId())
                        .setPriority(Priority.LOW)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray data = response.getJSONArray("result");
                                    for (int i = 0; i < data.length(); i++) {
                                        JSONObject object = data.getJSONObject(i);
                                        p720 = object.getString("720");
                                        p480 = object.getString("480");
                                        p360 = object.getString("360");
                                    }
                                    Log.e("tes", "onResponse: " + p720);
                                    Log.e("tes", "onResponse: " + p480);
                                    Log.e("tes", "onResponse: " + p360);
                                    Intent intent = new Intent(holder.itemView.getContext(), VideoPlayer.class);
                                    intent.putExtra("position", dataList.get(position).getPosition());
                                    intent.putExtra("p720", p720);
                                    intent.putExtra("p480", p480);
                                    intent.putExtra("p360", p360);
                                    holder.itemView.getContext().startActivity(intent);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            @Override
                            public void onError(ANError anError) {

                            }
                        });

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvEpisode;
        LinearLayout linear;
        CardView cvEpisode;

        ViewHolder(View itemView) {
            super(itemView);
            cvEpisode = itemView.findViewById(R.id.cvEpisode);
            linear = itemView.findViewById(R.id.linear);
            tvEpisode = itemView.findViewById(R.id.tvEpisode);

        }
    }

}
