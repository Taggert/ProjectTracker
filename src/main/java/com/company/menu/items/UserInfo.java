package com.company.menu.items;

import com.company.menu.InputOutput;
import com.company.menu.Item;
import com.company.menu.Menu;
import java.util.ArrayList;

public class UserInfo extends Item {


    public UserInfo(InputOutput inputOutput) {
        super(inputOutput);
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

        res.add(new GetUser(inputOutput));
        res.add(new UpdateUser(inputOutput));
        res.add(new AllUsers(inputOutput));
        res.add(new BackItem(inputOutput));



        return res;
    }
}