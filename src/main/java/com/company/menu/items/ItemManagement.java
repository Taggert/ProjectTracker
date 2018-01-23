package com.company.menu.items;

import com.company.menu.InputOutput;
import com.company.menu.Item;
import com.company.menu.Menu;
import java.util.ArrayList;

public class ItemManagement extends Item {


    public ItemManagement(InputOutput inputOutput) {
        super(inputOutput);
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

        res.add(new CreateItem(inputOutput));
        res.add(new AllItems(inputOutput));
        res.add(new AllMyItems(inputOutput));
        res.add(new ChangeStatus(inputOutput));
        res.add(new DeletItem(inputOutput));
        res.add(new BackItem(inputOutput));



        return res;
    }
}