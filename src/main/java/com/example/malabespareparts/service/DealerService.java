package com.example.malabespareparts.service;

import com.example.malabespareparts.filehandler.DealerParser;
import com.example.malabespareparts.model.Dealer;

import java.util.ArrayList;

public class DealerService {
    private ArrayList<Dealer> dealers;

    public  DealerService(String filePath){
        dealers = DealerParser.loadDealers(filePath);
    }

    public ArrayList<Dealer> getAllDealers() {
        return dealers;
    }

    public  Dealer findDealerById(String dealerId) {
        for (Dealer dealer : dealers) {
            if (dealer.getDealerId().equalsIgnoreCase(dealerId)) {
                return dealer;
            }
        }

        return null;
    }

    public  int getDealerCount(){
        return dealers.size();
    }
}

