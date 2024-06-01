package com.prithvianilk;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;

public class DslContextSingleton {
    private DslContextSingleton() {
    }

    private static DSLContext dsl;

    public static DSLContext getInstance() {
        if (Objects.nonNull(dsl)) {
            return dsl;
        }

        Connection conn = getConnection();
        dsl = DSL.using(conn, SQLDialect.SQLITE);

        return dsl;
    }

    private static Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:/Users/prithvianilkumar/code/fun/jooq-example/lol.db");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
