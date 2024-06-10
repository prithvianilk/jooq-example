package com.prithvianilk.repository;

import org.jooq.DSLContext;
import com.prithvianilk.tables.records.PostsRecord;

import static com.prithvianilk.Tables.POSTS;

import java.util.List;

import static com.prithvianilk.Tables.USERS;
import static org.jooq.impl.DSL.count;

public class PostRepository {
    private final DSLContext sql;

    public PostRepository(DSLContext dsl) {
        this.sql = dsl;
    }

    public PostsRecord save(String id, String userId, String content) {
        return sql.insertInto(POSTS)
                .set(POSTS.ID, id)
                .set(POSTS.USER_ID, userId)
                .set(POSTS.CONTENT, content)
                .returning()
                .fetchOne();
    }

    public List<PostsRecord> findByUserId(String userId) {
        return sql.selectFrom(POSTS)
                .where(POSTS.USER_ID.eq(userId))
                .fetch();
    }

    public List<PostsCountByUserId> countOfPostsByUserId() {
        return sql.select(count(), POSTS.USER_ID)
                .from(POSTS)
                .groupBy(POSTS.USER_ID)
                .fetchInto(PostsCountByUserId.class);
    }

    public List<PostsRecord> findPostsWhereContentContains(String contentPattern) {
        return sql.selectFrom(POSTS)
                .where(POSTS.CONTENT.like("%" + contentPattern + "%"))
                .fetch();
    }

    public List<PostsCountByUserName> countOfPostsByUserName() {
        return sql.select(count(), USERS.USERNAME)
                .from(POSTS).join(USERS).on(POSTS.USER_ID.eq(USERS.ID))
                .groupBy(USERS.ID)
                .fetchInto(PostsCountByUserName.class);
    }

    public List<PostsRecord> postsOwnedByUserWithUsername(String username) {
        var userIdsWithUsername = sql.select(USERS.ID)
                .from(USERS)
                .where(USERS.USERNAME.eq(username));

        return sql.selectFrom(POSTS)
                .where(POSTS.USER_ID.in(userIdsWithUsername))
                .fetch();
    }
}
