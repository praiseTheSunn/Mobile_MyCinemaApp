package com.example.mycinemaapp.activity;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.mycinemaapp.R;
import com.example.mycinemaapp.adapters.MovieAdapterGrid;
import com.example.mycinemaapp.models.MovieEntity;
import com.example.mycinemaapp.utils.CsvReaderUtil;
import com.example.mycinemaapp.viewModels.AllMovieFragmentViewModel;
import com.example.mycinemaapp.viewModels.HomeFragmentViewModel;
import com.example.mycinemaapp.viewModels.MoviePageViewModel;

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
    private AllMovieFragmentViewModel viewModel;
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

        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()))
                .get(AllMovieFragmentViewModel.class);


        List<MovieEntity> movieList = viewModel.getMovies(getContext());

        // Create the adapter and set it to the RecyclerView
        MovieAdapterGrid movieAdapterGrid = new MovieAdapterGrid(getContext(), movieList, AllMovieFragmentDirections.actionAllMovieFragmentToMoviePageFragment());
        recyclerView.setAdapter(movieAdapterGrid);

        // Set a GridLayoutManager with, for example, 2 columns
        int spanCount = 2;
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), spanCount, GridLayoutManager.VERTICAL , false);
        recyclerView.setLayoutManager(layoutManager);

        SearchView searchView = view.findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // This method is called when the user submits the query (e.g., presses Enter).
                // Handle the search here.
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // This method is called when the text in the search view changes.
                // You can perform actions while the user is typing.
                List<MovieEntity> newMovieEntities = viewModel.getMoviesWithNames(getContext(), newText);
                movieAdapterGrid.setNewMovieEntities(newMovieEntities);
                movieAdapterGrid.notifyDataSetChanged();
                return true;
            }
        });
    }
}