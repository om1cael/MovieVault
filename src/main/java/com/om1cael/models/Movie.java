package com.om1cael.models;

import com.om1cael.MovieStatus;

public record Movie(String title, String genre, int releaseYear, byte rating, MovieStatus status) {}

