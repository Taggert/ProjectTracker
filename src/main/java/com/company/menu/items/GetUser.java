package com.company.menu.items;

import com.company.model.Interfaces.GetUserByIdRequestInt;
import com.company.menu.Item;
import com.company.model.Interfaces.GetUserInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetUser extends Item implements GetUserInt{

    @Autowired
    GetUserByIdRequestInt getUserByIdRequest;

    public GetUser() {
    }

    @Override
    public String displayedName() {
        return "Get user by user ID";
    }

    @Override
    public void perform() {

        getUserByIdRequest.printResponce();

    }
}