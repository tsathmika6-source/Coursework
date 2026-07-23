package com.example.malabespareparts.filehandler;

import com.example.malabespareparts.model.Dealer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;

public class DealerParser {
    public static ArrayList<Dealer> loadDealers(String filePath){
        ArrayList<Dealer> dealers=new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){

            String line;
            while ((line=reader.readLine()) != null ){

                if(line.trim().isEmpty()){
                    continue;
                }

                Dealer dealer=parseLine(line);

                if (dealer != null) {
                    dealers.add(dealer);
                }
            }
        }catch (IOException e){
            System.out.println("Error reading dealer file.");
        }

        return  dealers;
    }

    private  static Dealer parseLine(String line){
        line=line.replace("|",",");
        line=line.replace(";",",");

        String[] data = line.split(",");

        if (data.length<5) {
            return  null;
        }

        return new Dealer(
                data[0].trim(),
                data[1].trim(),
                data[2].trim(),
                data[3].trim(),
                data[4].trim()
        );
    }
}

