package com.example.malabespareparts.filehandler;

import com.example.malabespareparts.model.Part;
import com.example.malabespareparts.utils.ValidationUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InventoryParser {
    public static ArrayList<Part> loadInventory(String filePath) {
        ArrayList<Part> parts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                Part part = parseLine(line);

                if (part != null) {
                    parts.add(part);
                }

            }

        } catch (IOException e) {
            System.out.println("Error reading inventory file.");
        }
        return parts;
    }

    private static Part parseLine(String line) {
        line=line.replace("|",",");
        line=line.replace(";",",");

        String[] data=line.split(",");

        if (data.length<7){
            return null;
        }

        String partCode = ValidationUtil.safeString(data[0]);
        String name = ValidationUtil.safeString(data[1]);
        String brand = ValidationUtil.safeString(data[2]);

        double price = ValidationUtil.parsePrice(data[3]);
        int quantity = ValidationUtil.parseQuantity(data[4]);

        String category = ValidationUtil.normalizeCategory(data[5]);
        String date = ValidationUtil.standardizeDate(data[6]);

        String image="";

        if (data.length>7){
            image=ValidationUtil.safeString(data[7]);
        }

        if (!ValidationUtil.isValidPartCode(partCode)){
            return  null;
        }

        return  new  Part(
                partCode,
                name,
                brand,
                price,
                quantity,
                category,
                date,
                image
        );
    }
}