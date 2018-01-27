package com.company.menu.items;

import com.company.model.Interfaces.DeleteItemRequestInt;
import com.company.menu.Item;
import com.company.model.Interfaces.DeletItemInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletItem extends Item implements DeletItemInt {

    @Autowired
    DeleteItemRequestInt deleteItemRequestInt;

    public DeletItem( ) {

    }

    @Override
    public String displayedName() {
        return "Delete item";
    }

    @Override
    public void perform() {
        deleteItemRequestInt.deleteItem();
    }
}