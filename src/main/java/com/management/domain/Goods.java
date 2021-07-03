package com.management.domain;


import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;

/**
 * @author SiKun
 * @date 2021/05/18/ 10:58
 */

public class Goods {
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleDoubleProperty price = new SimpleDoubleProperty();
    private Category category;
    private Brand brand;
    private SimpleIntegerProperty sales = new SimpleIntegerProperty();
    private SimpleIntegerProperty inventory = new SimpleIntegerProperty();
    private Date addTime;
    private Date updateTime;
    private SimpleStringProperty remarks = new SimpleStringProperty();
    private SimpleBooleanProperty onSale = new SimpleBooleanProperty();
    private SimpleBooleanProperty deleted = new SimpleBooleanProperty();

    public Goods() {
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public int getSales() {
        return sales.get();
    }

    public SimpleIntegerProperty salesProperty() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales.set(sales);
    }

    public int getInventory() {
        return inventory.get();
    }

    public SimpleIntegerProperty inventoryProperty() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory.set(inventory);
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

    public String getRemarks() {
        return remarks.get();
    }

    public SimpleStringProperty remarksProperty() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks.set(remarks);
    }


    public boolean isOnSale() {
        return onSale.get();
    }

    public SimpleBooleanProperty onSaleProperty() {
        return onSale;
    }

    public void setOnSale(boolean onSale) {
        this.onSale.set(onSale);
    }


    public SimpleBooleanProperty deletedProperty() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted.set(deleted);
    }


    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name=" + name +
                ", price=" + price +
                ", category=" + category +
                ", brand=" + brand +
                ", sales=" + sales +
                ", inventory=" + inventory +
                ", addTime=" + addTime +
                ", updateTime=" + updateTime +
                ", remarks=" + remarks +
                ", isOnSale=" + onSale +
                ", deleted=" + deleted +
                '}';
    }
}
