package com.example.mycinemaapp;

import android.view.View;
import androidx.viewpager2.widget.ViewPager2;

public class HomeSliderDepthPageTransformer implements ViewPager2.PageTransformer {
    private static final float MIN_SCALE = 0.75f;

    @Override
    public void transformPage(View page, float position) {
        int pageWidth = page.getWidth();

        if (position < -1) { // [-Infinity,-1)
            page.setAlpha(0f);
        } else if (position <= 0) { // [-1,0]
            page.setAlpha(1f + position);
            page.setTranslationX(pageWidth * -position);
            float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 + position);
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
        } else if (position <= 1) { // (0,1]
            page.setAlpha(1f - position);
            page.setTranslationX(pageWidth * -position);
            float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - position);
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
        } else { // (1,+Infinity]
            page.setAlpha(0f);
        }
    }
}
