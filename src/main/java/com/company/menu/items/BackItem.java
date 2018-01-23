package com.company.menu.items;

import com.company.menu.InputOutput;
import com.company.menu.Item;

public class BackItem extends Item {

    public BackItem(InputOutput inputOutput) {
        super(inputOutput);
    }
 
    @Override
    public String displayedName() {
        return "Back";
    }
 
    @Override
    public void perform() {

    }
     
    @Override
    public boolean isExit(){
        return true;
    }
     
 
}