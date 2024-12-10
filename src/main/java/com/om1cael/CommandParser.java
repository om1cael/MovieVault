package com.om1cael;

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

    private final Scanner scanner;
    private final MovieVault movieVault;

    public CommandParser(Scanner scanner) {
        this.movieVault = new MovieVault();
        this.scanner = scanner;
    }

    public void getInitialInput() {
        showChoices();
        handleInput();
    }

    private void handleInput() {
        System.out.print("Your choice: ");
        final int inputChoice = this.scanner.nextInt();

        if (isInputInvalid(inputChoice)) return;

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
        this.choices.forEach((k, v) -> {
            System.out.println("[" + k + "]: " + v.getDescription());
        });
    }
}

