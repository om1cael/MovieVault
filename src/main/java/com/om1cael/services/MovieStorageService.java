package com.om1cael.services;

import com.om1cael.model.Movie;

import java.util.List;

public class MovieStorageService {
    private final StorageService storageService;

    public MovieStorageService() {
        this.storageService = new StorageService();
    }

    public void addToMovies(Movie movie) {
        this.storageService.saveToFile(movie);
    }

    public List<Movie> getMovies() {
        return this.storageService.readFromFile();
    }


}
