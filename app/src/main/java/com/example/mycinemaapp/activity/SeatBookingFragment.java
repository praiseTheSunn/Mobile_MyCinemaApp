package com.example.mycinemaapp.activity;

import static com.example.mycinemaapp.utils.Utility.loadBitmapFromAsset;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mycinemaapp.R;
import com.example.mycinemaapp.adapters.SeatAdapter;
import com.example.mycinemaapp.models.MovieShowEntity;
import com.example.mycinemaapp.models.Seat;
import com.example.mycinemaapp.parcelable.MovieShowParcelable;
import com.example.mycinemaapp.utils.Utility;
import com.example.mycinemaapp.viewModels.SeatBookingViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SeatBookingFragment extends Fragment {

    public static final String ARG_PARAM1 = "param1";
    public static final String ARG_PARAM2 = "param2";
    private SeatBookingViewModel seatBookingViewModel;

    public static SeatBookingFragment newInstance() {
        return new SeatBookingFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        seatBookingViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()))
                .get(SeatBookingViewModel.class);
        if (getArguments() != null) {
            seatBookingViewModel.movieShow = new MovieShowEntity(getArguments().getParcelable(ARG_PARAM1));
            seatBookingViewModel.setMovieImagePath(getArguments().getString(ARG_PARAM2));
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_seat_booking, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        seatBookingViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()))
                .get(SeatBookingViewModel.class);

        TextView selectedSeatCount = view.findViewById(R.id.selectedSeatCount);
        TextView totalPrice = view.findViewById(R.id.totalPrice);


        seatBookingViewModel.getSelectedSeat().observe(getViewLifecycleOwner(), newInt -> {
            selectedSeatCount.setText("x" + newInt);
            int TotalPrice = newInt * 5;

            // Format the TotalPrice as "45,000đ"
            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
            String formattedPrice = "$" + numberFormat.format(TotalPrice);

            totalPrice.setText(formattedPrice);
        });

        seatBookingViewModel.seatList = getSeatList();

        populateSpinner(view);

        ImageView movieImage = view.findViewById(R.id.darkenedImage);
        String newImagePath = seatBookingViewModel.getMovieImagePath();
        Bitmap imageBitMap = loadBitmapFromAsset(getContext(), newImagePath);
        movieImage.setImageBitmap(imageBitMap);


                // Set the item selection listener
//        cinemaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                String selectedPair = cinemaIdOptions.get(position);
//
//                // Update ViewModel with the selected pair
////                viewModel.setSelectedSpinnerItem(selectedPair.first);
////                viewModel.setViewModelAttributeY(selectedPair.second);
//                Log.d("SPINNER", selectedPair);
//                // Notify RecyclerView to update
//                // TODO: Call a method to update your RecyclerView based on the selected pair
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parentView) {
//                // Do nothing here if needed
//            }
//        });

        RecyclerView seatOptionsRecyclerView = view.findViewById(R.id.seatOptionsRecyclerView);
        SeatAdapter seatAdapter = new SeatAdapter(seatBookingViewModel.seatList, seatBookingViewModel);

        seatOptionsRecyclerView.setAdapter(seatAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), seatBookingViewModel.numberOfColumns);
        seatOptionsRecyclerView.setLayoutManager(gridLayoutManager);

        ImageView backButton = view.findViewById(R.id.fab_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigateUp();
            }
        });

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = SeatBookingFragmentDirections.actionSeatBookingFragmentToPurchaseFragment();
                Navigation.findNavController(view).navigate(action);
            }
        });
    }

    private void populateSpinner(@NonNull View view) {
        Spinner nameSpinner = view.findViewById(R.id.movieTitleSpinner);
        Spinner cinemaSpinner = view.findViewById(R.id.cinemaSpinner);
        Spinner timeSpinner = view.findViewById(R.id.timeSpinner);
        Spinner dateSpinner = view.findViewById(R.id.dateSpinner);

        // ArrayAdapter for the Spinner
        ArrayAdapter<String> nameAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                seatBookingViewModel.getAllMovieNamesAndIds(getContext()).first
        );

        // Specify the layout to use when the list of choices appears
        nameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                seatBookingViewModel.getAllShowTimes(getContext())
        );

        // Specify the layout to use when the list of choices appears
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> dateAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                seatBookingViewModel.getAllDates().first
        );

        // Specify the layout to use when the list of choices appears
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> cinemaAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                seatBookingViewModel.getAllCinemaNamesAndIds(getContext()).first
        );

        // Specify the layout to use when the list of choices appears
        cinemaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        nameSpinner.setAdapter(nameAdapter);
        cinemaSpinner.setAdapter(cinemaAdapter);
        timeSpinner.setAdapter(timeAdapter);
        dateSpinner.setAdapter(dateAdapter);


        Long movieId = seatBookingViewModel.movieShow.getMovieId(); // Replace with the value you want to select
        int positionToSelect = -1;
        List<Long> movieIds = seatBookingViewModel.getAllMovieNamesAndIds(getContext()).second;

        for (int i = 0; i < movieIds.size(); i++) {
            if (movieIds.get(i) == movieId) {
                positionToSelect = i;
                break;
            }
        }

        if (positionToSelect != -1) {
            nameSpinner.setSelection(positionToSelect);
        }

        Long cinemaId = seatBookingViewModel.movieShow.getCinemaId(); // Replace with the value you want to select
        positionToSelect = -1;
        List<Long> cinemaIds = seatBookingViewModel.getAllCinemaNamesAndIds(getContext()).second;

        for (int i = 0; i < cinemaIds.size(); i++) {
            if (cinemaIds.get(i) == cinemaId) {
                positionToSelect = i;
                break;
            }
        }

        if (positionToSelect != -1) {
            cinemaSpinner.setSelection(positionToSelect);
        }

        Date dateToSelect = seatBookingViewModel.movieShow.getShowDate(); // Replace with the value you want to select
        positionToSelect = -1;
        List<Date> dates = seatBookingViewModel.getAllDates().second;

        for (int i = 0; i < dates.size(); i++) {
            if (dates.get(i).equals(dateToSelect)) {
                positionToSelect = i;
                break;
            }
        }

        if (positionToSelect != -1) {
            dateSpinner.setSelection(positionToSelect);
        }

        String timeToSelect  = seatBookingViewModel.movieShow.getShowTime(); // Replace with the value you want to select
        positionToSelect = -1;
        List<String> times = seatBookingViewModel.getAllShowTimes(getContext());

        for (int i = 0; i < times.size(); i++) {

            if (times.get(i).equals(timeToSelect)) {
                positionToSelect = i;
                break;
            }
        }

        if (positionToSelect != -1) {
            timeSpinner.setSelection(positionToSelect);
        }
    }

    private List<Seat> getSeatList() {
        String SEAT_LAYOUT_TEXT =
                        "00X0XXXXX0X00\n" +
                        "0XX0XXXXX0++0\n" +
                        "++X0XXXXX0X++\n" +
                        "+++0XXXXX0XXX";

        List<Seat> seatList = new ArrayList<>();

        // Read the seat layout from the text file
        String[] rows = SEAT_LAYOUT_TEXT.split("\n");


        // Loop through each row and column to create Seat objects
        int id = 0;
        for (int row = 0; row < rows.length; row++) {
            char[] seatArray = rows[row].toCharArray();
            seatBookingViewModel.numberOfColumns = seatArray.length;

            for (int column = 0; column < seatArray.length; column++) {

                char seatType = seatArray[column];
                String type = "NONE";
                switch(seatType) {
                    case ('X'):
                        type = "AVAILABLE";
                        break;
                    case ('+'):
                        type = "BOOKED";
                        break;
                    default:
                        break;
                }
                // Create a Seat object and add it to the list
                Seat seat = new Seat(id++, row, column, type);
                seatList.add(seat);
            }
        }

        return seatList;
    }



}