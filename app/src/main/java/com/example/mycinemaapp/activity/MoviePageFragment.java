package com.example.mycinemaapp.activity;

import static com.example.mycinemaapp.utils.Utility.loadBitmapFromAsset;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mycinemaapp.R;
import com.example.mycinemaapp.adapters.DateSelectAdapter;
import com.example.mycinemaapp.models.SelectCinemaTimeItem;
import com.example.mycinemaapp.models.SelectDateItem;
import com.example.mycinemaapp.models.MovieEntity;
import com.example.mycinemaapp.models.MovieParcelable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoviePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoviePageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
    private MovieParcelable mMovie;

    public MoviePageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param movie Parameter 2.
     * @return A new instance of fragment MoviePageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoviePageFragment newInstance(MovieEntity movie) {
        MoviePageFragment fragment = new MoviePageFragment();

        Bundle args = new Bundle();
        MovieParcelable movieParcelable = new MovieParcelable(movie);
        args.putParcelable(ARG_PARAM1, movieParcelable);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMovie = getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        view.findViewById(R.id.movieTitle);
        if (mMovie != null) {
            // Set values to the views
            updateMovieInformation(view);

            List<SelectDateItem> selectDateItemList = getDates();
            List<List<SelectCinemaTimeItem>> selectCinemaTimeLists = getCinemaTimeLists();
            int len = selectDateItemList.size();

            RecyclerView selectShowRecyclerView = view.findViewById(R.id.cinemaOptionsRecyclerView);
            DateSelectAdapter dateSelectAdapter = new DateSelectAdapter(selectDateItemList, selectCinemaTimeLists, selectShowRecyclerView);

            RecyclerView recyclerView = view.findViewById(R.id.dateOptionsRecyclerView);
            recyclerView.setAdapter(dateSelectAdapter);

            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(layoutManager);
//            recyclerView.setAdapter();
//            background.setImageResource(mMovie.getImagePath());
        }
    }

    private List<SelectDateItem> getDates() {
        List<SelectDateItem> dateList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        // Add dates for the next 7 days
        for (int i = 0; i < 7; i++) {
            Date date = calendar.getTime();
            String abbreviatedWeekday = new SimpleDateFormat("EEE", Locale.ENGLISH).format(date);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            dateList.add(new SelectDateItem(abbreviatedWeekday, day));

            // Move to the next day
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return dateList;
    }

    private List<List<SelectCinemaTimeItem>> getCinemaTimeLists() {
        List<List<SelectCinemaTimeItem>> selectCinemaTimeLists = new ArrayList<>();

        // List of cinema names
        List<String> cinemaNames = Arrays.asList("Cinema A", "Cinema B", "Cinema C", "Cinema D", "Cinema E");

        // List of show times
        List<String> showTimes = Arrays.asList("10:00 AM", "1:00 PM", "4:00 PM", "7:00 PM", "10:00 PM");

        Random random = new Random();

        // Number of cinema lists
        int numCinemaLists = 8; // You can adjust this as needed

        for (int i = 0; i < numCinemaLists; i++) {
            List<SelectCinemaTimeItem> cinemaTimeItems = new ArrayList<>();

            // Randomly initialize cinema names and show times
            for (int j = 0; j < cinemaNames.size(); j++) {
                String randomCinemaName = cinemaNames.get(random.nextInt(cinemaNames.size()));
                List<String> randomShowTimes = new ArrayList<>(showTimes);
                randomShowTimes.remove(random.nextInt(randomShowTimes.size())); // Randomly remove a show time
                cinemaTimeItems.add(new SelectCinemaTimeItem(randomCinemaName, randomShowTimes));
            }

            selectCinemaTimeLists.add(cinemaTimeItems);
        }

        return selectCinemaTimeLists;
    }

    private void updateMovieInformation(@NonNull View view) {
        TextView movieTitleTextView = view.findViewById(R.id.movieTitle);
        TextView ratingTextView = view.findViewById(R.id.ratingText);
        TextView durationTextView = view.findViewById(R.id.durationText);
        TextView typeTextView = view.findViewById(R.id.typeText);
        TextView categoriesTextView = view.findViewById(R.id.categoriesText);
        TextView synopsisTitleTextView = view.findViewById(R.id.sypnosisTitle);
        TextView synopsisTextView = view.findViewById(R.id.sypnosisText);

        ImageView background = view.findViewById(R.id.darkenedImage);

        // Set values from MovieParcelable
        movieTitleTextView.setText(mMovie.getTitle());
        ratingTextView.setText(String.valueOf(mMovie.getRating()));
        durationTextView.setText(String.valueOf(mMovie.getDuration()) + " minutes");
        typeTextView.setText(mMovie.getType());

        // Join categories list into a single string

//            String categories = TextUtils.join(", ", mMovie.getCategories());
//            categoriesTextView.setText(categories);
        categoriesTextView.setText(mMovie.getCategories());

        synopsisTitleTextView.setText("Synopsis");
        synopsisTextView.setText(mMovie.getSynopsis());

        String imagePath = mMovie.getImagePath();
        Bitmap imageBitMap = loadBitmapFromAsset(getContext(), imagePath);
        background.setImageBitmap(imageBitMap);
    }

}