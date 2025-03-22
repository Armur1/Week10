package com.example.week10.util;

import android.content.Context;
import android.util.Log;

import java.io.InputStream;
import java.util.Scanner;

public class JsonUtil {
    private static final String TAG = "JsonUtil";

    public static String loadJSONFromAsset(Context context, String filename) {
        try {
            InputStream is = context.getAssets().open(filename);
            Scanner scanner = new Scanner(is).useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : null;
        } catch (Exception e) {
            Log.e(TAG, "Error loading JSON from asset: " + filename, e);
            return null;
        }
    }
}


