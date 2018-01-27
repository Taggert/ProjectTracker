package com.company.menu.items;

import com.company.model.Interfaces.AllItemsRequestInt;
import com.company.menu.Item;
import com.company.model.Interfaces.AllMyItemsInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AllMyItems extends Item implements AllMyItemsInt{

    @Autowired
    AllItemsRequestInt allItemsRequestInt;

    public AllMyItems() {

    }

    @Override
    public String displayedName() {
        return "Show all my items";
    }

    @Override
    public void perform() {

        allItemsRequestInt.printResponse("true");

    }
}