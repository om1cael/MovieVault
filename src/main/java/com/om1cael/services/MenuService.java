package com.om1cael.services;

import com.om1cael.controller.MovieController;
import com.om1cael.model.ProgramActions;
import com.om1cael.view.InputParser;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuService {
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

    public MenuService() {
        this.inputParser = new InputParser(new Scanner(System.in));
        this.movieController = new MovieController();
    }

    public boolean handleInput() {
        final int inputChoice = inputParser.getNumberInput("Your choice: ", 1, choices.size(), true);
        if(!isInputValid(inputChoice)) return false;

        handleActions(inputChoice);
        return true;
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

    private boolean isInputValid(int inputChoice) {
        return choices.containsKey(inputChoice);
    }

    public HashMap<Integer, ProgramActions> getChoices() {
        return choices;
    }
}
