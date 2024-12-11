package com.om1cael.services;

import com.google.gson.Gson;
import com.om1cael.model.APIMovie;

import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class APIService {

    private URI apiEndpoint;
    private final Duration apiFetchTimeout = Duration.ofSeconds(10);
    private final String apiKey = System.getenv("OMDB_API_KEY");

    public APIService() {
        if(apiKey == null || apiKey.isEmpty())
            throw new IllegalStateException("Please, define the API key!");

        this.apiEndpoint = URI.create("http://www.omdbapi.com/?apikey=" + this.apiKey + "&t=");
    }

    public CompletableFuture<String> fetchAPI(String movieTitle) {
        movieTitle = getFormattedMovieTitle(movieTitle);
        URI apiMovieEndPoint = URI.create(this.apiEndpoint + movieTitle);

        try(HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpRequest httpRequest = HttpRequest
                    .newBuilder()
                    .GET()
                    .timeout(apiFetchTimeout)
                    .uri(apiMovieEndPoint)
                    .build();

            CompletableFuture<HttpResponse<String>> futureResponse =
                    httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());

            return futureResponse.thenApply(response -> {
                if(response.statusCode() != 200) {
                    throw new RuntimeException("The API returned an error code!");
                }

                return response.body();
            }).exceptionally(e -> {
                System.err.println("An error occurred while parsing your movies: " + e.getMessage());
                return "[]";
            });
        } catch (UncheckedIOException e) {
            throw new RuntimeException("Could not create the HTTP Client: " + e.getMessage());
        }
    }

    public CompletableFuture<APIMovie> mapToJson(String title) {
        Gson gson = new Gson();
        return this.fetchAPI(title)
                .thenApply(response -> gson.fromJson(response, APIMovie.class))
                .exceptionally(e -> {
                    System.err.println("An error occurred while preparing your movies data: " + e.getMessage());
                    return null;
                });
    }

    private String getFormattedMovieTitle(String movieTitle) {
        if(movieTitle.contains(" ")) {
            movieTitle = movieTitle.replaceAll(" ", "+");
        }

        return movieTitle.toLowerCase();
    }
}
