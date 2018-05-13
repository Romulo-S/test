package com.example.analyst;

import java.util.List;
import java.util.Objects;

public class Sale {

    private int SaleID;
    private List<Item> items;
    private String salesmanName;

    public Sale(int saleID, List<Item> items, String salesmanName) {
        SaleID = saleID;
        this.items = items;
        this.salesmanName = salesmanName;
    }

    public int getSaleID() {
        return SaleID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sale)) return false;
        Sale sale = (Sale) o;
        return SaleID == sale.SaleID &&
                Objects.equals(items, sale.items) &&
                Objects.equals(salesmanName, sale.salesmanName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(SaleID, items, salesmanName);
    }

    public void setSaleID(int saleID) {
        SaleID = saleID;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }
}
