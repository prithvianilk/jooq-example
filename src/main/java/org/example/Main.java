package org.example;

import com.prithvianilk.Tables;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.prithvianilk.tables.records.UsersRecord;

public class Main {
    public static void main(String[] args) throws Exception {
        DSLContext dsl = getDslContext();

        UsersRecord usersRecord = dsl.newRecord(Tables.USERS);
        String userId = "dc812c8f-6994-4123-861b-8f98bd8b3ab8";
        usersRecord.setId(userId);
        usersRecord.setUsername("prithvianilk");
        usersRecord.store();

        UsersRecord x = dsl
                .selectFrom(Tables.USERS)
                .where(Tables.USERS.ID.eq(userId))
                .limit(1)
                .fetch()
                .get(0);

        System.out.println(x);
    }

    private static DSLContext getDslContext() throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();
        return DSL.using(conn, SQLDialect.SQLITE);
    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection("jdbc:sqlite:/Users/prithvianilkumar/code/fun/jooq-example/lol.db");
    }
}