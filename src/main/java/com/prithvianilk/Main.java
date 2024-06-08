package com.prithvianilk;

import com.prithvianilk.repository.MyPostRepository;
import com.prithvianilk.repository.MyUserRepository;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        String userId = "dc812c8f-6994-4123-861b-8f98bd8b3ab8";
        String userId2 = "dc81288f-6994-4123-861b-8f98bd8b3ab2";
        String postId1 = "dc81288f-6994-4123-861b-8f98bd8b3ab9";
        String postId2 = "dc81288f-6994-4123-861b-8f98bd8b3ab0";
        String postId3 = "dc81288f-6994-4123-861b-8f98bd8b3ab1";
        String postId4 = "dc81288f-6994-4123-861b-8f98bd8b3ab3";

        DSLContext dsl = DslContextSingleton.getInstance();
        MyUserRepository userRepository = new MyUserRepository(dsl);
//         userRepository.save(userId, "prithvianilk");
//         userRepository.save(userId2, "ragiballs");

        log.info("{}", userRepository.findAll());

        log.info("{}", userRepository.findById(userId));

        MyPostRepository postRepository = new MyPostRepository(dsl);
//        postRepository.save(postId1, userId, "lmao");
//        postRepository.save(postId2, userId, "gg");
//        postRepository.save(postId3, userId, "dhh gg rox");
//        postRepository.save(postId4, userId2, "go lang");

        log.info("{}", postRepository.findByUserId(userId));
        log.info("{}", postRepository.countOfPostsByUserId());
        log.info("{}", postRepository.findPostsWhereContentContains("gg"));
        log.info("{}", postRepository.countOfPostsByUserName());
    }
}