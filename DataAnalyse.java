package com.example.analyst;

import org.springframework.beans.factory.annotation.Value;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataAnalyse {
    private List<SalesMan> salesMans;
    private List<Customer> customers;
    private List<Sale> sales;
    private Path path;
    private ExportFile exportFile;

    private int clientesAmount;
    private int salesmanAmount;
    private int mostExpensiveSaleID;
    private String worstSalesman;

    @Value("system.data.out")
    private static String OUTPUT_FOLDER;

    public DataAnalyse(List<SalesMan> salesMans, List<Customer> customers, List<Sale> sales, Path path) {
        this.salesMans = salesMans;
        this.customers = customers;
        this.sales = sales;
        this.path = path;
    }

    public void doAnalyse() {
        salesmanAmount = salesMans.size();
        clientesAmount = customers.size();
        double higherSell = 0;
        int highSaleID = 0;
        for (Sale sale : sales) {
            List<Item> items = sale.getItems();
            for (Item item : items) {
                double itemPrice = item.getItemPrice();
                int quantity = item.getQuantity();
                double total = quantity * itemPrice;
                if (total > higherSell) {
                    higherSell = itemPrice;
                    highSaleID = sale.getSaleID();
                }
            }
        }
        mostExpensiveSaleID = highSaleID;

        worstSalesman();
        exportFile = new ExportFile(clientesAmount, salesmanAmount, mostExpensiveSaleID, worstSalesman);
        exportFile.export(path.resolve(".done.dat"));

    }

    private void worstSalesman() {

        Set<String> names = new HashSet<>();

        for (Sale sale : sales) {
            String salesmanName = sale.getSalesmanName();
            if (!names.contains(salesmanName)) {
                names.add(salesmanName);
            }
        }

        for (Sale sale : sales) {
            List<Item> items = sale.getItems();
            for (Item item : items) {
                double itemPrice = item.getItemPrice();
                int quantity = item.getQuantity();
                double total = quantity * itemPrice;
                String salesManName = sale.getSalesmanName();
                for (SalesMan salesMan : salesMans) {
                    if (salesMan.getName().equals(salesManName)) {
                        salesMan.incrementTotalSale(total);
                    }
                }
            }
        }
        double lowerSell = salesMans.get(0).getTotalSell();
        for (SalesMan salesMan : salesMans) {
            double totalSell = salesMan.getTotalSell();
            if (totalSell < lowerSell) {
                worstSalesman = salesMan.getName();
            }
        }
    }
}
