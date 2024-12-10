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
