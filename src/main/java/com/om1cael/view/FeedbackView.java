package com.om1cael.view;

import com.om1cael.model.APIMovie;
import com.om1cael.model.Movie;

import java.util.List;

public class FeedbackView {
    public void printMovieAdded(Movie movie) {
        System.out.println("You added the movie: " + movie.getTitle());
    }

    public void printMovieRemoved(Movie movie) {
        System.out.println("You removed the movie: " + movie.getTitle());
    }

    public void printMovieNotRemoved() {
        System.out.println("The movie was not found.");
    }

    public void printMovieList(List<Movie> movieList) {
        System.out.println("Your movies: ");
        movieList.forEach(movie -> {
            System.out.println();
            System.out.println(movie.getTitle() + " [ID " + movie.getId() + "]:");
            System.out.println(" - Genre: " + movie.getGenre());
            System.out.println(" - Year of release: " + movie.getReleaseYear());
            System.out.println(" - Your rating: " + movie.getRating());
            System.out.println(" - Status: " + movie.getStatus().getDescription());
        });
    }

    public void printAPIMovie(APIMovie apiMovie) {
        System.out.println();
        System.out.println(apiMovie.title() + ":");
        System.out.println(" - Year(s): " + apiMovie.year());
        System.out.println(" - Date of release: " + apiMovie.released());
        System.out.println(" - Genre(s): " + apiMovie.genre());
        System.out.println(" - Plot: " + apiMovie.plot());
        System.out.println(" - IMDB Rating: " + apiMovie.imdbRating());
    }

    public void printNoMovies() {
        System.out.println("You did not add any movie.");
    }

    public void printSuccessfulEdit(int field, Movie movie) {
        switch(field) {
            case 0 -> System.out.println("Title set to " + movie.getTitle());
            case 1 -> System.out.println("Genre set to " + movie.getGenre());
            case 2 -> System.out.println("Release year set to " + movie.getReleaseYear());
            case 3 -> System.out.println("Rating set to " + movie.getRating());
            case 4 -> System.out.println("Status set to " + movie.getStatus().getDescription());
            default -> System.out.println("No editing was made.");
        }
    }

    public void printNoEdit() {
        System.out.println("It was not possible to edit the movie.");
    }

    public void printFilterFeedback(List<Movie> filteredList,
                                    String successfulMessage
    ) {
        if(filteredList.isEmpty()) System.out.println("Could not find any movie with this filter.");
        else {
            System.out.println(successfulMessage);
            filteredList.forEach(movie -> System.out.println(" - " + movie.getTitle()));
        }
    }
}
