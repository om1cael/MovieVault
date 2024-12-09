package com.om1cael;

import com.om1cael.enums.MovieStatus;
import com.om1cael.models.Movie;
import com.om1cael.storage.MoviesStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// TODO: Move input handling to another class
public class MovieVault {
    InputParser inputParser;
    MoviesStorage moviesStorage;

    public MovieVault() {
        moviesStorage = new MoviesStorage();
        inputParser = new InputParser(new Scanner(System.in));
    }

    public void addMovie() {
        System.out.println("Adding a new movie!");
        String movieTitle = inputParser.getTextInput(" - Title: ");
        String movieGenre = inputParser.getTextInput(" - Genre: ");
        int releaseYear = inputParser.getNumberInput(" - Release year: ", 1800, LocalDate.now().getYear());
        byte rating = (byte)inputParser.getNumberInput(" - Rating (0-5): ", 0, 5);
        int movieStatusChoice = inputParser.getNumberInput(
                " - Status (1 - Watched, 2 - To watch, 3 - Not interested in): ",
                1,
                3
        );

        MovieStatus movieStatus = switch(movieStatusChoice) {
            case 1 -> MovieStatus.WATCHED;
            case 2 -> MovieStatus.TO_WATCH;
            default -> MovieStatus.NO_INTEREST;
        };

        Movie movie = new Movie(0, movieTitle, movieGenre, releaseYear, rating, movieStatus);
        moviesStorage.addToMovies(movie);
        System.out.println(moviesStorage.getMovies());
    }

    public void editMovie() {}
    public void removeMovie() {}

    public void filterByGenre(String genre) {
         List<Movie> filteredMovies = moviesStorage.getMovies().stream().
                filter(movie -> movie.genre().equalsIgnoreCase(genre))
                .toList();

        System.out.println(filteredMovies);
    }

    public void filterByYear(int year) {
        List<Movie> filteredMovies = moviesStorage.getMovies().stream().
                filter(movie -> movie.releaseYear() == year)
                .toList();

        System.out.println(filteredMovies);
    }

    public void filterByRating(byte rating) {
        List<Movie> filteredMovies = moviesStorage.getMovies().stream().
                filter(movie -> movie.rating() == rating)
                .toList();

        System.out.println(filteredMovies);
    }
}