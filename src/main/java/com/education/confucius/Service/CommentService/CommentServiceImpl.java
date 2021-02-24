package com.education.confucius.Service.CommentService;

import com.education.confucius.Dao.CommentMapper;
import com.education.confucius.Entity.Comment.Comment;
import com.education.confucius.Entity.Comment.CommentRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/2/24 下午5:51
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    public CommentMapper commentMapper;


    @Override
    public Boolean publicCommentService(CommentRequest commentRequest) {
        Comment comment = new Comment();
        comment.setUserId(commentRequest.getUserId());
        comment.setContent(commentRequest.getContent());
        comment.setParentId(commentRequest.getParentId());
        return commentMapper.publicComment(comment);
    }
}