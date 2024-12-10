package com.om1cael;

import com.om1cael.controller.MovieController;
import com.om1cael.view.MenuView;
import com.om1cael.view.InputParser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MenuView menuView = new MenuView(new InputParser(new Scanner(System.in)), new MovieController());
        menuView.getInitialInput();
    }
}
