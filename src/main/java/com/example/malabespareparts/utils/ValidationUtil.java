package com.example.malabespareparts.utils;

public class ValidationUtil {
    public static boolean isValidPartCode(String partCode){
        return partCode != null &&
                partCode.matches("P\\d{3}");

    }

    public static double parsePrice(String price) {

        if (price == null || price.trim().isEmpty()){ return 0.0;}

        price=price.replace("Rs.", "")
                .replace("Rs", "")
                .replace(",", "")
                .trim();

        try {
            return Double.parseDouble(price);
        } catch (NumberFormatException e){
            return 0.0;
        }
    }

    public static int parseQuantity(String quantity) {

        if (quantity == null || quantity.trim().isEmpty()) {return 0;}
        try {
            return Integer.parseInt(quantity.trim());
        }   catch (NumberFormatException e) {return  0;}
    }

    public  static String safeString(String text){
        if(text == null) {
            return "";
        }
        return  text.trim();
    }

    public static String normalizeCategory(String category){
        return safeString(category).toUpperCase();
    }

    public  static  String standardizeDate(String date){
        return  safeString(date);
    }
}
