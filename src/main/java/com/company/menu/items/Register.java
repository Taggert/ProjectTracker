package com.company.menu.items;

import com.company.core.RegisterRequest;
import com.company.menu.InputOutput;
import com.company.menu.Item;
import com.company.menu.Menu;
import java.util.ArrayList;

public class Register extends Item {


    public Register(InputOutput inputOutput) {
        super(inputOutput);
    }

    @Override
    public String displayedName() {
        return "Register new user";
    }

    @Override
    public void perform() {

        if (RegisterRequest.getResponce()) {
            System.out.println("Hi "+ RegisterRequest.user.getFirstName() + " " + RegisterRequest.user.getLastName());
            ArrayList<Item> items = getItems();
            Menu menu = new Menu(items, inputOutput);
            menu.runMenu();
        }
    }

    private ArrayList<Item> getItems() {
        ArrayList<Item> res = new ArrayList<>();

        res.add(new UserInfo(inputOutput));
        res.add(new ItemManagement(inputOutput));
        res.add(new Logout(inputOutput));



        return res;
    }
}