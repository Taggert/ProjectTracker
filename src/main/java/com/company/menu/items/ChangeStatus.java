package com.company.menu.items;

import com.company.model.Interfaces.ItemStatusChangeRequestInt;
import com.company.menu.Item;
import com.company.model.Interfaces.ChangeStatusInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangeStatus extends Item implements ChangeStatusInt {

    @Autowired
    ItemStatusChangeRequestInt itemStatusChangeRequestInt;

    public ChangeStatus(){
    }

    @Override
    public String displayedName() {
        return "Change item status to DONE";
    }

    @Override
    public void perform() {
        itemStatusChangeRequestInt.changeStatus();
    }
}