package com.company.menu.items;

import com.company.model.Interfaces.AllItemsRequestInt;
import com.company.menu.Item;
import com.company.model.Interfaces.AllItemsInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AllItems extends Item implements AllItemsInt {

    @Autowired
    AllItemsRequestInt allItemsRequestInt;

    public AllItems() {

    }

    @Override
    public String displayedName() {
        return "Show all items";
    }

    @Override
    public void perform() {

        allItemsRequestInt.printResponse("false");

    }
}