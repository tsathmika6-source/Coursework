package com.example.malabespareparts.filehandler;

import com.example.malabespareparts.model.Part;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class InventoryFileHandler {

    public  static  void  saveInventory(String filePath,List<Part> parts) {
        try(BufferedWriter writer=new BufferedWriter(new FileWriter(filePath))) {

            for (Part part:parts){
                writer.write(part.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
