package com.om1cael.services;

import com.om1cael.model.MovieStatus;
import com.om1cael.model.Movie;

import java.util.List;

public class MovieService {
    private final MovieStorageService movieStorageService;

    public MovieService() {
        this.movieStorageService = new MovieStorageService();
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
        this.movieStorageService.addToMovies(movie);
        return movie;
    }

    public void editMovie() {}
    public void removeMovie() {}

    public List<Movie> filterByGenre(String genre) {
        return this.movieStorageService.getMovies().stream().
                filter(movie -> movie.getGenre().equalsIgnoreCase(genre))
                .toList();
    }

    public List<Movie> filterByYear(int year) {
        return this.movieStorageService.getMovies().stream().
                filter(movie -> movie.getReleaseYear() == year)
                .toList();
    }

    public List<Movie> filterByRating(byte rating) {
        return this.movieStorageService.getMovies().stream().
                filter(movie -> movie.getRating() == rating)
                .toList();
    }
}
