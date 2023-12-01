package com.example.mycinemaapp.adapters;

//public class HomeSlideAdapter extends Carousel.Adapter {
//    private Context context;
//    private List<Integer> imageList;
//    private List<Movie> movieList;
//
//    public HomeSliderAdapter(Context context, List<Movie> movieList) {
//        this.context = context;
//        this.movieList = movieList;
//    }
//}

//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.mycinemaapp.R;
//import com.example.mycinemaapp.models.Movie;
//
//import java.util.List;
//
//public class HomeSliderAdapter extends RecyclerView.Adapter<HomeSliderAdapter.ImageViewHolder> {
//    private Context context;
//    private List<Integer> imageList;
//    private List<Movie> movieList;
//
//    public HomeSliderAdapter(Context context, List<Movie> movieList) {
//        this.context = context;
//        this.movieList = movieList;
//    }
//
//    @Override
//    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.item_home_slider, parent, false);
//        return new ImageViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(ImageViewHolder holder, int position) {
////        int imageResId = imageList.get(position);
////        holder.imageView.setImageResource(imageResId);
//
//        Movie movie = movieList.get(position);
//        holder.imageView.setImageResource(movie.getImage());
//    }
//
//    @Override
//    public int getItemCount() {
//        return movieList.size();
//    }
//
//    public static class ImageViewHolder extends RecyclerView.ViewHolder {
//        ImageView imageView;
//
//        public ImageViewHolder(View itemView) {
//            super(itemView);
//            imageView = itemView.findViewById(R.id.homeSliderImageView);
//        }
//    }
//}


import static com.example.mycinemaapp.utils.Utility.loadBitmapFromAsset;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycinemaapp.R;
import com.example.mycinemaapp.models.Movie;
import com.example.mycinemaapp.models.MovieEntity;

import java.util.List;

public class HomeSliderAdapter extends RecyclerView.Adapter<HomeSliderAdapter.ImageViewHolder> {

    private Context context;
    private int[] imageArray;
    private List<MovieEntity> movieList;

    public HomeSliderAdapter(Context context, List<MovieEntity> movieList) {
        this.context = context;
//        this.imageArray = imageArray;
        this.movieList = movieList;

    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_slider, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        MovieEntity movie = movieList.get(position);
        String imagePath = movie.getImagePath();
//        holder.imageView.setImageResource();
        Bitmap imageBitMap = loadBitmapFromAsset(context, imagePath);
        holder.imageView.setImageBitmap(imageBitMap);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.homeSliderImageView);
        }
    }
}
