package com.company.menu.items;

import com.company.menu.ConsoleInputOutput;
import com.company.model.Interfaces.*;
import com.company.menu.Item;
import com.company.menu.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ItemManagement extends Item implements ItemManagementInt {
    private InputOutput inputOutput = new ConsoleInputOutput();
    @Autowired
    AllItemsInt allItemsInt;
    @Autowired
    CreateItemInt createItemInt;
    @Autowired
    AllMyItemsInt allMyItemsInt;
    @Autowired
    ChangeStatusInt changeStatusInt;
    @Autowired
    DeletItemInt deletItemInt;

    public ItemManagement() {
    }

    @Override
    public String displayedName() {
        return "Item management";
    }

    @Override
    public void perform() {


        ArrayList<Item> items = getItems();
        Menu menu = new Menu(items, inputOutput);
        menu.runMenu();

    }

    private ArrayList<Item> getItems() {
        ArrayList<Item> res = new ArrayList<>();

        res.add((Item) createItemInt);
        res.add((Item) allItemsInt);
        res.add((Item) allMyItemsInt);
        res.add((Item) changeStatusInt);
        res.add((Item) deletItemInt);
        res.add(new BackItem(inputOutput));


        return res;
    }
}