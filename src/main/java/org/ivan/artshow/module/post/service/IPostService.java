package org.ivan.artshow.module.post.service;

import org.ivan.artshow.module.post.pojo.Post;
import org.ivan.artshow.module.post.pojo.dto.PostDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
/**
 * IPostService - 业务服务接口
 *
 * <p>IPostService定义业务方法规范。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public interface IPostService {
    public Post queryPost(Integer postId);
    public void deletePost(Integer postId);
    public Post updatePost(PostDTO post);
    public Post addPost(PostDTO post);
    List<Post> findAllPosts();
}
