package com.education.confucius.Controller;

import com.education.confucius.Entity.Comment.Comment;
import com.education.confucius.Entity.Comment.CommentRequest;
import com.education.confucius.Service.CommentService.CommentService;
import com.education.confucius.Service.UserService.UserService;
import com.pangu.Http.response.RestResult;
import com.pangu.Http.response.ResultEnum;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/2/24 下午5:24
 * @desc 评论控制类
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    @Resource
    public CommentService commentService;

    @Resource
    public UserService userService;
    /**
     * 获取对应评论
     * @param mediaId
     * @return
     */
    @RequestMapping(value = "/getCommentList")
    public RestResult getCommentList(@RequestParam(value = "mediaId") String mediaId){
        List<Comment> commentList = commentService.getCommentList(mediaId);
        commentList.forEach(comment -> comment.setAvatar(userService.getUserByUserId(comment.getUserId()).getAvatar()));
        return RestResult.successResult(commentList);
    }


    /**
     * 发表评论
     * @param commentRequest
     * @return
     */
    @RequestMapping(value = "/publicComment")
    public RestResult publicComment(@RequestBody CommentRequest commentRequest){
        if(commentRequest.getUserName() == null){
            return RestResult.failResult(ResultEnum.EXCEPTION);
        }
        Boolean publicStatus = commentService.publicCommentService(commentRequest);
        return publicStatus ? RestResult.successResult() : RestResult.failResult(ResultEnum.EXCEPTION);
    }
}
