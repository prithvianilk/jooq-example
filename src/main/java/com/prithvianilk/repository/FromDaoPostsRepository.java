package com.prithvianilk.repository;

import com.prithvianilk.tables.daos.PostsDao;
import com.prithvianilk.tables.pojos.Posts;

public class FromDaoPostsRepository {
    private final PostsDao postsDao;

    public FromDaoPostsRepository(PostsDao postsDao) {
        this.postsDao = postsDao;
    }

    public void save(Posts post) {
        postsDao.insert(post);
    }

    public Posts findById(String id) {
        return postsDao.findById(id);
    }

    // Can't implement group by queries w/ a jooq dao
}
