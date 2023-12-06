package com.example.mycinemaapp.activity;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.example.mycinemaapp.R;
import com.example.mycinemaapp.viewModels.WatchTrailerViewModel;

public class WatchTrailerFragment extends Fragment {

    private static final String ARG_EMBED_LINK = "embedLink";

    private WatchTrailerViewModel mViewModel;
    String embedLink;

    public static WatchTrailerFragment newInstance(String embedLink) {
        WatchTrailerFragment fragment = new WatchTrailerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_EMBED_LINK, embedLink);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_watch_trailer, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            embedLink = getArguments().getString(ARG_EMBED_LINK);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set OnClickListener for the transparent background
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close the WebFragment
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        WebView webView = view.findViewById(R.id.webView);
//        String video = "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/V2KCAfHjySQ?si=Bfh1mAHfcZV4jy7d\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>";
        webView.loadData(embedLink, "text/html", "utf-8");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
    }
}