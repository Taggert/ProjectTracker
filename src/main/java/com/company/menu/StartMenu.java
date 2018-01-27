package com.company.menu;

import com.company.menu.items.ExitItem;
import com.company.model.Interfaces.LoginInt;
import com.company.model.Interfaces.RegisterInt;
import com.company.model.Interfaces.InputOutput;
import com.company.model.Interfaces.StartMenuInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StartMenu implements StartMenuInt {

    private static InputOutput inputOutput = new ConsoleInputOutput();
    @Autowired
    RegisterInt registerInt;
    @Autowired
    LoginInt loginInt;

    @Override
    public void startMenu() {
        ArrayList<Item> items = getItems();
        Menu menu = new Menu(items, inputOutput);
        menu.runMenu();
    }

    private ArrayList<Item> getItems() {
        ArrayList<Item> res = new ArrayList<>();
        res.add((Item) registerInt);
        res.add((Item) loginInt);
        res.add(new ExitItem(inputOutput));


        return res;
    }
}