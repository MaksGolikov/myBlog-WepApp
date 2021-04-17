package com.my.blog.spring.myblog.util;

import com.my.blog.spring.myblog.model.Post;

import java.util.List;

public class CopyUtil {

    public static List<Post> copyUtil(List<Post> list, List<Post> list1, int i){
        Post post = list1.get(i);
        for (int j = 0; j < list1.size(); j++) {
            list.set(j, list1.get(j));
        }
        list.set(i, post);
        return list;
    }
}
