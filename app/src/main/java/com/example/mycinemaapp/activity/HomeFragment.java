package com.example.mycinemaapp.activity;

import static com.example.mycinemaapp.utils.Utility.loadBitmapFromAsset;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.helper.widget.Carousel;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mycinemaapp.R;
import com.example.mycinemaapp.adapters.HomeSliderAdapter;
import com.example.mycinemaapp.models.MovieEntity;
import com.example.mycinemaapp.utils.CsvReaderUtil;
import com.example.mycinemaapp.adapters.MovieAdapterGrid;
import com.example.mycinemaapp.models.Movie;
import com.example.mycinemaapp.viewModels.HomeFragmentViewModel;
import com.example.mycinemaapp.viewModels.MoviePageViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.rxjava3.disposables.Disposable;

//import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private HomeFragmentViewModel viewModel;
    List<MovieEntity> movieList = null;
    List<MovieEntity> hotMovieList = null;

    Carousel carousel = null;

    private ViewPager2 viewPager;

    Disposable movieListUpdateDisposable;


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

        return inflater.inflate(R.layout.activity_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //         Initialize ViewModel
        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()))
                .get(HomeFragmentViewModel.class);

//        movieList = getMovies(getContext(), "movie.csv");
//        hotMovieList = getMovies(getContext(), "movie_hot.csv");

        movieList = viewModel.getMovies(getContext());
        hotMovieList = viewModel.getHotMovies(getContext());
//        movieList = viewModel.getMovies().getValue();
//        hotMovieList =  viewModel.getHotMovies().getValue();
//

        // update movies
//        disposable = viewModel.getMovies()
//                .observeOn(AndroidSchedulers.mainThread()) // Make sure UI updates happen on the main thread
//                .subscribe(movieEntities -> {
//                    // Update UI with the list of movies
//                    // Implement your UI logic here
//                    movieList = movieEntities;
//                }, throwable -> {
//                    // Handle errors if any
//                    Log.e("DATABASE", "Error updating UI", throwable);
//
//                });
//

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        // Create the adapter and set it to the RecyclerView
        MovieAdapterGrid movieAdapterGrid = new MovieAdapterGrid(getContext(), movieList, HomeFragmentDirections.actionHomePageFragmentToMoviePageFragment());
        recyclerView.setAdapter(movieAdapterGrid);

        // Set a GridLayoutManager with, for example, 2 columns
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        viewPager = view.findViewById(R.id.viewPager);
        HomeSliderAdapter imageAdapter = new HomeSliderAdapter(getContext(), hotMovieList);
        viewPager.setAdapter(imageAdapter);
//        viewPager.setPageTransformer(new OffsetPageTransformer(1, 1));

        viewPager.setOffscreenPageLimit(5);

        int nextItemVisiblePx = 590;
        int currentItemHorizontalMarginPx = 100;
        float pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx;

        ViewPager2.PageTransformer pageTransformer = (page, position) -> {
            page.setTranslationX(-pageTranslationX * position);
            // Next line scales the item's height. You can remove it if you don't want this effect
            page.setScaleY(1 - (0.25f * Math.abs(position)));
            page.setTranslationZ(1 - Math.abs(position));
            // If you want a fading effect uncomment the next line:
             page.setAlpha(0.25f + (1 - Math.abs(position)));
        };

        viewPager.setPageTransformer(pageTransformer);
        viewPager.addItemDecoration(new HorizontalMarginItemDecoration(requireContext(), currentItemHorizontalMarginPx));

//        setupCarousel(view, movieList);


        TextView textViewAll = view.findViewById(R.id.textViewAll);

        // Set an OnClickListener on the TextView
        textViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = HomeFragmentDirections.actionHomePageFragmentToAllMovieFragment();
                Navigation.findNavController(view).navigate(action);
            }
        });
    }

    private void setupCarousel(@NonNull View view, List<Movie> movieList) {
        carousel = view.findViewById(R.id.carousel);
//        mViewModel.setMotionProgress(carousel.getCurrentIndex());
//
//        mViewModel.setMotionProgress(carousel.getCurrentIndex());
//        mViewModel.getMotionProgress().observe(getViewLifecycleOwner(), newProgress -> {
//            Log.d("motion view update", " update chua d" + newProgress);
//            if (newProgress != null)
//                carousel.transitionToIndex(newProgress, 0);
//        });

        carousel.setAdapter(new Carousel.Adapter() {
            @Override
            public int count() {
                return movieList.size();
            }

            @Override
            public void populate(View view, int index){
                CardView cardView = (CardView) view;
                ImageView imageView = cardView.findViewById(R.id.homeSliderImageView);
                Movie movie = movieList.get(index);
//                imageView.setImageResource(movie.getImagePath());
                String imagePath = movie.getImagePath();
                Bitmap imageBitMap = loadBitmapFromAsset(getContext(), imagePath);
                imageView.setImageBitmap(imageBitMap);
            }

            @Override
            public void onNewItem(int index) {

            }
        });
    }

    public class HorizontalMarginItemDecoration extends RecyclerView.ItemDecoration {

        private final int horizontalMarginInPx;

        public HorizontalMarginItemDecoration(Context context, int horizontalMarginInDp) {
            this.horizontalMarginInPx = horizontalMarginInDp;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = horizontalMarginInPx;
            outRect.right = horizontalMarginInPx;
        }
    }

    public class OverlappingPageTransformer implements ViewPager2.PageTransformer {
        private static final float OVERLAP_FACTOR = 0.5f; // Adjust this value based on your preference

        @Override
        public void transformPage(@NonNull View page, float position) {
            int width = page.getWidth();
            if (position < -1) {
                // Page is off-screen to the left
                page.setAlpha(0f);
            } else if (position <= 0) {
                // Page is moving from left to center
                page.setTranslationX(-width * position * OVERLAP_FACTOR);
            } else if (position <= 1) {
                // Page is in the center or moving from center to right
                page.setTranslationX(0f);
            } else {
                // Page is off-screen to the right
                page.setAlpha(0f);
            }
        }
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

    @Override
    public void onPause() {
        super.onPause();
//        mViewModel.setMotionProgress(carousel.getCurrentIndex());
    }

    @Override
    public void onResume() {
        super.onResume();
//        carousel.invalidate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        movieListUpdateDisposable.dispose();
    }

}