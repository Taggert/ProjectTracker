package com.company;

import com.company.menu.ConsoleInputOutput;
import com.company.menu.InputOutput;
import com.company.menu.Item;
import com.company.menu.Menu;
import com.company.menu.items.ExitItem;
import com.company.menu.items.Login;
import com.company.menu.items.Register;
import java.util.ArrayList;

public class App {

    private static InputOutput inputOutput = new ConsoleInputOutput();

    public static void main(String[] args) {
        ArrayList<Item> items = getItems();
        Menu menu = new Menu(items, inputOutput);
        menu.runMenu();

    }

    private static ArrayList<Item> getItems() {
        ArrayList<Item> res = new ArrayList<>();
        res.add(new Register(inputOutput));
        res.add(new Login(inputOutput));
        res.add(new ExitItem(inputOutput));


        return res;
    }

}