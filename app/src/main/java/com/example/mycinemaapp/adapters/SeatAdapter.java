package com.example.mycinemaapp.adapters;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycinemaapp.R;
import com.example.mycinemaapp.models.Seat;

import java.util.List;

public class SeatAdapter extends RecyclerView.Adapter<SeatAdapter.SeatViewHolder> {

    private List<Seat> seatList;

    // Constructor
    public SeatAdapter(List<Seat> seatList) {
        this.seatList = seatList;
    }

    @NonNull
    @Override
    public SeatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seat, parent, false);
        return new SeatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeatViewHolder holder, int position) {
        Seat seat = seatList.get(position);

        // Set data to views
        holder.seatIdTextView.setText("" + seat.getSeatRow() + seat.getSeatCol());
        holder.seatImageView.setImageResource(R.drawable.seat_unpressed);

        holder.seatImageView.clearColorFilter();
        switch (seat.getSeatState()) {
            case "AVAILABLE":
//                holder.seatImageView.setColorFilter(R.color.seat_state_available);
                break;
            case "BOOKED":
                holder.seatImageView.setColorFilter(R.color.seat_state_booked);
                break;
            case "SELECTING":
                holder.seatImageView.setColorFilter(R.color.seat_state_selected);
                break;
            default:
                holder.seatImageView.setColorFilter(Color.WHITE);
                holder.seatImageView = null;
                holder.seatIdTextView.setText("");
                break;
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle item click

                switch (seat.getSeatState()) {
                    case "AVAILABLE":
                        seat.setSeatState("SELECTING");
                        break;
                    case "SELECTING":
                        seat.setSeatState("AVAILABLE");
                        break;
                    default:
                        break;
                }
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return seatList.size();
    }

    // ViewHolder class
    public static class SeatViewHolder extends RecyclerView.ViewHolder {
        TextView seatIdTextView;
        ImageView seatImageView;

        public SeatViewHolder(@NonNull View itemView) {
            super(itemView);
            seatIdTextView = itemView.findViewById(R.id.seatIdTextView);
            seatImageView = itemView.findViewById(R.id.seatImageView);
        }
    }
}
