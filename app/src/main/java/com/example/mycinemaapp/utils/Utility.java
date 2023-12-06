package com.example.mycinemaapp.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

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

    public static void showToast(Context context, String message) {
        // Create a Toast with the specified message
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);

        // Set the position of the Toast to the bottom
        toast.setGravity(Gravity.BOTTOM, 0, 16); // You can adjust the second parameter for the horizontal position

        // Show the Toast
        toast.show();
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View currentFocusedView = activity.getCurrentFocus();
        if (currentFocusedView != null) {
            inputManager.hideSoftInputFromWindow(currentFocusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
