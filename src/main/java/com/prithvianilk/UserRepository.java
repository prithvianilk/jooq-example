package com.prithvianilk;

import com.prithvianilk.tables.records.UsersRecord;
import com.prithvianilk.Tables;
import org.jooq.DSLContext;

public class UserRepository {
    private final DSLContext dsl;

    public UserRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public UsersRecord save(String id, String username) {
        return dsl.insertInto(Tables.USERS)
                .set(Tables.USERS.ID, id)
                .set(Tables.USERS.USERNAME, username)
                .returning()
                .fetchOne();
    }

    public UsersRecord findById(String id) {
        return dsl.selectFrom(Tables.USERS)
                .where(Tables.USERS.ID.eq(id))
                .limit(1)
                .fetchOne();
    }
}
