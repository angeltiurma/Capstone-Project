package com.dicoding.nettoietonvisage.treatment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.nettoietonvisage.R;

import java.util.ArrayList;

public class LevelAdapt extends RecyclerView.Adapter<com.dicoding.nettoietonvisage.treatment.LevelAdapt.ListViewHolder> {
    private ArrayList<com.dicoding.nettoietonvisage.treatment.Level> listLevel;
    private OnItemClickCallBack onItemClickCallBack;

    public void setOnItemClickCallBack(OnItemClickCallBack onItemClickCallBack){
        this.onItemClickCallBack = onItemClickCallBack;
    }

    public LevelAdapt(ArrayList<com.dicoding.nettoietonvisage.treatment.Level> list){
        this.listLevel = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_card, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        com.dicoding.nettoietonvisage.treatment.Level level = listLevel.get(position);
        holder.tvUser.setText(level.getLevell());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallBack.onItemClicked(listLevel.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listLevel.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvUser;

        ListViewHolder(View itemView) {
            super(itemView);
            tvUser = itemView.findViewById(R.id.det_title);

        }
    }
    public interface OnItemClickCallBack{
        void onItemClicked(com.dicoding.nettoietonvisage.treatment.Level data);
    }
}