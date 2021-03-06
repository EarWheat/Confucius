package com.education.confucius.Service.CommentService;

import com.education.confucius.Dao.Confucius.CommentMapper;
import com.education.confucius.Entity.Comment.Comment;
import com.education.confucius.Entity.Comment.CommentRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
        comment.setUserId(commentRequest.getUserName());
        comment.setContent(commentRequest.getContent());
        comment.setMediaId(commentRequest.getMediaId());
        comment.setCommentId(UUID.randomUUID().toString().replace("-", ""));
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        comment.setCreateTime(simpleDateFormat.format(date));
        comment.setUpdateTime(simpleDateFormat.format(date));
        return commentMapper.publicComment(comment);
    }

    @Override
    public List<Comment> getCommentList(String mediaId) {
        return commentMapper.getCommentList(mediaId);
    }
}
