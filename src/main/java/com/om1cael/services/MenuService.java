package com.om1cael.services;

import com.om1cael.controller.MovieController;
import com.om1cael.model.ProgramActions;
import com.om1cael.controller.InputParser;

import java.util.HashMap;
import java.util.Scanner;

public class MenuService {
    private final InputParser inputParser;
    private final MovieController movieController;

    private final HashMap<Integer, ProgramActions> choices = new HashMap<>();

    public MenuService() {
        this.inputParser = new InputParser(new Scanner(System.in));
        this.movieController = new MovieController();

        this.populateChoices();
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
            case LIST_MOVIES -> movieController.listMovies();
            case LIST_MOVIES_WITH_API -> movieController.listMoviesWithAPI();
            case EDIT_MOVIE -> movieController.editMovie();
            case REMOVE_MOVIE -> movieController.removeMovie();
            case FILTER_BY_WORD -> movieController.filterByWord();
            case FILTER_BY_GENRE -> movieController.filterByGenre();
            case FILTER_BY_YEAR -> movieController.filterByYear();
            case FILTER_BY_RATING -> movieController.filterByRating();
            default -> System.out.println("Not a valid option.");
        }
    }

    private boolean isInputValid(int inputChoice) {
        return choices.containsKey(inputChoice);
    }

    private void populateChoices() {
        int index = 1;

        for(ProgramActions programAction : ProgramActions.values()) {
            choices.put(index, programAction);
            index++;
        }
    }

    public HashMap<Integer, ProgramActions> getChoices() {
        return choices;
    }
}
