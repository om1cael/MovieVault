package com.om1cael.view;

import java.util.Scanner;

public class InputParser {
    private final Scanner scanner;

    public InputParser(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getTextInput(String message) {
        System.out.print(message);
        return this.scanner.nextLine();
    }

    public int getNumberInput(String message, int min, int max) {
        System.out.print(message);
        int inputNumber = this.scanner.nextInt();

        if(inputNumber >= min && inputNumber <= max) {
            return inputNumber;
        }

        return inputNumber < min ? min : max;
    }
}
