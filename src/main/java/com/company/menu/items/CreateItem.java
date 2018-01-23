package com.company.menu.items;

import com.company.core.CreateItemRequest;
import com.company.menu.InputOutput;
import com.company.menu.Item;

public class CreateItem extends Item {


    public CreateItem(InputOutput inputOutput) {
        super(inputOutput);
    }

    @Override
    public String displayedName() {
        return "Create new item";
    }

    @Override
    public void perform() {

        CreateItemRequest.getResponce();

    }
}