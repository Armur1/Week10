package com.example.week10.util;

import android.content.Context;
import android.util.Log;

import com.example.week10.R;
import com.example.week10.model.Movie;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieData {

    public static List<Movie> loadMovieList(Context context) {
        List<Movie> movieList = new ArrayList<>();

        try {
            InputStream is = context.getResources().openRawResource(R.raw.movies);
            Scanner scanner = new Scanner(is).useDelimiter("\\A");
            String json = scanner.hasNext() ? scanner.next() : "";

            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject obj = jsonArray.getJSONObject(i);

                    String title = obj.has("title") && !obj.isNull("title")
                            ? obj.getString("title").trim()
                            : "";

                    String genre = obj.optString("genre", "").trim();
                    String poster = obj.optString("poster", "").trim();
                    int year = ErrorHandler.parseYear(obj);

                    // Infer title from poster
                    if (title.isEmpty() && !poster.isEmpty()) {
                        title = inferTitleFromPoster(poster);
                    }



                    // Skip entries with no data
                    boolean hasSomething =
                            (!title.isEmpty() && !title.equals("Untitled")) ||
                                    (year > 0) ||
                                    (!genre.isEmpty() && !genre.equals("Unknown Genre")) ||
                                    (!poster.isEmpty() && !poster.equals("default_poster"));

                    if (!hasSomething) {
                        Log.w("MovieData", "Skipping empty movie at index " + i);
                        continue;
                    }

                    movieList.add(new Movie(title, year, genre, poster));

                } catch (Exception e) {
                    Log.e("MovieData", "Skipping invalid movie entry at index " + i, e);
                }
            }

        } catch (Exception e) {
            Log.e("MovieData", "Error reading movies.json", e);
        }

        return movieList;
    }

    private static String inferTitleFromPoster(String poster) {
        if (poster == null || poster.isEmpty()) return "Untitled";

        String cleaned = poster.replace("_poster", "")
                .replace("_", " ")
                .trim();

        if (cleaned.isEmpty()) return "Untitled";

        String[] words = cleaned.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1))
                        .append(" ");
            }
        }

        return result.toString().trim();
    }

}




