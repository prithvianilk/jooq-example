package com.prithvianilk.repository;

import com.prithvianilk.tables.records.UsersRecord;
import org.jooq.DSLContext;

import static com.prithvianilk.Tables.USERS;

public class MyUserRepository {
    private final DSLContext dsl;

    public MyUserRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public UsersRecord save(String id, String username) {
        return dsl.insertInto(USERS)
                .set(USERS.ID, id)
                .set(USERS.USERNAME, username)
                .returning()
                .fetchOne();
    }

    public UsersRecord findById(String id) {
        return dsl.selectFrom(USERS)
                .where(USERS.ID.eq(id))
                .limit(1)
                .fetchOne();
    }
}
