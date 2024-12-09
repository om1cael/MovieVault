package com.om1cael.models;

import com.om1cael.enums.MovieStatus;

public record Movie(int id, String title, String genre, int releaseYear, byte rating, MovieStatus status) {}

