package com.my.blog.spring.myblog.service;

import com.my.blog.spring.myblog.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    void create(Post post);

    //List<Post> findAllFromLastAddedOrChanged();

    void updateById(Post post, Long id);

    void delete(Long id);

    void deleteAll();

    List<Post> findAll();

    Optional<Post> findById(Long id);
}
