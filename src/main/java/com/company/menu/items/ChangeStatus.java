package com.company.menu.items;

import com.company.core.ItemStatusChangeRequest;
import com.company.menu.InputOutput;
import com.company.menu.Item;

public class ChangeStatus extends Item {

    public ChangeStatus(InputOutput inputOutput) {
        super(inputOutput);
    }

    @Override
    public String displayedName() {
        return "Change item status to DONE";
    }

    @Override
    public void perform() {
        ItemStatusChangeRequest.changeStatus();
    }
}