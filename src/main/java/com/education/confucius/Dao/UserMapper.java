package com.education.confucius.Dao;

import com.education.confucius.Entity.User.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/1/26 下午7:39
 * @desc 用户查询类
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@Mapper
public interface UserMapper {
    User getUserByUserName(String userName);
    Boolean addUser(User user);
}
