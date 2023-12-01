package com.example.mycinemaapp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

public class Utility {
    public static Bitmap loadBitmapFromAsset(Context context, String filePath) {
        try {
            InputStream inputStream = context.getAssets().open(filePath);
            return BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
