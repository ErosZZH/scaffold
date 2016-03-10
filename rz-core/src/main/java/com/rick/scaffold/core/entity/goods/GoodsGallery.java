package com.rick.scaffold.core.entity.goods;

import com.rick.scaffold.core.entity.generic.BaseEntity;

public class GoodsGallery extends BaseEntity<GoodsGallery> {

    private Long goodsId;

    private String imgUrl;

    private String imgDesc;

    private Byte imgType;



    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getImgDesc() {
        return imgDesc;
    }

    public void setImgDesc(String imgDesc) {
        this.imgDesc = imgDesc == null ? null : imgDesc.trim();
    }

    public Byte getImgType() {
        return imgType;
    }

    public void setImgType(Byte imgType) {
        this.imgType = imgType;
    }
}