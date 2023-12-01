package com.example.mycinemaapp.activity;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mycinemaapp.R;
import com.example.mycinemaapp.adapters.MovieAdapterGrid;
import com.example.mycinemaapp.models.Movie;
import com.example.mycinemaapp.models.MovieEntity;
import com.example.mycinemaapp.utils.CsvReaderUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllMovieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllMovieFragment extends Fragment {

    public AllMovieFragment() {
        // Required empty public constructor
    }

    public static AllMovieFragment newInstance(String param1, String param2) {
        AllMovieFragment fragment = new AllMovieFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_all_movie, container, false);
        return inflater.inflate(R.layout.activity_all_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Assume you have a RecyclerView with the ID "recyclerView" in your layout
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

//        List<Movie> movieList = getMovies();
        List<MovieEntity> movieList = getMovies(getContext(), "movie.csv");

        // Create the adapter and set it to the RecyclerView
        MovieAdapterGrid movieAdapterGrid = new MovieAdapterGrid(getContext(), movieList, AllMovieFragmentDirections.actionAllMovieFragmentToMoviePageFragment());
        recyclerView.setAdapter(movieAdapterGrid);

        // Set a GridLayoutManager with, for example, 2 columns
        int spanCount = 2;
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), spanCount, GridLayoutManager.VERTICAL , false);
        recyclerView.setLayoutManager(layoutManager);
    }


    public List<MovieEntity> getMovies(Context context, String fileName) {
        List<MovieEntity> res = new ArrayList<>();
        try {
            List<String[]> csvData = CsvReaderUtil.readCsvFromAssets(context, fileName);

            int i = 0;
            for (String[] row : csvData) {
                // Parse CSV data and create MovieEntity objects
                MovieEntity movie = parseCsvRow(row);
                movie.setId(i++);

                // Insert each movie into the Room database
                res.add(movie);
                int a = 5;
            }
            return res;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private MovieEntity parseCsvRow(String[] row) {
        // Implement logic to convert CSV row to MovieEntity object
        // For example:
        return new MovieEntity(
                (row[0]),    // Assuming the first column is the image resource ID
                row[1],                      // Title
                Float.parseFloat(row[2]),    // Rating
                Integer.parseInt(row[3]),    // Duration
                row[4],                      // Type
                new ArrayList<>(Arrays.asList(row[5].split(","))),  // Categories
                row[6]                       // Synopsis
        );
    }

//    @NonNull
//    private static List<Movie> getMovies() {
//        // Create a list of Movie objects
//        List<Movie> movieList = new ArrayList<>();
//        Movie placeholderMovie = new Movie(
//                R.drawable.default_movie_image, // Replace with your placeholder image resource
//                "Placeholder Movie",
//                0.0f,
//                0,
//                "2D",
//                null,
//                "No synopsis available"
//        );
//        Movie placeholderMovie2 = new Movie(
//                R.drawable.ic_filter_icon, // Replace with your placeholder image resource
//                "Placeholder Movie",
//                0.0f,
//                0,
//                "2D",
//                null,
//                "No synopsis available"
//        );
//        Movie placeholderMovie3 = new Movie(
//                R.drawable.example_image2, // Replace with your placeholder image resource
//                "Placeholder Movie",
//                0.0f,
//                0,
//                "2D",
//                null,
//                "No synopsis available"
//        );
//        Movie placeholderMovie4 = new Movie(
//                R.drawable.example_image3, // Replace with your placeholder image resource
//                "Placeholder Movie",
//                0.0f,
//                0,
//                "2D",
//                null,
//                "No synopsis available"
//        );
//        Movie placeholderMovie5 = new Movie(
//                R.drawable.example_image4, // Replace with your placeholder image resource
//                "Placeholder Movie",
//                0.0f,
//                0,
//                "2D",
//                null,
//                "No synopsis available"
//        );
////        Movie placeholderMovie2 = placeholderMovie;
////        Movie placeholderMovie3 = placeholderMovie;
////        Movie placeholderMovie4 = placeholderMovie;
//        movieList.add(placeholderMovie);
//        movieList.add(placeholderMovie2);
//        movieList.add(placeholderMovie3);
//        movieList.add(placeholderMovie4);
//        movieList.add(placeholderMovie5);
//        return movieList;
//    }
}