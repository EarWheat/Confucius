package com.education.confucius.Entity.User;

import lombok.Data;

import java.io.Serializable;

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
    private Long userId;
    private String userName;
    private String nickName;
    private String email;
}
