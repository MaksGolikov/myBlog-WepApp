package com.my.blog.spring.myblog.service.impl;

import com.my.blog.spring.myblog.exeption.TooManySymbolsException;
import com.my.blog.spring.myblog.model.Post;
import com.my.blog.spring.myblog.repository.PostRepository;
import com.my.blog.spring.myblog.service.PostService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void create(Post post) {
        if (post.getText().toCharArray().length >= 140) {
            throw new TooManySymbolsException("Many symbols");
        }
        postRepository.save(post);
    }

    @Override
    public void updateById(Post post, Long id) {
        Post post1 = postRepository.findById(id).orElseThrow();
        post1.setAuthorName(post.getAuthorName());
        post1.setDate(post.getDate());
        post1.setText(post.getText());
        post1.setTitle(post.getTitle());
        postRepository.save(post1);
    }

    @Override
    public void delete(Long id) {

        postRepository.deleteById(id);

    }

    @Override
    public void deleteAll() {
        postRepository.deleteAll();
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }
}
