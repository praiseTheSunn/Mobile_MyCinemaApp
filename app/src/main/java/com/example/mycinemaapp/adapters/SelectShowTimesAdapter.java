package com.example.mycinemaapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycinemaapp.R;

import java.util.List;

public class SelectShowTimesAdapter extends RecyclerView.Adapter<SelectShowTimesAdapter.ViewHolder> {

    private List<String> showTimes;
    private Context context;

    public SelectShowTimesAdapter(Context context, List<String> showTimes) {
        this.context = context;
        this.showTimes = showTimes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_select_time, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String time = showTimes.get(position);
        holder.textViewTime.setText(time);
    }

    @Override
    public int getItemCount() {
        return showTimes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTime = itemView.findViewById(R.id.textViewTime);
        }
    }
}
