package com.management.domain;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

/**
 * @author SiKun
 * @date 2021/05/18/ 10:53
 */

public class Brand {
    private SimpleIntegerProperty brandId = new SimpleIntegerProperty();
    private SimpleStringProperty brandName = new SimpleStringProperty();
    private Date addTime;
    private Date updateTime;
    private SimpleBooleanProperty deleted = new SimpleBooleanProperty();

    public Brand() {
    }

    public int getBrandId() {
        return brandId.get();
    }

    public SimpleIntegerProperty brandIdProperty() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId.set(brandId);
    }

    public String getBrandName() {
        return brandName.get();
    }

    public SimpleStringProperty brandNameProperty() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName.set(brandName);
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isDeleted() {
        return deleted.get();
    }

    public SimpleBooleanProperty deletedProperty() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted.set(deleted);
    }

    @Override
    public String toString() {
        return "Brand{" +
                "brandId=" + brandId +
                ", brandName=" + brandName +
                ", addTime=" + addTime +
                ", updateTime=" + updateTime +
                ", deleted=" + deleted +
                '}';
    }
}
