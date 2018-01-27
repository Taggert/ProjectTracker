package com.company.menu.items;

import com.company.model.Interfaces.RegisterRequestInt;
import com.company.menu.ConsoleInputOutput;
import com.company.model.Interfaces.*;
import com.company.menu.Item;
import com.company.menu.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class Register extends Item implements RegisterInt {

    @Autowired
    RegisterRequestInt registerRequestInt;
    @Autowired
    UserInfoInt userInfoInt;
    @Autowired
    ItemManagementInt itemManagementInt;
    @Autowired
    LogoutInt logoutInt;
    private InputOutput inputOutput = new ConsoleInputOutput();

    public Register() {
    }

    @Override
    public String displayedName() {
        return "Register new user";
    }

    @Override
    public void perform() {
        if (registerRequestInt.getResponce()) {
            System.out.println("Hi "+ registerRequestInt.getUser().getFirstName() + " " + registerRequestInt.getUser().getLastName());
            ArrayList<Item> items = getItems();
            Menu menu = new Menu(items, inputOutput);
            menu.runMenu();
        }
    }

    private ArrayList<Item> getItems() {
        ArrayList<Item> res = new ArrayList<>();

        res.add((Item) userInfoInt);
        res.add((Item) itemManagementInt);
        res.add((Item) logoutInt);



        return res;
    }
}