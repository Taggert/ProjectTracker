package com.company.menu.items;

import com.company.model.Interfaces.LoginRequestInt;
import com.company.menu.ConsoleInputOutput;
import com.company.model.Interfaces.*;
import com.company.menu.Item;
import com.company.menu.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class Login extends Item implements LoginInt {

    @Autowired
    LoginRequestInt loginRequestInt;
    @Autowired
    UserInfoInt userInfoInt;
    @Autowired
    ItemManagementInt itemManagementInt;
    @Autowired
    LogoutInt logoutInt;

    private InputOutput inputOutput = new ConsoleInputOutput();

    public Login() {

    }

    public String displayedName() {
        return "Login";
    }

    public void perform() {

           if(loginRequestInt.logIn()){
               System.out.println("Hi "+ loginRequestInt.getUser().getFirstName() + " " + loginRequestInt.getUser().getLastName());
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