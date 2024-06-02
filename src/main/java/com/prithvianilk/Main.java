package com.prithvianilk;

import org.jooq.DSLContext;

public class Main {
    public static void main(String[] args) {
        String userId = "dc812c8f-6994-4123-861b-8f98bd8b3ab8";
        String postId1 = "dc81288f-6994-4123-861b-8f98bd8b3ab9";
        String postId2 = "dc81288f-6994-4123-861b-8f98bd8b3ab0";
        String postId3 = "dc81288f-6994-4123-861b-8f98bd8b3ab1";

        DSLContext dsl = DslContextSingleton.getInstance();
        UserRepository userRepository = new UserRepository(dsl);
//         userRepository.save(userId, "prithvianilk");

        System.out.println(userRepository.findById(userId));

        PostRepository postRepository = new PostRepository(dsl);
//        postRepository.save(postId1, userId, "lmao");
//        postRepository.save(postId2, userId, "gg");
//        postRepository.save(postId3, userId, "dhh rox");

        System.out.println(postRepository.findByUserId(userId));
        postRepository.countOfPostsByUserId().forEach(System.out::println);
    }
}