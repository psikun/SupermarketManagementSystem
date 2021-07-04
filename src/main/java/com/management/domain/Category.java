package com.management.domain;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

/**
 * @author SiKun
 * @date 2021/05/18/ 10:57
 */

public class Category {
    private SimpleIntegerProperty categoryId = new SimpleIntegerProperty();
    private SimpleStringProperty categoryName = new SimpleStringProperty();
    private Date addTime;
    private Date updateTime;
    private SimpleBooleanProperty deleted = new SimpleBooleanProperty();

    public Category() {
    }

    public int getCategoryId() {
        return categoryId.get();
    }

    public SimpleIntegerProperty categoryIdProperty() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId.set(categoryId);
    }

    public String getCategoryName() {
        return categoryName.get();
    }

    public SimpleStringProperty categoryNameProperty() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName.set(categoryName);
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
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName=" + categoryName +
                ", addTime=" + addTime +
                ", updateTime=" + updateTime +
                ", deleted=" + deleted +
                '}';
    }
}
