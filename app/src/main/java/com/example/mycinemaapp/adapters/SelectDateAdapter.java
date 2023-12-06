package com.example.mycinemaapp.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycinemaapp.R;
import com.example.mycinemaapp.models.SelectCinemaTimeItem;
import com.example.mycinemaapp.models.SelectDateItem;
import com.example.mycinemaapp.viewModels.MoviePageViewModel;
import com.example.mycinemaapp.viewModels.SeatBookingViewModel;
import com.google.android.material.card.MaterialCardView;

import java.util.List;


public class SelectDateAdapter extends RecyclerView.Adapter<SelectDateAdapter.DateViewHolder> {

    private final RecyclerView selectCinemaTimeRecylerView;
    private List<SelectDateItem> selectDateItemList;
    private List<List<SelectCinemaTimeItem>> selectCinemaTimeLists;
    MoviePageViewModel moviePageViewModel;
    MaterialCardView previousSelectDateCardView = null;

    public SelectDateAdapter(List<SelectDateItem> selectDateItemList, List<List<SelectCinemaTimeItem>> selectCinemaTimeLists, RecyclerView selectCinemaTimeRecylerView, MoviePageViewModel moviePageViewModel) {
        this.selectDateItemList = selectDateItemList;
        this.selectCinemaTimeLists = selectCinemaTimeLists;
        this.selectCinemaTimeRecylerView = selectCinemaTimeRecylerView;
        this.moviePageViewModel =  moviePageViewModel;
    }

    @NonNull
    @Override
    public DateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_select_day, parent, false);
        return new DateViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DateViewHolder holder, int position) {
        SelectDateItem selectDateItem = selectDateItemList.get(position);
        List<SelectCinemaTimeItem> selectCinemaTimeItemList = selectCinemaTimeLists.get(position);

        // Bind data to the ViewHolder
        holder.textViewWeekday.setText(selectDateItem.getWeekday());
        holder.textViewDay.setText(String.valueOf(selectDateItem.getDay()));
        holder.selectDateCardView.setChecked(false);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moviePageViewModel.movieShow.setDate(selectDateItem.getDate());

                if (previousSelectDateCardView == holder.selectDateCardView) {
                    if (holder.selectDateCardView.isChecked()) {
//                        holder.selectDateCardView.setChecked(false);
                    }
                    else {
                        holder.selectDateCardView.setChecked(true);
                    }
                }
                else {
                    holder.selectDateCardView.setChecked(true);
                    if (previousSelectDateCardView != null) {
                        previousSelectDateCardView.setChecked(false);
                    }
                    previousSelectDateCardView = holder.selectDateCardView;

                }

//                findNavController().navigate(R.id);
                RecyclerView selectShowTimeRecyclerView = view.findViewById(R.id.selectShowRecyclerView);
                SelectCinemaTimeAdapter dateSelectAdapter = new SelectCinemaTimeAdapter(view.getContext(), selectCinemaTimeItemList, selectShowTimeRecyclerView, moviePageViewModel);

                selectCinemaTimeRecylerView.setAdapter(dateSelectAdapter);

                LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
                selectCinemaTimeRecylerView.setLayoutManager(layoutManager);
            }
        });
    }

    @Override
    public int getItemCount() {
        return selectDateItemList.size();
    }

    public static class DateViewHolder extends RecyclerView.ViewHolder {
        TextView textViewWeekday;
        TextView textViewDay;
        MaterialCardView selectDateCardView;

        public DateViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewWeekday = itemView.findViewById(R.id.textViewWeekday);
            textViewDay = itemView.findViewById(R.id.textViewDay);
            selectDateCardView = itemView.findViewById(R.id.selectDateCardView);
        }
    }
}
