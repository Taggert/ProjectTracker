package com.company.menu.items;

import com.company.core.LoginRequest;
import com.company.menu.InputOutput;
import com.company.menu.Item;
import com.company.menu.Menu;
import java.util.ArrayList;

public class Login extends Item{


    public Login(InputOutput inputOutput) {
        super(inputOutput);
    }

    public String displayedName() {
        return "Login";
    }

    public void perform() {

           if(LoginRequest.logIn()){
               System.out.println("Hi "+ LoginRequest.user.getFirstName() + " " + LoginRequest.user.getLastName());
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