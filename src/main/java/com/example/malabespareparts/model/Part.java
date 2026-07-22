package com.example.malabespareparts.model;

public class Part {
    private String partCode;
    private String name;
    private String brand;
    private double price;
    private int  quantity;
    private String category;
    private String lastUpdated;
    private String image;

    public Part(){
    }

    public Part(String partCode, String name, String brand, double price, int quantity,
                String category, String lastUpdated, String image){
       this.partCode=partCode;
       this.name=name;
       this.brand=brand;
       this.price=price;
       this.quantity=quantity;
       this.category=category;
       this.lastUpdated =lastUpdated;
       this.image=image;
    }

    public  String getPartCode() {return partCode;}

    public void setPartCode(String partCode) {this.partCode=partCode;}

    public String getName() {return name;}

    public void setName(String name){this.name=name;}

    public String getBrand() {return brand;}

    public void setBrand(String brand){this.brand=brand;}

    public double getPrice(){return price;}

    public  void  setPrice(double price){this.price=price;}

    public  int getQuantity(){return quantity;}

    public void setQuantity(int quantity){this.quantity=quantity;}

    public  String getCategory(){return category;}

    public  void  setCategory(String category){this.category=category;}

    public String getLastUpdated() {return lastUpdated;}

    public void setLastUpdated(String lastUpdated) {this.lastUpdated = lastUpdated;}

    public String getImage() {return image;}

    public void setImage(String image) {this.image = image;}

    public double getStockValue() {return price*quantity;}

    @Override
    public String toString() {
        return partCode + "," + name + "," + brand + "," + price + "," +
                quantity + "," + category + "," + lastUpdated + "," + image;
    }

}

