package com.om1cael.model;

public enum ProgramActions {
    ADD_MOVIE("Add a new movie"),
    EDIT_MOVIE("Edit a movie"),
    REMOVE_MOVIE("Remove a movie"),
    FILTER_BY_GENRE("Filter by genre"),
    FILTER_BY_YEAR("Filter by year of release"),
    FILTER_BY_RATING("Filter by rating");

    private final String description;

    ProgramActions(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
