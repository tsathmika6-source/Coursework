package com.example.malabespareparts.service;

import com.example.malabespareparts.filehandler.InventoryParser;
import com.example.malabespareparts.model.Part;

import java.util.ArrayList;

public class InventoryService {

    private ArrayList<Part> inventory;

    public InventoryService(String filePath){
        inventory=InventoryParser.loadInventory(filePath);
    }

    public ArrayList<Part> getAllParts(){
        return inventory;
    }

    public void addPart(Part part){
        inventory.add(part);
    }

    public  Part findPartByCode(String partCode) {
        for(Part part : inventory) {
            if (part.getPartCode().equalsIgnoreCase(partCode)){
                return part;
            }
        }

        return  null;
    }

    public boolean deletePart(String partCode){
        Part part = findPartByCode(partCode);
        if (part != null){
            inventory.remove(part);
            return true;
        }

        return false;
    }
    public  int getTotalParts(){
        return  inventory.size();
    }
}
