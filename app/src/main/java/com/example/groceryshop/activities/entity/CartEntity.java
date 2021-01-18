package com.example.groceryshop.activities.entity;

public class CartEntity {
    public int id;
    public String imgProduct;
    public String nameProduct;
    public int priceProduct;
    public int quantity;

    public CartEntity(String imgProduct, String nameProduct, int priceProduct, int quantity) {
        this.imgProduct = imgProduct;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.quantity = quantity;
    }

    public CartEntity() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImgProduct(String imgProduct) {
        this.imgProduct = imgProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }


    public void setPriceProduct(int priceProduct) {
        this.priceProduct = priceProduct;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
