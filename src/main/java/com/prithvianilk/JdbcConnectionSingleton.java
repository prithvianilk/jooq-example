package com.prithvianilk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;

public class JdbcConnectionSingleton {
    private JdbcConnectionSingleton() {
    }

    private static final Logger log = LoggerFactory.getLogger(JdbcConnectionSingleton.class);

    private static Connection connection;

    public static Connection getInstance() {
        if (Objects.isNull(connection)) {
            initConnection();
        }

        return connection;
    }

    private static void initConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:/Users/prithvianilkumar/code/fun/jooq-example/lol.db");
        } catch (Exception e) {
            log.info("Failed to init jdbc connection", e);
            throw new RuntimeException(e);
        }
    }
}
