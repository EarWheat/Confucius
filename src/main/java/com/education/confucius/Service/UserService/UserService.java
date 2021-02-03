package com.education.confucius.Service.UserService;

import com.education.confucius.Entity.User.User;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/1/26 下午6:26
 * @desc 用户类Service
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */

public interface UserService {
    User getUserByUserName(String userName);
    Boolean updateUserInfo(User user);
    Boolean addUser(User user);
}
