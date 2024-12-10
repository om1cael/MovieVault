package com.om1cael.view;

import com.om1cael.services.MenuService;

public final class MenuView {
    private final MenuService menuService;

    public MenuView() {
        this.menuService = new MenuService();
    }

    public void getInitialInput() {
        this.showChoices();

        if(!this.menuService.handleInput()) {
            System.out.println("You choose an invalid option.");
        }
    }

    private void showChoices() {
        this.menuService.getChoices().forEach((actionNumber, action) -> {
            System.out.println("[" + actionNumber + "]: " + action.getDescription());
        });
    }
}

