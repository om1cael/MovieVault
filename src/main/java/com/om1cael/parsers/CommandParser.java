package com.om1cael.parsers;

import com.om1cael.MovieVault;
import com.om1cael.enums.ProgramActions;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public final class CommandParser {
    private final HashMap<Integer, ProgramActions> choices = new HashMap<>(Map.of(
            1, ProgramActions.ADD_MOVIE,
            2, ProgramActions.EDIT_MOVIE,
            3, ProgramActions.REMOVE_MOVIE,
            4, ProgramActions.FILTER_BY_GENRE,
            5, ProgramActions.FILTER_BY_YEAR,
            6, ProgramActions.FILTER_BY_RATING
    ));

    private final InputParser inputParser;
    private final MovieVault movieVault;

    public CommandParser(InputParser inputParser, MovieVault movieVault) {
        this.inputParser = inputParser;
        this.movieVault = movieVault;
    }

    public void getInitialInput() {
        showChoices();
        handleInput();
    }

    private void handleInput() {
        final int inputChoice = inputParser.getNumberInput(
                "Your choice: ",
                1,
                choices.size());

        if (isInputInvalid(inputChoice)) return;
        handleActions(inputChoice);
    }

    private void handleActions(int inputChoice) {
        switch (choices.get(inputChoice)) {
            case ADD_MOVIE -> movieVault.addMovie();
            case EDIT_MOVIE -> movieVault.editMovie();
            case REMOVE_MOVIE -> movieVault.removeMovie();
            case FILTER_BY_GENRE -> movieVault.filterByGenre();
            case FILTER_BY_YEAR -> movieVault.filterByYear();
            case FILTER_BY_RATING -> movieVault.filterByRating();
            default -> System.out.println("Not a valid option.");
        }
    }

    private boolean isInputInvalid(int inputChoice) {
        if(!choices.containsKey(inputChoice)) {
            System.out.println("This option is not valid.");
            return true;
        }
        return false;
    }

    private void showChoices() {
        this.choices.forEach((actionNumber, action) -> {
            System.out.println("[" + actionNumber + "]: " + action.getDescription());
        });
    }
}

