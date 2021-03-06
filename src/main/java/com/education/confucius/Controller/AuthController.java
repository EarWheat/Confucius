package com.education.confucius.Controller;

import com.alibaba.fastjson.JSONObject;
import com.education.confucius.Entity.Auth.AuthEnum;
import com.education.confucius.Service.AuthService.AuthService;
import com.pangu.Http.response.RestResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/2/4 上午10:30
 * @desc 鉴权
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@RequestMapping("/auth")
@RestController
public class AuthController {
    @Resource
    AuthService authService;

    @RequestMapping("/getToken")
    public RestResult<JSONObject> getToken(@RequestParam(value = "product") String product){
        AuthEnum authEnum;
        if(product.equals(AuthEnum.Baidu.getName())){
            authEnum = AuthEnum.Baidu;
        } else if(product.equals(AuthEnum.Tencent.getName())){
            authEnum = AuthEnum.Tencent;
        } else {
            authEnum = AuthEnum.Alibaba;
        }
        JSONObject token = authService.getAuthToken(authEnum);
        return RestResult.successResult(token);
    }
}
