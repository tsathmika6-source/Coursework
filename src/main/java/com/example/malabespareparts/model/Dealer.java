package com.example.malabespareparts.model;

public class Dealer {

    private String dealerId;
    private String dealerName;
    private String contactNumber;
    private String address;

    public Dealer() {
    }

    public Dealer(String dealerId, String dealerName, String contactNumber,
                  String address) {

        this.dealerId = dealerId;
        this.dealerName = dealerName;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    public String getDealerId() {return dealerId;}

    public  void  setDealerId(String dealerId){this.dealerId=dealerId;}

    public String getDealerName() {return dealerName;}

    public void setDealerName(String dealerName){this.dealerName=dealerName;}

    public  String getContactNumber() {return contactNumber;}

    public  void setContactNumber(String contactNumber){this.contactNumber=contactNumber;}

    public String getAddress() {return address;}

    public  void setAddress(String address) {this.address=address;}

    @Override
    public String toString() {
        return dealerId + "," + dealerName + "," +
                contactNumber + "," + address;
    }
}





