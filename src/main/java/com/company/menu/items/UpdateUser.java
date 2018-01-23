package com.company.menu.items;

import com.company.core.UpdateUserById;
import com.company.menu.InputOutput;
import com.company.menu.Item;

public class UpdateUser extends Item {


    public UpdateUser(InputOutput inputOutput) {
        super(inputOutput);
    }

    @Override
    public String displayedName() {
        return "Update user info";
    }

    @Override
    public void perform() {

        UpdateUserById.printResponce();

    }
}