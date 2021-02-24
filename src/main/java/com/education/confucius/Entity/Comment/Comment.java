package com.education.confucius.Entity.Comment;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/2/24 下午5:54
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@Data
public class Comment implements Serializable {
    private static final long serialVersionUID = -8817472561955051311L;
    @JSONField
    public BigInteger userId;
    @JSONField
    public String commentId;
    @JSONField
    public String mediaId;
    @JSONField
    public String content;
    @JSONField(name = "createTime")
    private String createTime;
    @JSONField(name = "updateTime")
    private String updateTime;
}
