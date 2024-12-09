package com.om1cael;

import com.om1cael.enums.MovieStatus;
import com.om1cael.models.Movie;
import com.om1cael.storage.MoviesStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// TODO: Move input handling to another class
public class MovieVault {
    MoviesStorage moviesStorage;
    ArrayList<Movie> movies = new ArrayList<>();

    public MovieVault() {
        moviesStorage = new MoviesStorage();
        this.movies = moviesStorage.getMovies();
    }

    public void addMovie() {
        Scanner scanner = new Scanner(System.in);

        String movieTitle, movieGenre;
        int movieReleaseYear, movieStatus;
        byte movieRating;

        System.out.println("Adding a new movie!");
        System.out.print(" - Title: ");
        movieTitle = scanner.nextLine();
        System.out.print(" - Genre: ");
        movieGenre = scanner.next();
        System.out.print(" - Release year: ");
        movieReleaseYear = scanner.nextInt();
        System.out.print(" - Choose [1 - Watched, 2 - Planning to watch, 3 - Not interested]: ");
        movieStatus = scanner.nextInt();
        System.out.print(" - Rating (0 - 5): ");
        movieRating = scanner.nextByte();

        MovieStatus movieStatusEnum = MovieStatus.WATCHED;

        switch (movieStatus) {
            case 2 -> movieStatusEnum = MovieStatus.TO_WATCH;
            case 3 -> movieStatusEnum = MovieStatus.NO_INTEREST;
        }

        Movie movie = new Movie(0, movieTitle, movieGenre, movieReleaseYear, movieRating, movieStatusEnum);
        moviesStorage.addToMovies(movie);
        System.out.println(moviesStorage.getMovies());
    }

    public void editMovie() {}
    public void removeMovie() {}

    public void filterByGenre(String genre) {
         List<Movie> filteredMovies = movies.stream().
                filter(movie -> movie.genre().equalsIgnoreCase(genre))
                .toList();

        System.out.println(filteredMovies);
    }

    public void filterByYear(int year) {
        List<Movie> filteredMovies = movies.stream().
                filter(movie -> movie.releaseYear() == year)
                .toList();

        System.out.println(filteredMovies);
    }

    public void filterByRating(byte rating) {
        List<Movie> filteredMovies = movies.stream().
                filter(movie -> movie.rating() == rating)
                .toList();

        System.out.println(filteredMovies);
    }
}
