package com.om1cael.controller;

import java.util.Scanner;

public class InputParser {
    private final Scanner scanner;

    public InputParser(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getTextInput() {
        return this.scanner.nextLine();
    }

    public int getNumberInput(int min, int max) {
        int inputNumber = this.scanner.nextInt();

        if(inputNumber >= min && inputNumber <= max) {
            return inputNumber;
        }

        return inputNumber < min ? min : max;
    }
}
