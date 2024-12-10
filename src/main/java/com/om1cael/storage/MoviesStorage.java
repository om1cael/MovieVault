package com.om1cael.storage;

import com.om1cael.enums.MovieStatus;
import com.om1cael.models.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
