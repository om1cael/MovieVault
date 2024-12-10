package com.om1cael.services;

import com.om1cael.model.MovieStatus;
import com.om1cael.model.Movie;
import com.om1cael.controller.MovieStorageController;

import java.util.List;

public class MovieService {
    private final MovieStorageController movieStorageController;

    public MovieService() {
        this.movieStorageController = new MovieStorageController();
    }

    public Movie addMovie(String movieTitle,
                         String movieGenre,
                         int releaseYear,
                         byte rating,
                         int movieStatusChoice
    ) {
        MovieStatus movieStatus = switch(movieStatusChoice) {
            case 1 -> MovieStatus.WATCHED;
            case 2 -> MovieStatus.TO_WATCH;
            default -> MovieStatus.NO_INTEREST;
        };

        Movie movie = new Movie(movieTitle, movieGenre, releaseYear, rating, movieStatus);
        this.movieStorageController.addToMovies(movie);
        return movie;
    }

    public void editMovie() {}
    public void removeMovie() {}

    public List<Movie> filterByGenre(String genre) {
        return this.movieStorageController.getMovies().stream().
                filter(movie -> movie.getGenre().equalsIgnoreCase(genre))
                .toList();
    }

    public List<Movie> filterByYear(int year) {
        return this.movieStorageController.getMovies().stream().
                filter(movie -> movie.getReleaseYear() == year)
                .toList();
    }

    public List<Movie> filterByRating(byte rating) {
        return this.movieStorageController.getMovies().stream().
                filter(movie -> movie.getRating() == rating)
                .toList();
    }
}
