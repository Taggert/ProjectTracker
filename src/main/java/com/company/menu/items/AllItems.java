package com.company.menu.items;

import com.company.core.AllItemsRequest;
import com.company.menu.InputOutput;
import com.company.menu.Item;

public class AllItems extends Item {


    public AllItems(InputOutput inputOutput) {
        super(inputOutput);
    }

    @Override
    public String displayedName() {
        return "Show all items";
    }

    @Override
    public void perform() {

        AllItemsRequest.printResponse();

    }
}