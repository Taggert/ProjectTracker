package com.company.menu.items;

import com.company.model.Interfaces.AllUsersRequestInt;
import com.company.menu.Item;
import com.company.model.Interfaces.AllUsersInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AllUsers extends Item implements AllUsersInt {

    @Autowired
    AllUsersRequestInt allUsersRequestInt;


    public AllUsers() {

    }

    @Override
    public String displayedName() {
        return "Show all users";
    }

    @Override
    public void perform() {

        allUsersRequestInt.printResponse();

    }
}