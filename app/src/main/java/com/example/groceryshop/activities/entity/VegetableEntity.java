package com.example.groceryshop.activities.entity;


public class VegetableEntity {
    private int idProduct;
    private String imgProduct;
    private String productName;
    private float productWeight;
    private int productPrice;
    private int productIdCategory;

    public VegetableEntity() {
    }

    public VegetableEntity(String imgProduct, String productName, float productWeight, int productPrice) {
        this.imgProduct = imgProduct;
        this.productName = productName;
        this.productWeight = productWeight;
        this.productPrice = productPrice;
    }

    public String getImgProduct() {
        return imgProduct;
    }

    public void setImgProduct(String imgProduct) {
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

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getProductIdCategory() {
        return productIdCategory;
    }

    public void setProductIdCategory(int productIdCategory) {
        this.productIdCategory = productIdCategory;
    }
}
