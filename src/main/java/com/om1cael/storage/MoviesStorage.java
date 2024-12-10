package com.om1cael.storage;

import com.om1cael.models.Movie;

import java.util.ArrayList;

// TODO: The content must be loaded from a JSON or text file.
public class MoviesStorage {
    ArrayList<Movie> movies = new ArrayList<>();

    public void addToMovies(Movie movie) {
        this.movies.add(movie);
    }

    public ArrayList<Movie> getMovies() {
        return this.movies;
    }
}
