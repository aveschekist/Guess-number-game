package org.nesteruk;

import org.nesteruk.menu.Menu;
import org.nesteruk.menu.MenuConsole;


public class Main {
    public static void main(String[] args) {
        Menu menu = new MenuConsole();
        menu.startMenu();
    }
}
