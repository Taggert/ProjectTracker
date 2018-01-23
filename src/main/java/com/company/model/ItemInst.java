package com.company.model;

import com.company.utils.ItemStatus;
import com.company.utils.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ItemInst {

    private String createdDate;
    private String description;
    private Integer id;
    private ItemStatus itemStatus;
    private ItemType itemType;
    private String title;
    private User user;

    @Override
    public String toString() {
        return "Item (created" + createdDate + ")\n" +
                description + "\n"+
                "id: " + id +"\n"+
                "Status: " + itemStatus +"\n"+
                "Type: " + itemType +"\n"+
                title + "\n"+
                "created by: \n" + user+"\n";
    }
}