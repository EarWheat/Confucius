package com.education.confucius.Service.CommentService;

import com.education.confucius.Entity.Comment.Comment;
import com.education.confucius.Entity.Comment.CommentRequest;

import java.util.List;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/2/24 下午5:52
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public interface CommentService {
    Boolean publicCommentService(CommentRequest commentRequest);
    List<Comment> getCommentList(String mediaId);
}
