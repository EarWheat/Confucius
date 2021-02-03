package com.education.confucius.Controller;

import com.education.confucius.Entity.User.User;
import com.education.confucius.Service.UserService.UserServiceImpl;
import com.pangu.Http.response.RestResult;
import com.pangu.Http.response.ResultEnum;
import com.pangu.Redis.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2020-09-22 16:30
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@RestController
public class UserController {

    @Resource
    public UserServiceImpl userService;

    @RequestMapping("/redisInfo")
    public RestResult getRedisInfo(){
        RedisUtil.set("name","Confucius");
        String hello = "Hello!".concat(RedisUtil.get("name"));
        return RestResult.successResult(hello);
    }

    @RequestMapping("/")
    public RestResult welcome(){
        return RestResult.successResult("welcome");
    }

    @RequestMapping("/getUserByUserName")
    public RestResult getUserInfoByName(@RequestParam(value = "userName") String userName){
        if(StringUtils.isBlank(userName)){
            return RestResult.failResult(ResultEnum.PARAM_EMPTY);
        }
        User user = userService.getUserByUserName(userName);
        return RestResult.successResult(user);
    }

    @RequestMapping("/getUserInfo")
    public RestResult getUserInfo(@RequestParam(value = "userName") String name){
        if(StringUtils.isBlank(name)){
            return RestResult.failResult(ResultEnum.PARAM_EMPTY);
        }
        User user = userService.getUserByUserName(name);
        return RestResult.successResult(user);
    }

    @RequestMapping("/addUser")
    public RestResult addUser(@RequestBody User user){
        Boolean eventResult = userService.addUser(user);
        if(eventResult){
            return RestResult.successResult();
        }
        return RestResult.failResult("add user error");
    }

}
