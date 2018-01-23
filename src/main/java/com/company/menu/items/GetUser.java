package com.company.menu.items;

import com.company.core.GetUserByIdRequest;
import com.company.menu.InputOutput;
import com.company.menu.Item;

public class GetUser extends Item {


    public GetUser(InputOutput inputOutput) {
        super(inputOutput);
    }

    @Override
    public String displayedName() {
        return "Get user by user ID";
    }

    @Override
    public void perform() {

            GetUserByIdRequest.printResponce();

    }
}