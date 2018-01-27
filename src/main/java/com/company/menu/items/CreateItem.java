package com.company.menu.items;

import com.company.model.Interfaces.CreateItemRequestInt;
import com.company.menu.Item;
import com.company.model.Interfaces.CreateItemInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateItem extends Item implements CreateItemInt {

    @Autowired
    CreateItemRequestInt createItemRequestInt;

    public CreateItem() {

    }

    @Override
    public String displayedName() {
        return "Create new item";
    }

    @Override
    public void perform() {

        createItemRequestInt.getResponce();

    }
}