package com.education.confucius.Service.UserService;

import com.education.confucius.Dao.UserMapper;
import com.education.confucius.Entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/1/26 下午6:30
 * @desc 用户Service实现类
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    public UserMapper userMapper;

    @Override
    public User getUserByUserName(String userName) {
        return userMapper.getUserByUserName(userName);
    }
}
