package com.company.model.Interfaces;

import com.company.model.ItemInst;
import org.springframework.http.ResponseEntity;

public interface AllItemsRequestInt {

    public ResponseEntity<ItemInst[]> getAllItems(String flag);

    public void printResponse(String flag);

}