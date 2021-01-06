package com.example.groceryshop.activities.entity;


public class VegetableEntity {
    private int imgProduct;
    private String productName;
    private float productWeight;
    private int productPrice;

    public VegetableEntity(int imgProduct, String productName, float productWeight, int productPrice) {
        this.imgProduct = imgProduct;
        this.productName = productName;
        this.productWeight = productWeight;
        this.productPrice = productPrice;
    }

    public int getImgProduct() {
        return imgProduct;
    }

    public void setImgProduct(int imgProduct) {
        this.imgProduct = imgProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(float productWeight) {
        this.productWeight = productWeight;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
}
