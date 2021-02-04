package com.education.confucius.Service.AuthService;

import com.alibaba.fastjson.JSONObject;
import com.education.confucius.Entity.Auth.AuthEnum;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/2/3 下午7:55
 * @desc 获取鉴权token
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public interface AuthService {
    String getAuthToken(AuthEnum authEnum);
}
