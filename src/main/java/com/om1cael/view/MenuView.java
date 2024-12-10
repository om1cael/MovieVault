package com.om1cael.view;

import com.om1cael.controller.MovieController;
import com.om1cael.model.ProgramActions;

import java.util.HashMap;
import java.util.Map;

public final class MenuView {
    private final InputParser inputParser;
    private final MovieController movieController;

    private final HashMap<Integer, ProgramActions> choices = new HashMap<>(Map.of(
            1, ProgramActions.ADD_MOVIE,
            2, ProgramActions.EDIT_MOVIE,
            3, ProgramActions.REMOVE_MOVIE,
            4, ProgramActions.FILTER_BY_GENRE,
            5, ProgramActions.FILTER_BY_YEAR,
            6, ProgramActions.FILTER_BY_RATING
    ));

    public MenuView(InputParser inputParser, MovieController movieController) {
        this.inputParser = inputParser;
        this.movieController = movieController;
    }

    public void getInitialInput() {
        showChoices();
        handleInput();
    }

    private void handleInput() {
        final int inputChoice = inputParser.getNumberInput("Your choice: ", 1, choices.size());

        if (isInputInvalid(inputChoice)) return;
        handleActions(inputChoice);
    }

    private void handleActions(int inputChoice) {
        switch (choices.get(inputChoice)) {
            case ADD_MOVIE -> movieController.addMovie();
            case EDIT_MOVIE -> movieController.editMovie();
            case REMOVE_MOVIE -> movieController.removeMovie();
            case FILTER_BY_GENRE -> movieController.filterByGenre();
            case FILTER_BY_YEAR -> movieController.filterByYear();
            case FILTER_BY_RATING -> movieController.filterByRating();
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

