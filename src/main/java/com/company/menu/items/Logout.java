package com.company.menu.items;

import com.company.model.Interfaces.LogoutRequestInt;
import com.company.menu.ConsoleInputOutput;
import com.company.model.Interfaces.InputOutput;
import com.company.menu.Item;
import com.company.model.Interfaces.LogoutInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Logout extends Item implements LogoutInt {

    @Autowired
    LogoutRequestInt logoutRequestInt;
    private InputOutput inputOutput = new ConsoleInputOutput();

    public Logout() {
    }

    @Override
    public String displayedName() {
        return "Logout";
    }

    @Override
    public void perform() {
        logoutRequestInt.printResponce();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}