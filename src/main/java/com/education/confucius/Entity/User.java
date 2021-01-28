package com.education.confucius.Entity;

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
    @JSONField(name = "userId")
    private BigInteger userId;

    @JSONField(name = "userName")
    private String userName;

    @JSONField(name = "password")
    private String password;

    @JSONField(name = "userNickName")
    private String userNickName;

    @JSONField(name = "createTime")
    private String createTime;

    @JSONField(name = "updateTime")
    private String updateTime;
}
