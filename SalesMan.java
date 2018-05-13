package com.example.analyst;

public class SalesMan {

    private String cpf;
    private String name;

    private float salary;

    private double totalSell;
    public SalesMan(String cpf, String name, float salary) {
        this.cpf = cpf;
        this.name = name;
        this.salary = salary;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public void incrementTotalSale(double total) {
        this.totalSell += total;
    }

    public double getTotalSell() {
        return totalSell;
    }

    public void setTotalSell(double totalSell) {
        this.totalSell = totalSell;
    }
}
