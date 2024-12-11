package com.om1cael.controller;

import com.om1cael.services.InputParserService;

import java.util.Scanner;

public class InputParser {
    private final Scanner scanner;
    private final InputParserService inputParserService;

    public InputParser(Scanner scanner) {
        this.scanner = scanner;
        this.inputParserService = new InputParserService();
    }

    public String getTextInput(String message) {
        System.out.print(message);

        this.scanner.nextLine();
        return this.scanner.nextLine();
    }

    public int getNumberInput(String message, int min, int max, boolean receiveRaw) {
        System.out.print(message);
        int inputNumber = this.scanner.nextInt();

        return this.inputParserService.validateNumberInput(inputNumber, min, max, receiveRaw);
    }
}
