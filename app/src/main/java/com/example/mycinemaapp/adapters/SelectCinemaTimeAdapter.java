package com.example.mycinemaapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycinemaapp.R;
import com.example.mycinemaapp.models.SelectCinemaTimeItem;
import com.example.mycinemaapp.viewModels.MoviePageViewModel;
import com.example.mycinemaapp.viewModels.SeatBookingViewModel;

import java.util.List;

public class SelectCinemaTimeAdapter extends RecyclerView.Adapter<SelectCinemaTimeAdapter.ViewHolder> {

    private List<SelectCinemaTimeItem> selectCinemaTimeItemList;
    private Context context;
    RecyclerView selectTimeRecyclerView;
    MoviePageViewModel moviePageViewModel;

    public SelectCinemaTimeAdapter(Context context, List<SelectCinemaTimeItem> selectCinemaTimeItemList, RecyclerView selectTimeRecyclerView, MoviePageViewModel moviePageViewModel) {
        this.selectCinemaTimeItemList = selectCinemaTimeItemList;
        this.context = context;
        this.selectTimeRecyclerView = selectTimeRecyclerView;
        this.moviePageViewModel = moviePageViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_cinema, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SelectCinemaTimeItem item = selectCinemaTimeItemList.get(position);
        List<String> showTimes = item.getShowTimes();

        // Bind data to views
        holder.cinemaNameTextView.setText(item.getCinemaName());
//        holder.showTimeTextView.setText(item.getShowTime());

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
                // When the TextView is clicked, start a new activity
//                Intent intent = new Intent(HomeActivity.this, AllMovieActivity.class);
//                startActivity(intent);

//                findNavController().navigate(R.id);
                SelectShowTimesAdapter dateSelectAdapter = new SelectShowTimesAdapter(context, showTimes, item.getCinemaId(), moviePageViewModel);
//                selectShowRecyclerView

                holder.showTimeRecylerView.setAdapter(dateSelectAdapter);

                LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                holder.showTimeRecylerView.setLayoutManager(layoutManager);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return selectCinemaTimeItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView cinemaNameTextView;
        RecyclerView showTimeRecylerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cinemaNameTextView = itemView.findViewById(R.id.cinemaName);
            showTimeRecylerView = itemView.findViewById(R.id.selectShowRecyclerView);
        }
    }
}
