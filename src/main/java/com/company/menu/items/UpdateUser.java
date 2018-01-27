package com.company.menu.items;

import com.company.model.Interfaces.UpdateUserByIdInt;
import com.company.menu.Item;
import com.company.model.Interfaces.UpdateUserInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateUser extends Item implements UpdateUserInt {

    @Autowired
    UpdateUserByIdInt updateUserByIdInt;

    public UpdateUser() {
    }

    @Override
    public String displayedName() {
        return "Update user info";
    }

    @Override
    public void perform() {

        updateUserByIdInt.printResponce();

    }
}