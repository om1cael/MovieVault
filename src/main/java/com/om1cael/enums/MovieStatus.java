package com.om1cael.enums;

public enum MovieStatus {
    WATCHED("Watched"),
    TO_WATCH("To Watch"),
    NO_INTEREST("No Interest");

    private final String description;

    MovieStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
