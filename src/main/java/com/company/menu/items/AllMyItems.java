package com.company.menu.items;

import com.company.core.AllMyItemsRequest;
import com.company.menu.InputOutput;
import com.company.menu.Item;

public class AllMyItems extends Item {


    public AllMyItems(InputOutput inputOutput) {
        super(inputOutput);
    }

    @Override
    public String displayedName() {
        return "Show all my items";
    }

    @Override
    public void perform() {

        AllMyItemsRequest.printResponse();

    }
}