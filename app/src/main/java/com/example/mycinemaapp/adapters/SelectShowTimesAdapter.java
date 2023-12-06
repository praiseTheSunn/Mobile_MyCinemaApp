package com.example.mycinemaapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycinemaapp.R;
import com.example.mycinemaapp.viewModels.MoviePageViewModel;
import com.example.mycinemaapp.viewModels.SeatBookingViewModel;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class SelectShowTimesAdapter extends RecyclerView.Adapter<SelectShowTimesAdapter.ViewHolder> {

    private List<String> showTimes;
    private Context context;

    MoviePageViewModel moviePageViewModel;
    Long cinemaId;

    public SelectShowTimesAdapter(Context context, List<String> showTimes, Long cinemaId, MoviePageViewModel moviePageViewModel) {
        this.context = context;
        this.showTimes = showTimes;
        this.moviePageViewModel = moviePageViewModel;
        this.cinemaId = cinemaId;
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (moviePageViewModel.getPreviousSelectTimeCardView() == holder.selectTimeCardView) {
                    if (holder.selectTimeCardView.isChecked()) {
                        holder.selectTimeCardView.setChecked(false);
                    } else {
                        holder.selectTimeCardView.setChecked(true);
                    }
                    moviePageViewModel.movieShow.setShowTime(null);
                    moviePageViewModel.movieShow.setCinemaId((long)-1);
                } else {
                    holder.selectTimeCardView.setChecked(true);
                    if (moviePageViewModel.getPreviousSelectTimeCardView() != null) {
                        moviePageViewModel.getPreviousSelectTimeCardView().setChecked(false);
                    }
                    moviePageViewModel.setPreviousSelectTimeCardView(holder.selectTimeCardView);
                    moviePageViewModel.movieShow.setShowTime(time);
                    moviePageViewModel.movieShow.setCinemaId(cinemaId);
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return showTimes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTime;
        MaterialCardView selectTimeCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTime = itemView.findViewById(R.id.textViewTime);
            selectTimeCardView = itemView.findViewById(R.id.selectTimeCardView);
        }
    }
}
