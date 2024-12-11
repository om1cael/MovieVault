package com.om1cael.model;

public enum ProgramActions {
    ADD_MOVIE("Add a new movie"),
    LIST_MOVIES("List your movies with your own info"),
    LIST_MOVIES_WITH_API("List your movies with the OMDB API info"),
    EDIT_MOVIE("Edit a movie"),
    REMOVE_MOVIE("Remove a movie"),
    FILTER_BY_WORD("Filter by word"),
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
