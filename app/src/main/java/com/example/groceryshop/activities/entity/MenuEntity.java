package com.example.groceryshop.activities.entity;

public class MenuEntity {
    public int imgIcon;
    public int imgIconYellow;
    public String tvTitle;
    public boolean isSelected;

    public MenuEntity(int imgIcon, int imgIconYellow, String tvTitle) {
        this.imgIcon = imgIcon;
        this.imgIconYellow = imgIconYellow;
        this.tvTitle = tvTitle;
    }

    public MenuEntity setSelected(boolean selected) {
        isSelected = selected;
        return this;
    }

}
