package com.example.analyst;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class DataAnalystApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DataAnalystApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        File file = new File("c:\\data\\ test.dat");
        file.createNewFile();
        WatchFolder watchFolder = new WatchFolder();
        watchFolder.watchInputFolder();
    }
}
