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
        if (Objects.isNull(dsl)) {
            Connection conn = JdbcConnectionSingleton.getInstance();
            dsl = DSL.using(conn, SQLDialect.SQLITE);
        }

        return dsl;
    }
}
