package com.om1cael.controller;

import com.om1cael.model.Movie;
import com.om1cael.services.MovieService;
import com.om1cael.services.MovieStorageService;
import com.om1cael.view.FeedbackView;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MovieController {
    private final MovieService movieService;
    private final InputParser inputParser;
    private final MovieStorageService movieStorageService;
    private final FeedbackView feedbackView;

    public MovieController() {
        this.movieStorageService = new MovieStorageService();
        this.inputParser = new InputParser(new Scanner(System.in));

        this.movieService = new MovieService();
        this.feedbackView = new FeedbackView();
    }

    public void addMovie() {
        System.out.println("Adding a new movie!");
        String movieTitle = this.inputParser.getTextInput(" - Title: ");
        String movieGenre = this.inputParser.getTextInput(" - Genre: ");
        int releaseYear = this.inputParser.getNumberInput(" - Release year: ", 1800, LocalDate.now().getYear(), false);
        byte rating = (byte)this.inputParser.getNumberInput(" - Rating (0-5): ", 0, 5, false);
        int movieStatusChoice = this.inputParser.getNumberInput(
                " - Status (1 - Watched, 2 - To watch, 3 - Not interested in): ",
                1,
                3,
                false
        );

        Movie movie = this.movieService.addMovie(
                movieTitle,
                movieGenre,
                releaseYear,
                rating,
                movieStatusChoice
        );

        feedbackView.printMovieAdded(movie);
    }

    public void listMovies() {
        List<Movie> movieList = this.movieStorageService.getMovies();
        if(movieList == null) {
            feedbackView.printNoMovies();
            return;
        }

        feedbackView.printMovieList(movieList);
    }

    public void editMovie() {}

    public void removeMovie() {
        int id = this.inputParser.getNumberInput("Movie ID: ", 0, Integer.MAX_VALUE, true);

        Movie removedMovie = this.movieService.removeMovie(id);
        if(removedMovie != null) {
            feedbackView.printMovieRemoved(removedMovie);
            return;
        }

        feedbackView.printMovieNotRemoved();
    }

    public void filterByGenre() {
        String genre = this.inputParser.getTextInput("Genre: ");
        List<Movie> filteredList = this.movieService.filterByGenre(genre);
        feedbackView.printFilterFeedback(filteredList,
                "List of movies with the genre of " + genre);
    }

    public void filterByYear() {
        int year = this.inputParser.getNumberInput("Year: ", 1800, LocalDate.now().getYear(), false);
        List<Movie> filteredList = this.movieService.filterByYear(year);
        feedbackView.printFilterFeedback(filteredList,
                "List of movies released in " + year);
    }

    public void filterByRating() {
        byte rating = (byte) this.inputParser.getNumberInput("Rating: ", 0, 5, false);
        List<Movie> filteredList = this.movieService.filterByRating(rating);
        feedbackView.printFilterFeedback(filteredList,
                "List of movies with rating of " + rating);
    }
}
