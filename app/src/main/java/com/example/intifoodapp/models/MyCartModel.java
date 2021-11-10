package com.example.intifoodapp.models;

import java.io.Serializable;

public class MyCartModel implements Serializable {
    String foodName;
    String totalQuantity;
    int totalPrice;
    String documentId;


    public MyCartModel() {
    }

    public MyCartModel(String foodName, String totalQuantity, int totalPrice, String documentId) {
        this.foodName = foodName;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
        this.documentId = documentId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
