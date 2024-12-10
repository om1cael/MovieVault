package com.om1cael.storage;

import com.om1cael.models.Movie;

import java.util.ArrayList;
import java.util.List;

// TODO: The content must be loaded from a JSON or text file.
public class MoviesStorage {
    StorageHandler storageHandler;

    public MoviesStorage() {
        this.storageHandler = new StorageHandler();
    }

    public void addToMovies(Movie movie) {
        this.storageHandler.saveToFile(movie);
    }

    public List<Movie> getMovies() {
        return this.storageHandler.readFromFile();
    }
}
