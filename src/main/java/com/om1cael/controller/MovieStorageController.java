package com.om1cael.controller;

import com.om1cael.model.Movie;
import com.om1cael.services.StorageService;

import java.util.List;

// TODO: It may be a controller in the future.
public class MovieStorageController {
    private final StorageService storageService;

    public MovieStorageController() {
        this.storageService = new StorageService();
    }

    public void addToMovies(Movie movie) {
        this.storageService.saveToFile(movie);
    }

    public List<Movie> getMovies() {
        return this.storageService.readFromFile();
    }
}
