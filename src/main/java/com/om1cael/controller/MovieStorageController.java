package com.om1cael.controller;

import com.om1cael.model.Movie;

import java.util.List;

public class MovieStorageController {
    private final StorageController storageController;

    public MovieStorageController() {
        this.storageController = new StorageController();
    }

    public void addToMovies(Movie movie) {
        this.storageController.saveToFile(movie);
    }

    public List<Movie> getMovies() {
        return this.storageController.readFromFile();
    }
}
