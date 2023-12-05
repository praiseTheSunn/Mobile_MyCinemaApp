package com.example.mycinemaapp.activity;

import static com.example.mycinemaapp.utils.Utility.loadBitmapFromAsset;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mycinemaapp.R;
import com.example.mycinemaapp.adapters.SelectDateAdapter;
import com.example.mycinemaapp.models.MovieShowEntity;
import com.example.mycinemaapp.models.SelectCinemaTimeItem;
import com.example.mycinemaapp.models.SelectDateItem;
import com.example.mycinemaapp.models.MovieEntity;
import com.example.mycinemaapp.parcelable.MovieParcelable;
import com.example.mycinemaapp.parcelable.MovieShowParcelable;
import com.example.mycinemaapp.repositories.CinemaRepository;
import com.example.mycinemaapp.repositories.MovieShowRepository;
import com.example.mycinemaapp.viewModels.MoviePageViewModel;
import com.example.mycinemaapp.viewModels.SeatBookingViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    private MoviePageViewModel moviePageViewModel;

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
        moviePageViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()))
                .get(MoviePageViewModel.class);
        moviePageViewModel.movieShow = new MovieShowEntity(new Date(), "", (long)0, (long)0, mMovie.getId());
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
            List<List<SelectCinemaTimeItem>> selectCinemaTimeLists = getCinemaTimeLists(selectDateItemList);
            int len = selectDateItemList.size();

            RecyclerView selectShowRecyclerView = view.findViewById(R.id.cinemaOptionsRecyclerView);
            SelectDateAdapter selectDateAdapter = new SelectDateAdapter(selectDateItemList, selectCinemaTimeLists, selectShowRecyclerView, moviePageViewModel);

            RecyclerView recyclerView = view.findViewById(R.id.dateOptionsRecyclerView);
            recyclerView.setAdapter(selectDateAdapter);

            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(layoutManager);

            FloatingActionButton fab = view.findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    moviePageViewModel.setMovieImagePath(mMovie.getImagePath());

                    Bundle args = new Bundle();

                    MovieShowParcelable movieShowParcelable = new MovieShowParcelable(moviePageViewModel.movieShow);

                    Log.d("viewModel", "" + moviePageViewModel.movieShow.getShowTime() + moviePageViewModel.movieShow.getShowDate());

//                    MovieParcelable movieParcelable = new MovieParcelable(movie);
                    args.putParcelable(SeatBookingFragment.ARG_PARAM1, movieShowParcelable);
                    args.putString(SeatBookingFragment.ARG_PARAM2, mMovie.getImagePath());

                    NavDirections action = MoviePageFragmentDirections.actionMoviePageFragmentToSeatBookingFragment();
                    Navigation.findNavController(view).navigate(action.getActionId(), args);
                }
            });
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

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String ymdDate = sdf.format(date);

            dateList.add(new SelectDateItem(abbreviatedWeekday, day, java.sql.Date.valueOf(ymdDate)));

            // Move to the next day
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return dateList;
    }

    private List<List<SelectCinemaTimeItem>> getCinemaTimeLists(List<SelectDateItem> dateList) {
        List<List<SelectCinemaTimeItem>> selectCinemaTimeLists = new ArrayList<>();

        // List of cinema names
//        List<String> cinemaNames = Arrays.asList("Cinema A", "Cinema B", "Cinema C", "Cinema D", "Cinema E");

        // List of show times
//        List<String> showTimes = Arrays.asList("10:00 AM", "1:00 PM", "4:00 PM", "7:00 PM", "10:00 PM");

//        Random random = new Random();

        // Number of cinema lists
        int numCinemaLists = dateList.size(); // You can adjust this as needed

        CinemaRepository cinemaRepository = new CinemaRepository(getContext());
        MovieShowRepository movieShowRepository = new MovieShowRepository(getContext());

        for (int i = 0; i < numCinemaLists; i++) {
            List<SelectCinemaTimeItem> cinemaTimeItems = new ArrayList<>();
            SelectDateItem curDate = dateList.get(i);

            Date date = curDate.getDate();
            List<String> cinemaNames = movieShowRepository.getCinemaNamesByDateAndMovieId(date, mMovie.getId());
            List<Long> cinemaIds = movieShowRepository.getCinemaIdsByDateAndMovieId(date, mMovie.getId()).blockingFirst();

            for (int j = 0; j < cinemaNames.size(); j++) {
                Long cinemaId = cinemaIds.get(j);
                String cinemaName = cinemaNames.get(j);

                List<String> showTimes = movieShowRepository.getTimesByDateAndMovieIdAndCinemaId(date, mMovie.getId(), cinemaId);
//                List<String> showTimes = movieShowRepository.getShowTimes(date, cinemaName);
                cinemaTimeItems.add(new SelectCinemaTimeItem(cinemaId, cinemaName, showTimes));
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