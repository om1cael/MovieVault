package com.om1cael.view;

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
            System.out.println(movie.getTitle());
            System.out.println(" - Genre: " + movie.getGenre());
            System.out.println(" - Year of release: " + movie.getReleaseYear());
            System.out.println(" - Your rating: " + movie.getRating());
            System.out.println(" - Status: " + movie.getStatus().getDescription());
            System.out.println();
        });
    }

    public void printNoMovies() {
        System.out.println("You did not add any movie.");
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
