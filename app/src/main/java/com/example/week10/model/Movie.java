package com.example.week10.model;

public class Movie {
    private final String title;
    private final int year;
    private final String genre;
    private final String poster;

    public Movie(String title, int year, String genre, String poster) {
        this.title = (title != null && !title.isEmpty()) ? title : "Untitled";
        this.year = (year > 0 && year <= 2100) ? year : -1;
        this.genre = (genre != null && !genre.isEmpty()) ? genre : "Unknown Genre";
        this.poster = (poster != null && !poster.isEmpty()) ? poster : "default_poster";
    }

    public String getTitle() { return title; }
    public int getYear() { return year; }
    public String getGenre() { return genre; }
    public String getPoster() { return poster; }
}



