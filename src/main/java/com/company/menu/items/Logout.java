package com.company.menu.items;

import com.company.core.LogoutRequest;
import com.company.menu.InputOutput;
import com.company.menu.Item;

public class Logout extends Item {


    public Logout(InputOutput inputOutput) {
        super(inputOutput);
    }

    @Override
    public String displayedName() {
        return "Logout";
    }

    @Override
    public void perform() {
        LogoutRequest.printResponce();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}