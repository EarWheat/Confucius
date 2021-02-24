package com.education.confucius.Entity.Comment;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.math.BigInteger;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/2/24 下午5:44
 * @desc 评论请求
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@Data
public class CommentRequest {
    @JSONField
    public String parentId;

    @JSONField
    public String content;

    @JSONField
    public BigInteger userId;

}
