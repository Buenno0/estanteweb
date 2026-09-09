package com.estanteweb.estanteweb;
import java.sql.Connection;
import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnectionTest implements CommandLineRunner {

    private final DataSource dataSource;

    public DatabaseConnectionTest(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("MySQL conectado com sucesso!");
            System.out.println("URL: " + connection.getMetaData().getURL());
            System.out.println("Banco: " + connection.getCatalog());
        }
    }
}