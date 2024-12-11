package com.om1cael.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.om1cael.model.Movie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class StorageService {
    private final Path storagePath = Path.of("movies.json");

    public StorageService() {
        this.createJsonFile();
    }

    public void saveToFile(Movie movieToAdd) {
        Gson gson = new Gson();
        List<Movie> movieList = readFromFile();

        try(BufferedWriter writer = Files.newBufferedWriter(storagePath);
            JsonWriter jsonWriter = new JsonWriter(writer)
        ) {
            jsonWriter.beginArray();

            if(movieList != null) {
                for(Movie movie : movieList) {
                    gson.toJson(movie, Movie.class, jsonWriter);
                }
            }

            gson.toJson(movieToAdd, Movie.class, jsonWriter);
            jsonWriter.endArray();
        } catch (IOException e) {
            throw new RuntimeException("It was not possible to save the movies into the storage");
        }
    }

    public void saveToFile(List<Movie> moviesToAdd) {
        Gson gson = new Gson();

        try(BufferedWriter writer = Files.newBufferedWriter(storagePath);
            JsonWriter jsonWriter = new JsonWriter(writer)
        ) {
            jsonWriter.beginArray();

            if(moviesToAdd != null) {
                for(Movie movie : moviesToAdd) {
                    gson.toJson(movie, Movie.class, jsonWriter);
                }
            }

            jsonWriter.endArray();
        } catch (IOException e) {
            throw new RuntimeException("It was not possible to save the movies into the storage");
        }
    }

    public List<Movie> readFromFile() {
        Gson gson = new Gson();
        List<Movie> movieList;

        try(BufferedReader bufferedReader = Files.newBufferedReader(storagePath);
            JsonReader jsonReader = new JsonReader(bufferedReader);
        ) {
            TypeToken<List<Movie>> movieListType = new TypeToken<>(){};
            movieList = gson.fromJson(jsonReader, movieListType);
        } catch (IOException e) {
            throw new RuntimeException("It was not possible to read the movie storage.");
        }

        return movieList;
    }

    private void createJsonFile() {
        try {
            if(!Files.exists(storagePath))
                Files.createFile(storagePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
