package com.example.mycinemaapp.adapters;

import static com.example.mycinemaapp.utils.Utility.loadBitmapFromAsset;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycinemaapp.R;
import com.example.mycinemaapp.activity.AllMovieFragmentDirections;
import com.example.mycinemaapp.activity.HomeFragmentDirections;
import com.example.mycinemaapp.activity.MoviePageFragment;
import com.example.mycinemaapp.models.MovieEntity;
import com.example.mycinemaapp.models.MovieParcelable;

import java.util.List;

public class MovieAdapterGrid extends RecyclerView.Adapter<MovieAdapterGrid.MovieViewHolder> {

    private Context context;
    private List<MovieEntity> movieList;
    private NavDirections toMoviePageAction;

    public MovieAdapterGrid(Context context, List<MovieEntity> movieList, NavDirections toMoviePageAction) {
        this.context = context;
        this.movieList = movieList;
        this.toMoviePageAction = toMoviePageAction;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("onCreateViewHolder", " onCreateViewHolder ne ");
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieEntity movie = movieList.get(position);
        Log.d("onBindViewHolder", " movie ne ");

        // Set values from the Movie instance
//        holder.movieImage.setImageResource(movie.getImagePath());
        String imagePath = movie.getImagePath();
//        holder.imageView.setImageResource();
        Bitmap imageBitMap = loadBitmapFromAsset(context, imagePath);
        holder.movieImage.setImageBitmap(imageBitMap);

        holder.movieTitle.setText(movie.getTitle());
        holder.movieDuration.setText(movie.getDuration() + " minutes");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle item click
                openItemDetailFragment(view, movie);
            }
        });
    }

    private void openItemDetailFragment(View view, MovieEntity movie) {
        // Create a new instance of ItemDetailFragment
//        MoviePageFragment fragment = MoviePageFragment.newInstance(movie);

        // Pass information to the fragment
//        Bundle args = new Bundle();
//            args.putString("key", item.getData()); // Replace "key" with the key for your data
//        fragment.setArguments(args);
        Bundle args = new Bundle();
        MovieParcelable movieParcelable = new MovieParcelable(movie);
        args.putParcelable(MoviePageFragment.ARG_PARAM1, movieParcelable);

//        NavDirections action = HomeFragmentDirections.actionHomePageFragmentToMoviePageFragment();
//        NavDirections action2 =
        Navigation.findNavController(view).navigate(toMoviePageAction.getActionId(), args);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView movieImage;
        TextView movieTitle;
        TextView movieDuration;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.movieImage);
            movieTitle = itemView.findViewById(R.id.movieTitle);
            movieDuration = itemView.findViewById(R.id.movieDuration);
        }
    }
}
