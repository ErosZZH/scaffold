package com.rick.scaffold.core.entity.address;

import com.rick.scaffold.core.entity.generic.BaseEntity;

public class Region extends BaseEntity<Region> {

    private Integer parentId;

    private String regionName;

    private Byte regionType;


    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName == null ? null : regionName.trim();
    }

    public Byte getRegionType() {
        return regionType;
    }

    public void setRegionType(Byte regionType) {
        this.regionType = regionType;
    }
}