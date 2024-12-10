package com.om1cael.storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.om1cael.models.Movie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class StorageHandler {
    private final Path storagePath = Path.of("movies.json");

    public StorageHandler() {
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
            throw new RuntimeException(e);
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
            throw new RuntimeException("It was not possible to read the file.");
        }

        return movieList;
    }

    private void createJsonFile() {
        try {
            if(!storageFileExists())
                Files.createFile(storagePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean storageFileExists() {
        return Files.exists(storagePath);
    }
}
