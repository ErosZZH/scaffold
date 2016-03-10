package com.rick.scaffold.core.entity.shop;

import com.rick.scaffold.core.entity.generic.DataEntity;

public class Shop extends DataEntity<Shop> {

    private String shopName;

    private String shopImage;

    private String phone;

    private String address;


    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getShopImage() {
        return shopImage;
    }

    public void setShopImage(String shopImage) {
        this.shopImage = shopImage == null ? null : shopImage.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}