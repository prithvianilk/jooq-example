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
        UsersRecord usersRecord = dsl
                .newRecord(Tables.USERS)
                .with(Tables.USERS.ID, id)
                .with(Tables.USERS.USERNAME, username);

        usersRecord.store();

        return usersRecord;
    }

    public UsersRecord findById(String id) {
        return dsl.selectFrom(com.prithvianilk.Tables.USERS)
                .where(Tables.USERS.ID.eq(id))
                .limit(1)
                .fetch()
                .get(0);
    }
}
