package org.ivan.artshow.module.post.service;

import org.ivan.artshow.common.auth.UserContext;
import org.ivan.artshow.common.core.resultcode.ResultCodes;
import org.ivan.artshow.common.exception.BizException;
import org.ivan.artshow.module.post.pojo.Post;
import org.ivan.artshow.module.post.pojo.dto.PostDTO;
import org.ivan.artshow.module.post.repository.PostRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PostService - 业务服务实现类
 *
 * <p>PostService实现具体的业务逻辑。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Service
public class PostService implements IPostService{
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post addPost(PostDTO post){
        if (post == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Post nPost = new Post();
        BeanUtils.copyProperties(post,nPost);
        return postRepository.save(nPost);
    }

    @Override
    public void deletePost(Long PostId){
        if (PostId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }

        // 查询帖子
        Post post = postRepository.findById(PostId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND, "帖子不存在"));

        // 权限检查：只有帖子作者或管理员可以删除
        Long currentUserId = UserContext.getUserId();
        String currentUserRole = UserContext.getRole();

        if (!post.getUserId().equals(currentUserId) && !"ADMIN".equals(currentUserRole)) {
            throw new BizException(ResultCodes.FORBIDDEN, "无权删除此帖子");
        }

        postRepository.deleteById(PostId);
    }

    @Override
    public Post updatePost(PostDTO Post){
        if (Post == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        Long postId = Post.getPostId();
        if (postId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }

        // 查询帖子
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BizException(ResultCodes.NOTFOUND, "帖子不存在"));

        // 权限检查：只有帖子作者或管理员可以修改
        Long currentUserId = UserContext.getUserId();
        String currentUserRole = UserContext.getRole();

        if (!post.getUserId().equals(currentUserId) && !"ADMIN".equals(currentUserRole)) {
            throw new BizException(ResultCodes.FORBIDDEN, "无权修改此帖子");
        }

        // 更新帖子内容（不允许修改userId）
        if (Post.getTitle() != null) post.setTitle(Post.getTitle());
        if (Post.getDescription() != null) post.setDescription(Post.getDescription());
        if (Post.getImageUrl() != null) post.setImageUrl(Post.getImageUrl());
        if (Post.getSubcategoryId() != null) post.setSubcategoryId(Post.getSubcategoryId());

        return postRepository.save(post);
    }

    @Override
    public Post queryPost(Long postId){
        if (postId == null) {
            throw new BizException(ResultCodes.NULLPOINT);
        }
        return postRepository.findById(postId).orElseThrow(()->new BizException(ResultCodes.NOTFOUND));
    }

    @Override
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }
}
