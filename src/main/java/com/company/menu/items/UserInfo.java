package com.company.menu.items;

import com.company.menu.ConsoleInputOutput;
import com.company.model.Interfaces.*;
import com.company.menu.Item;
import com.company.menu.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserInfo extends Item implements UserInfoInt {
    private InputOutput inputOutput = new ConsoleInputOutput();

    @Autowired
    GetUserInt getUserInt;
    @Autowired
    UpdateUserInt updateUserInt;
    @Autowired
    AllUsersInt allUsersInt;
    public UserInfo() {
    }

    @Override
    public String displayedName() {
        return "User info";
    }

    @Override
    public void perform() {


        ArrayList<Item> items = getItems();
        Menu menu = new Menu(items, inputOutput);
        menu.runMenu();

    }

    private ArrayList<Item> getItems() {
        ArrayList<Item> res = new ArrayList<>();

        res.add((Item) getUserInt);
        res.add((Item) updateUserInt);
        res.add((Item) allUsersInt);
        res.add(new BackItem(inputOutput));


        return res;
    }
}