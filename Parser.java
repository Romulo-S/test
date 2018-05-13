package com.example.analyst;

import java.io.*;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Parser {

    private List<SalesMan> salesMans;
    private List<Customer> customers;
    private List<Sale> sales;
    private List<Item> items;
    private SalesMan salesMan;
    private Customer customer;
    private Sale sale;
    private Item item;
    private String cpf;
    private DataAnalyse dataAnalyse;

    public void doParse(Path path) {

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(path.toFile()), "ISO-8859-1"))) {

            String sCurrentLine;

            while ((sCurrentLine = in.readLine()) != null) {
                System.out.println(sCurrentLine);
                String[] tokens = sCurrentLine.split("ç");
                if (tokens[0].equals("001")) {
                    salesMan = new SalesMan(tokens[1], tokens[2], Float.parseFloat(tokens[3]));
                    salesMans.add(salesMan);
                } else if (tokens[0].equals("002")) {
                    customer = new Customer(tokens[1], tokens[2], (tokens[3]));
                    customers.add(customer);
                } else if (tokens[0].equals("003")) {
                    List<String> itens = Arrays.asList(tokens[2].split(","));
                    for (String item : itens) {
                        String[] values = item.split("-");
                        this.item = new Item(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Double.parseDouble(values[2]));
                        items.add(this.item);
                    }
                    sale = new Sale(Integer.parseInt(tokens[1]), items, tokens[3]);
                    sales.add(sale);
                }

            }
            dataAnalyse = new DataAnalyse(salesMans, customers, sales, path);
            dataAnalyse.doAnalyse();
        } catch (IOException e) {
            e.getMessage();
            e.printStackTrace();
        }

    }
}
