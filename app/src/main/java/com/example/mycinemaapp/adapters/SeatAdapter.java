package com.example.mycinemaapp.adapters;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycinemaapp.R;
import com.example.mycinemaapp.models.Seat;
import com.example.mycinemaapp.viewModels.SeatBookingViewModel;

import java.util.List;

public class SeatAdapter extends RecyclerView.Adapter<SeatAdapter.SeatViewHolder> {

    private List<Seat> seatList;
    SeatBookingViewModel seatBookingViewModel;

    // Constructor
    public SeatAdapter(List<Seat> seatList, SeatBookingViewModel seatBookingViewModel) {
        this.seatList = seatList;
        this.seatBookingViewModel = seatBookingViewModel;
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
                holder.seatImageView.setColorFilter(ContextCompat.getColor(holder.itemView.getContext(), R.color.seat_state_booked), PorterDuff.Mode.SRC_IN);

//                holder.seatImageView.setImageTintList(ColorStateList.valueOf(getResources().color.R.color.seat_state_booked));
                break;
            case "SELECTING":
                holder.seatImageView.setColorFilter(ContextCompat.getColor(holder.itemView.getContext(), R.color.seat_state_selected), PorterDuff.Mode.SRC_IN);
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
                        notifyItemChanged(position);
                        seatBookingViewModel.increaseSelectedSeat();
                        break;
                    case "SELECTING":
                        seat.setSeatState("AVAILABLE");
                        notifyItemChanged(position);
                        seatBookingViewModel.decreaseSelectedSeat();
                        break;
                    default:
                        break;
                }

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
