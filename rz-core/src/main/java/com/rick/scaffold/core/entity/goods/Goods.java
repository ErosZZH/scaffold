package com.rick.scaffold.core.entity.goods;

import com.rick.scaffold.core.entity.generic.DataEntity;

import java.math.BigDecimal;

public class Goods extends DataEntity<Goods> {

    private Long cateId;

    private String sn;

    private String name;

    private String brand;

    private Integer amount;

    private String goodsThumb;

    private BigDecimal marketPrice;

    private BigDecimal shopPrice;

    private String brief;

    private Boolean isOnSale;

    private Long saledAmount;

    private Integer tag;

    private Long shopId;

    private String unit;

    private String specification;

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getCateId() {
        return cateId;
    }

    public void setCateId(Long cateId) {
        this.cateId = cateId;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getGoodsThumb() {
        return goodsThumb;
    }

    public void setGoodsThumb(String goodsThumb) {
        this.goodsThumb = goodsThumb == null ? null : goodsThumb.trim();
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(BigDecimal shopPrice) {
        this.shopPrice = shopPrice;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief == null ? null : brief.trim();
    }

    public Boolean getIsOnSale() {
        return isOnSale;
    }

    public void setIsOnSale(Boolean isOnSale) {
        this.isOnSale = isOnSale;
    }

    public Long getSaledAmount() {
        return saledAmount;
    }

    public void setSaledAmount(Long saledAmount) {
        this.saledAmount = saledAmount;
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}