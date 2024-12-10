package com.om1cael;

import com.om1cael.parsers.CommandParser;
import com.om1cael.parsers.InputParser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CommandParser commandParser = new CommandParser(new InputParser(new Scanner(System.in)), new MovieVault());
        commandParser.getInitialInput();
    }
}
