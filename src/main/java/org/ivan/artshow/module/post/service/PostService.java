package org.ivan.artshow.module.post.service;
import org.ivan.artshow.common.core.result.Result;


import org.ivan.artshow.common.core.resultcode.ResultCodes;
import org.ivan.artshow.common.exception.BizException;
import org.ivan.artshow.module.post.pojo.Post;
import org.ivan.artshow.module.post.pojo.dto.PostDTO;
import org.ivan.artshow.module.post.repository.PostRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
/**
 * PostService - 业务服务实现类
 *
 * <p>PostService实现具体的业务逻辑。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class PostService implements IPostService{
    @Autowired
    PostRepository postRepository;

    @Override
    public Post addPost(PostDTO post){
        Post nPost = new Post();
        BeanUtils.copyProperties(post,nPost);
        return postRepository.save(nPost);
    }

    @Override
    public void deletePost(Integer PostId){
        postRepository.deleteById(PostId);
    }

    @Override
    public Post updatePost(PostDTO Post){
        Integer postId = Post.getPostId();
        Post post = postRepository.findById(postId).orElseThrow(()->new BizException(ResultCodes.NOTFOUND));
        BeanUtils.copyProperties(Post,post);
        return postRepository.save(post);
    }

    @Override
    public Post queryPost(Integer postId){
        return postRepository.findById(postId).orElseThrow(()->new BizException(ResultCodes.NOTFOUND));
    }

    @Override
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }
}
