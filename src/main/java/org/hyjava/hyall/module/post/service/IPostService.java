package org.hyjava.hyall.module.post.service;
import org.hyjava.hyall.common.core.result.Result;

import org.hyjava.hyall.module.post.pojo.Post;
import org.hyjava.hyall.module.post.pojo.dto.PostDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPostService {
    public Post queryPost(Integer postId);
    public void deletePost(Integer postId);
    public Post updatePost(PostDTO post);
    public Post addPost(PostDTO post);
    List<Post> findAllPosts();
}
