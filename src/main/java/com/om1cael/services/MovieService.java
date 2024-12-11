package com.om1cael.services;

import com.om1cael.model.APIMovie;
import com.om1cael.model.MovieStatus;
import com.om1cael.model.Movie;

import java.util.List;

public class MovieService {
    private final StorageService storageService;

    public MovieService() {
        this.storageService = new StorageService();
    }

    public Movie addMovie(String movieTitle,
                         String movieGenre,
                         int releaseYear,
                         byte rating,
                         int movieStatusChoice
    ) {

        Movie movie = new Movie(retryMovieID(), movieTitle, movieGenre, releaseYear, rating, mapMovieStatus(String.valueOf(movieStatusChoice)));
        this.storageService.saveToFile(movie);
        return movie;
    }

    public APIMovie getMovieWithAPI(String title) {
        APIService apiService = new APIService();
        return apiService.mapToJson(title).join();
    }

    public Movie editMovie(int id, int field, String content) {
        List<Movie> movieList = this.storageService.readFromFile();
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
        this.storageService.saveToFile(movieList);
        return movie;
    }

    public Movie removeMovie(int id) {
        List<Movie> movieList = this.storageService.readFromFile();
        if(movieList == null || movieList.isEmpty() || id > movieList.size()) return null;


        if(movieList.get(id) != null) {
            Movie movie = movieList.get(id);
            movieList.remove(id);

            assignIDAfterRemoval(movieList);

            this.storageService.saveToFile(movieList);
            return movie;
        }

        return null;
    }

    public List<Movie> filterByWord(String word) {
        return this.storageService.readFromFile().stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(word.toLowerCase()))
                .toList();
    }

    public List<Movie> filterByGenre(String genre) {
        return this.storageService.readFromFile().stream()
                .filter(movie -> movie.getGenre().equalsIgnoreCase(genre))
                .toList();
    }

    public List<Movie> filterByYear(int year) {
        return this.storageService.readFromFile().stream()
                .filter(movie -> movie.getReleaseYear() == year)
                .toList();
    }

    public List<Movie> filterByRating(byte rating) {
        return this.storageService.readFromFile().stream()
                .filter(movie -> movie.getRating() == rating)
                .toList();
    }

    private MovieStatus mapMovieStatus(String content) {
        int contentIndex = Integer.parseInt(content);

        return switch(contentIndex) {
            case 0 -> MovieStatus.WATCHED;
            case 1 -> MovieStatus.TO_WATCH;
            default -> MovieStatus.NO_INTEREST;
        };
    }

    private void assignIDAfterRemoval(List<Movie> movieList) {
        int index = 0;

        for(Movie movieInList : movieList) {
            movieInList.setId(index);
            movieList.set(index, movieInList);
            index++;
        }
    }

    private int retryMovieID() {
        List<Movie> movieList = this.storageService.readFromFile();

        if(movieList == null) {
            return 0;
        } else {
            return movieList.size();
        }
    }
}
