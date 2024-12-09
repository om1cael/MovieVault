package com.om1cael;

import com.om1cael.commands.AddMovie;
import com.om1cael.enums.MovieStatus;
import com.om1cael.models.Movie;
import com.om1cael.storage.MoviesStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// TODO: Move input handling to another class
public class MovieVault {
    MoviesStorage moviesStorage;
    ArrayList<Movie> movies;

    public MovieVault() {
        moviesStorage = new MoviesStorage();
        this.movies = moviesStorage.getMovies();
    }

    public void addMovie() {
        //moviesStorage.addToMovies(movie);
        System.out.println(moviesStorage.getMovies());
    }

    public void editMovie() {}
    public void removeMovie() {}

    public void filterByGenre(String genre) {
         List<Movie> filteredMovies = movies.stream().
                filter(movie -> movie.genre().equalsIgnoreCase(genre))
                .toList();

        System.out.println(filteredMovies);
    }

    public void filterByYear(int year) {
        List<Movie> filteredMovies = movies.stream().
                filter(movie -> movie.releaseYear() == year)
                .toList();

        System.out.println(filteredMovies);
    }

    public void filterByRating(byte rating) {
        List<Movie> filteredMovies = movies.stream().
                filter(movie -> movie.rating() == rating)
                .toList();

        System.out.println(filteredMovies);
    }
}
