package com.om1cael;

import com.om1cael.enums.MovieStatus;
import com.om1cael.models.Movie;
import com.om1cael.storage.MoviesStorage;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

// TODO: Move input handling to another class
public class MovieVault {
    private final InputParser inputParser;
    private final MoviesStorage moviesStorage;

    public MovieVault() {
        this.moviesStorage = new MoviesStorage();
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
        this.moviesStorage.addToMovies(movie);
    }

    public void editMovie() {}
    public void removeMovie() {}

    public List<Movie> filterByGenre() {
        String genre = this.inputParser.getTextInput("Genre: ");

        return this.moviesStorage.getMovies().stream().
                filter(movie -> movie.genre().equalsIgnoreCase(genre))
                .toList();
    }

    public List<Movie> filterByYear() {
        int year = this.inputParser.getNumberInput("Year: ", 1800, LocalDate.now().getYear());

        return this.moviesStorage.getMovies().stream().
                filter(movie -> movie.releaseYear() == year)
                .toList();
    }

    public List<Movie> filterByRating() {
        byte rating = (byte) this.inputParser.getNumberInput("Rating: ", 0, 5);

        return this.moviesStorage.getMovies().stream().
                filter(movie -> movie.rating() == rating)
                .toList();
    }
}
