package com.example.analyst;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class ExportFile {

    private int clientesAmount;
    private int salesmanAmount;
    private int mostExpensiveSaleID;
    private String worstSalesman;

    public ExportFile(int clientesAmount, int salesmanAmount, int mostExpensiveSaleID, String worstSalesman) {
        this.clientesAmount = clientesAmount;
        this.salesmanAmount = salesmanAmount;
        this.mostExpensiveSaleID = mostExpensiveSaleID;
        this.worstSalesman = worstSalesman;
    }

    public void export(Path filename) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename.toFile()))) {

            bw.write(clientesAmount);
            bw.write(salesmanAmount);
            bw.write(mostExpensiveSaleID);
            bw.write(worstSalesman);

            System.out.println("Done");

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}

