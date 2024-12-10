package com.om1cael;

import com.om1cael.parsers.CommandParser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CommandParser commandParser = new CommandParser(new Scanner(System.in));
        commandParser.getInitialInput();
    }
}
