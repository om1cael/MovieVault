package com.om1cael;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public final class CommandParser {
    HashMap<Integer, Actions> choices = new HashMap<>(Map.of(
            1, Actions.ADD_MOVIE,
            2, Actions.REMOVE_MOVIE
    ));

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
            case ADD_MOVIE -> System.out.println("You choose to add a movie");
            case REMOVE_MOVIE -> System.out.println("You choose to remove a movie");
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

enum Actions {
    ADD_MOVIE("Add a new movie"),
    REMOVE_MOVIE("Remove a movie");

    private final String description;

    Actions(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}