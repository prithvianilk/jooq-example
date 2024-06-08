package com.prithvianilk.repository;

import com.prithvianilk.PostsCountByUserId;
import org.jooq.DSLContext;
import com.prithvianilk.tables.records.PostsRecord;

import static com.prithvianilk.Tables.POSTS;

import java.util.List;

import static com.prithvianilk.Tables.USERS;
import static org.jooq.impl.DSL.count;

public class MyPostRepository {
    private final DSLContext dsl;

    public MyPostRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public PostsRecord save(String id, String userId, String content) {
        return dsl.insertInto(POSTS)
                .set(POSTS.ID, id)
                .set(POSTS.USER_ID, userId)
                .set(POSTS.CONTENT, content)
                .returning()
                .fetchOne();
    }

    public List<PostsRecord> findByUserId(String userId) {
        return dsl.selectFrom(POSTS)
                .where(POSTS.USER_ID.eq(userId))
                .fetch();
    }

    public List<PostsCountByUserId> countOfPostsByUserId() {
        return dsl.select(count(), POSTS.USER_ID)
                .from(POSTS)
                .groupBy(POSTS.USER_ID)
                .fetchInto(PostsCountByUserId.class);
    }

    public List<PostsRecord> findPostsWhereContentContains(String contentPattern) {
        return dsl.selectFrom(POSTS)
                .where(POSTS.CONTENT.like("%" + contentPattern + "%"))
                .fetch();
    }

    public List<PostsCountByUserName> countOfPostsByUserName() {
        return dsl.select(count(), USERS.USERNAME)
                .from(POSTS).join(USERS).on(POSTS.USER_ID.eq(USERS.ID))
                .groupBy(USERS.ID)
                .fetchInto(PostsCountByUserName.class);
    }
}
