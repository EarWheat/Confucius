package com.education.confucius.Entity.User;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/1/26 下午6:29
 * @desc 用户实体类
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 5042847169151350716L;

    @JSONField
    private String userId;

    @JSONField
    private String userName;

    @JSONField
    private String avatar;

    @JSONField
    private String password;

    @JSONField
    private String userNickName;

    @JSONField
    private String createTime;

    @JSONField
    private String updateTime;
}
