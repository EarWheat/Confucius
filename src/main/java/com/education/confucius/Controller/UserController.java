package com.education.confucius.Controller;

import com.pangu.Http.response.RestResult;
import com.pangu.Redis.RedisUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
