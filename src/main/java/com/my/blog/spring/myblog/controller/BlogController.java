package com.my.blog.spring.myblog.controller;

import com.my.blog.spring.myblog.exeption.TooManySymbolsException;
import com.my.blog.spring.myblog.model.Post;
import com.my.blog.spring.myblog.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class BlogController {

    private final PostService postService;

    public BlogController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/create")
    public String createPage(){
        return "create-page";
    }

    @PostMapping("/create")
    public String create(@RequestParam String title, @RequestParam String text, @RequestParam String authorName){
        Post post = new Post();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MM yyyy, EEEE, HH:mm:ss");
        Calendar calendar = new GregorianCalendar();

        post.setTitle(title);
        post.setText(text);
        post.setAuthorName(authorName);
        post.setDate(dateFormat.format(calendar.getTime()));
        postService.create(post);
        return "create-page";
    }

    @GetMapping("/update/{id}")
    public String updateById(@PathVariable(value = "id") Long id, Model model){
        ArrayList<Post> list = new ArrayList<>();
        Optional<Post> post = postService.findById(id);
        post.ifPresent(list::add);
        model.addAttribute("myPost", list);

        return "update-page";
    }

    @PostMapping("/update")
    public String update(@RequestParam String title, @RequestParam String text, @RequestParam String authorName , @RequestParam Long id){
        Post post = new Post();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MM yyyy, EEEE, HH:mm:ss");
        Calendar calendar = new GregorianCalendar();

        System.out.println(title);

        post.setTitle(title);
        post.setText(text);
        post.setAuthorName(authorName);
        post.setDate(dateFormat.format(calendar.getTime())+": it was updated ");
        postService.updateById(post, id);
        return "redirect:/read";
    }

    @GetMapping("/read")
    public String readPage(Model model){
        List<Post> list1 =  postService.findAll();
        Collections.reverse(list1);

        model.addAttribute("result", list1);
        return "read-page";
    }

    @GetMapping("/delete")
    public String deletePage(Model model){
        List<Post> list = postService.findAll();
        StringBuilder allId = new StringBuilder();
        for (Post post : list) {
            allId.append(post.getId()).append(" ");
        }

        model.addAttribute("allId", allId);
        return "delete-page";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id){
        postService.delete(id);
        return "delete-page";
    }

    @ExceptionHandler(TooManySymbolsException.class)
    public ResponseEntity<?> method1(TooManySymbolsException exc) {
        return ResponseEntity.badRequest().build();
    }

}
