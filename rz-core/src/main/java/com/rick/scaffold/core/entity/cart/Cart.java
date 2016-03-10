package com.rick.scaffold.core.entity.cart;

import com.rick.scaffold.core.entity.generic.DataEntity;

import java.math.BigDecimal;

public class Cart extends DataEntity<Cart> {

    private Long userId;

    private Long shopId;

    private BigDecimal productPrice;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }
}