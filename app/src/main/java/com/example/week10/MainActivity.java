package com.example.week10;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.week10.adapter.MovieAdapter;
import com.example.week10.model.Movie;
import com.example.week10.util.MovieData;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Movie> movies = MovieData.loadMovieList(this);
        if (movies.isEmpty()) {
            Toast.makeText(this, "No movies loaded", Toast.LENGTH_LONG).show();
        }

        recyclerView.setAdapter(new MovieAdapter(movies));
    }
}

