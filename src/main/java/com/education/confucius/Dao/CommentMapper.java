package com.education.confucius.Dao;

import com.education.confucius.Entity.Comment.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/2/24 下午5:53
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@Mapper
public interface CommentMapper {
    Boolean publicComment(Comment comment);
}
