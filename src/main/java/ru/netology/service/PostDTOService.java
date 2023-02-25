package ru.netology.service;

import ru.netology.model.Post;
import ru.netology.model.PostDTO;

public class PostDTOService {
    public static PostDTO postToPostDTO(Post post){
        if (!post.isRemoved()) return new PostDTO(post.getId(), post.getContent());
        else return null;
    }
}
