package com.education.confucius.Service.UserService;

import com.education.confucius.Dao.Confucius.UserMapper;
import com.education.confucius.Entity.User.User;
import org.apache.commons.lang.StringUtils;
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

    @Override
    public User getUserByUserId(String userId) {
        //TODO:Redis提升性能
        return userMapper.getUserByUserId(userId);
    }

    @Override
    public Boolean updateUserInfo(User user) {
        return null;
    }

    @Override
    public Boolean addUser(User user) {
        if(StringUtils.isBlank(user.getUserName()) || StringUtils.isBlank(user.getPassword())){
            return false;
        }
        User originalUser = userMapper.getUserByUserName(user.getUserName());
        if(originalUser == null){
            return userMapper.addUser(user);
        } else {
            return false;
        }
    }
}
