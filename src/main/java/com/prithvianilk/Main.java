package com.prithvianilk;

import com.prithvianilk.repository.PostRepository;
import com.prithvianilk.repository.UserRepository;
import com.prithvianilk.tables.daos.PostsDao;
import com.prithvianilk.tables.daos.UsersDao;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    private static final String userId = "dc812c8f-6994-4123-861b-8f98bd8b3ab8";
    private static final String userId2 = "dc81288f-6994-4123-861b-8f98bd8b3ab2";
    private static final String postId1 = "dc81288f-6994-4123-861b-8f98bd8b3ab9";
    private static final String postId2 = "dc81288f-6994-4123-861b-8f98bd8b3ab0";
    private static final String postId3 = "dc81288f-6994-4123-861b-8f98bd8b3ab1";
    private static final String postId4 = "dc81288f-6994-4123-861b-8f98bd8b3ab3";

    public static void main(String[] args) {
        showcaseDsl();
        showcaseDao();
    }

    private static void showcaseDsl() {
        DSLContext dsl = DslContextSingleton.getInstance();
        UserRepository userRepository = new UserRepository(dsl);
//         userRepository.save(userId, "prithvianilk");
//         userRepository.save(userId2, "ragiballs");

        log.info("{}", userRepository.findAll());
        log.info("{}", userRepository.findById(userId));

        PostRepository postRepository = new PostRepository(dsl);
//        postRepository.save(postId1, userId, "lmao");
//        postRepository.save(postId2, userId, "gg");
//        postRepository.save(postId3, userId, "dhh gg rox");
//        postRepository.save(postId4, userId2, "go lang");

        log.info("{}", postRepository.findByUserId(userId));
        log.info("{}", postRepository.countOfPostsByUserId());
        log.info("{}", postRepository.findPostsWhereContentContains("gg"));
        log.info("{}", postRepository.countOfPostsByUserName());
        log.info("{}", postRepository.postsOwnedByUserWithUsername("prithvianilk"));
    }

    private static void showcaseDao() {
        Configuration configuration = getDaoConfiguration();

        UsersDao usersDao = new UsersDao(configuration);
        log.info("{}", usersDao.findById(userId));

        PostsDao postsDao = new PostsDao(configuration);
        log.info("{}", postsDao.findAll());
    }

    private static Configuration getDaoConfiguration() {
        return new DefaultConfiguration()
                .set(JdbcConnectionSingleton.getInstance())
                .set(SQLDialect.SQLITE);
    }
}