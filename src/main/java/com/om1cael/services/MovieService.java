package com.om1cael.services;

import com.om1cael.model.MovieStatus;
import com.om1cael.model.Movie;

import java.util.ArrayList;
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

        Movie movie = new Movie(retryMovieID(), movieTitle, movieGenre, releaseYear, rating, movieStatus);
        this.movieStorageService.addToMovies(movie);
        return movie;
    }

    public Movie editMovie(int id, int field, String content) {
        List<Movie> movieList = this.movieStorageService.getMovies();
        if(movieList == null || id > movieList.size() || movieList.get(id) == null) return null;
        Movie movie = movieList.get(id);

        switch(field) {
            case 0 -> movie.setTitle(content);
            case 1 -> movie.setGenre(content);
            case 2 -> movie.setReleaseYear(Integer.parseInt(content));
            case 3 -> movie.setRating(Byte.parseByte(content));
            case 4 -> movie.setStatus(mapMovieStatus(content));
        }

        movieList.set(id, movie);
        this.movieStorageService.addToMovies(movieList);
        return movie;
    }

    private MovieStatus mapMovieStatus(String content) {
        int contentIndex = Integer.parseInt(content);
        MovieStatus movieStatus;

        switch(contentIndex) {
            case 0 -> movieStatus = MovieStatus.WATCHED;
            case 1 -> movieStatus = MovieStatus.TO_WATCH;
            default -> movieStatus = MovieStatus.NO_INTEREST;
        }

        return movieStatus;
    }

    public Movie removeMovie(int id) {
        List<Movie> movieList = this.movieStorageService.getMovies();
        if(movieList == null || id > movieList.size()) return null;

        if(movieList.get(id) != null) {
            Movie movie = movieList.get(id);
            movieList.remove(id);

            movieList = assignIDAfterRemoval(movieList);
            this.movieStorageService.addToMovies(movieList);
            return movie;
        }

        return null;
    }

    private List<Movie> assignIDAfterRemoval(List<Movie> movieList) {
        List<Movie> modifiedMovieList = new ArrayList<>();

        int index = 0;
        for(Movie movieInList : movieList) {
            movieInList.setId(index);
            index++;

            modifiedMovieList.add(movieInList);
        }

        return modifiedMovieList;
    }

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

    private int retryMovieID() {
        List<Movie> movieList = this.movieStorageService.getMovies();

        if(movieList == null) {
            return 0;
        } else {
            return movieList.size();
        }
    }
}
