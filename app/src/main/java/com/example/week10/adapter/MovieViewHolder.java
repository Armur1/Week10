package com.example.week10.adapter;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.week10.R;
import com.example.week10.model.Movie;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    private final TextView titleView, yearView, genreView;

    public MovieViewHolder(View itemView) {
        super(itemView);
        titleView = itemView.findViewById(R.id.titleText);
        yearView = itemView.findViewById(R.id.yearText);
        genreView = itemView.findViewById(R.id.genreText);
    }

    public void bind(Movie movie) {
        titleView.setText(movie.getTitle());
        yearView.setText(movie.getYear() > 0 ? String.valueOf(movie.getYear()) : "Unknown Year");
        genreView.setText(movie.getGenre());
    }
}


