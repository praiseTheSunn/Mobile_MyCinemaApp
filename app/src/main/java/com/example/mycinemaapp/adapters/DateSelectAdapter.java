package com.example.mycinemaapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycinemaapp.R;
import com.example.mycinemaapp.activity.HomeFragmentDirections;
import com.example.mycinemaapp.models.SelectCinemaTimeItem;
import com.example.mycinemaapp.models.SelectDateItem;

import java.util.List;


public class DateSelectAdapter extends RecyclerView.Adapter<DateSelectAdapter.DateViewHolder> {

    private final RecyclerView selectCinemaTimeRecylerView;
    private List<SelectDateItem> selectDateItemList;
    private List<List<SelectCinemaTimeItem>> selectCinemaTimeLists;

    public DateSelectAdapter(List<SelectDateItem> selectDateItemList, List<List<SelectCinemaTimeItem>> selectCinemaTimeLists, RecyclerView selectCinemaTimeRecylerView) {
        this.selectDateItemList = selectDateItemList;
        this.selectCinemaTimeLists = selectCinemaTimeLists;
        this.selectCinemaTimeRecylerView = selectCinemaTimeRecylerView;
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When the TextView is clicked, start a new activity
//                Intent intent = new Intent(HomeActivity.this, AllMovieActivity.class);
//                startActivity(intent);

//                findNavController().navigate(R.id);
                RecyclerView selectShowTimeRecyclerView = view.findViewById(R.id.selectShowRecyclerView);
                SelectCinemaTimeAdapter dateSelectAdapter = new SelectCinemaTimeAdapter(view.getContext(), selectCinemaTimeItemList, selectShowTimeRecyclerView);

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

        public DateViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewWeekday = itemView.findViewById(R.id.textViewWeekday);
            textViewDay = itemView.findViewById(R.id.textViewDay);
        }
    }
}
