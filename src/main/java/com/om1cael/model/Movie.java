package com.om1cael.model;

public record Movie(int id, String title, String genre, int releaseYear, byte rating, MovieStatus status) { }

