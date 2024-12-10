package com.om1cael.services;

import com.om1cael.model.MovieStatus;
import com.om1cael.model.Movie;
import com.om1cael.controller.InputParser;
import com.om1cael.controller.MovieStorageController;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

// TODO: This class should handle only the "frontend" of the program.
// TODO: Any logic here must be managed in another class.
public class MovieVault {
    private final InputParser inputParser;
    private final MovieStorageController movieStorageController;

    public MovieVault() {
        this.movieStorageController = new MovieStorageController();
        this.inputParser = new InputParser(new Scanner(System.in));
    }

    public void addMovie() {
        System.out.println("Adding a new movie!");
        String movieTitle = this.inputParser.getTextInput(" - Title: ");
        String movieGenre = this.inputParser.getTextInput(" - Genre: ");
        int releaseYear = this.inputParser.getNumberInput(" - Release year: ", 1800, LocalDate.now().getYear());
        byte rating = (byte)this.inputParser.getNumberInput(" - Rating (0-5): ", 0, 5);
        int movieStatusChoice = this.inputParser.getNumberInput(
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
        this.movieStorageController.addToMovies(movie);

        System.out.println("You added the movie " + movie.title());
    }

    public void editMovie() {}
    public void removeMovie() {}

    public void filterByGenre() {
        String genre = this.inputParser.getTextInput("Genre: ");
        List<Movie> filteredList = this.movieStorageController.getMovies().stream().
                filter(movie -> movie.genre().equalsIgnoreCase(genre))
                .toList();

        if(filteredList.isEmpty()) System.out.println("No movies with that genre.");
        else {
            System.out.println("List of movies with genre " + genre);
            filteredList.forEach(movie -> System.out.println(" - " + movie.title()));
        }
    }

    public void filterByYear() {
        int year = this.inputParser.getNumberInput("Year: ", 1800, LocalDate.now().getYear());
        List<Movie> filteredList = this.movieStorageController.getMovies().stream().
                filter(movie -> movie.releaseYear() == year)
                .toList();

        if(filteredList.isEmpty()) System.out.println("No movies released in that year.");
        else {
            System.out.println("List of movies released in that year (" + year + ")");
            filteredList.forEach(movie -> System.out.println(" - " + movie.title()));
        }
    }

    public void filterByRating() {
        byte rating = (byte) this.inputParser.getNumberInput("Rating: ", 0, 5);
        List<Movie> filteredList = this.movieStorageController.getMovies().stream().
                filter(movie -> movie.rating() == rating)
                .toList();

        if(filteredList.isEmpty()) System.out.println("No movies with that rating.");
        else {
            System.out.println("List of movies with " + rating + " rating");
            filteredList.forEach(movie -> System.out.println(" - " + movie.title()));
        }
    }
}
