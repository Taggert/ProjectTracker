package com.company.menu.items;

import com.company.core.DeleteItemRequest;
import com.company.menu.InputOutput;
import com.company.menu.Item;

public class DeletItem extends Item {

    public DeletItem(InputOutput inputOutput) {
        super(inputOutput);
    }

    @Override
    public String displayedName() {
        return "Delete item";
    }

    @Override
    public void perform() {
        DeleteItemRequest.deleteItem();
    }
}