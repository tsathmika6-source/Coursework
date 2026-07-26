package com.example.malabespareparts.filehandler;

import com.example.malabespareparts.model.Dealer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DealerFileHandler {

    public static void saveDealers(String filePath, List<Dealer> dealers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            for (Dealer dealer : dealers) {
                writer.write(dealer.toString());
                writer.newLine();
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }


}
