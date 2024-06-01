package com.prithvianilk;

import com.prithvianilk.Tables;
import org.jooq.DSLContext;
import com.prithvianilk.tables.records.PostsRecord;
import org.jooq.Record2;
import org.jooq.Result;

import java.util.List;

import static org.jooq.impl.DSL.count;

public class PostRepository {
    private final DSLContext dsl;

    public PostRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public PostsRecord save(String id, String userId, String content) {
        PostsRecord postsRecord = dsl
                .newRecord(Tables.POSTS)
                .with(Tables.POSTS.ID, id)
                .with(Tables.POSTS.USER_ID, userId)
                .with(Tables.POSTS.CONTENT, content);

        postsRecord.store();

        return postsRecord;
    }

    public List<PostsRecord> findByUserId(String userId) {
        return dsl.selectFrom(Tables.POSTS)
                .where(Tables.POSTS.USER_ID.eq(userId))
                .fetch();
    }

    public Result<Record2<Integer, String>> countOfPostsByUserId() {
        return dsl.select(count(), Tables.POSTS.USER_ID)
                .from(Tables.POSTS)
                .groupBy(Tables.POSTS.USER_ID)
                .fetch();
    }
}
