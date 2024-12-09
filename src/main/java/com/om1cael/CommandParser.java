package com.om1cael;

import com.om1cael.enums.ProgramActions;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public final class CommandParser {
    HashMap<Integer, ProgramActions> choices = new HashMap<>(Map.of(
            1, ProgramActions.ADD_MOVIE,
            2, ProgramActions.EDIT_MOVIE,
            3, ProgramActions.REMOVE_MOVIE,
            4, ProgramActions.FILTER_BY_GENRE,
            5, ProgramActions.FILTER_BY_YEAR,
            6, ProgramActions.FILTER_BY_RATING
    ));

    private final MovieVault movieVault;

    public CommandParser() {
        movieVault = new MovieVault();
    }

    public void getInitialInput() {
        Scanner scanner = new Scanner(System.in);
        showChoices();
        handleInput(scanner);
    }

    private void handleInput(Scanner scanner) {
        System.out.print("Your choice: ");
        final int inputChoice = scanner.nextInt();

        if (isInputInvalid(inputChoice)) return;

        switch (choices.get(inputChoice)) {
            case ADD_MOVIE -> movieVault.addMovie();
            case EDIT_MOVIE -> System.out.println("Edit a movie");
            case REMOVE_MOVIE -> System.out.println("Remove a movie");
            case FILTER_BY_GENRE -> System.out.println("Filter by genre");
            case FILTER_BY_YEAR -> System.out.println("Filter by year");
            case FILTER_BY_RATING -> System.out.println("Filter by rating");
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
        choices.forEach((k, v) -> {
            System.out.println("[" + k + "]: " + v.getDescription());
        });
    }
}

