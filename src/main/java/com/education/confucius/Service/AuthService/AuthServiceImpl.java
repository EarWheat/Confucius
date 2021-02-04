package com.education.confucius.Service.AuthService;

import com.alibaba.fastjson.JSONObject;
import com.education.confucius.Constants.Constants;
import com.education.confucius.Entity.Auth.AuthEnum;
import com.pangu.Http.request.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/2/3 下午7:58
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@Service
public class AuthServiceImpl implements AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Override
    public JSONObject getAuthToken(AuthEnum authEnum) {
        String apiKey = authEnum.getApiKey();
        String secretKey = authEnum.getSecretKey();
        String getAccessTokenURL = Constants.BAIDU_GET_AUTH_TOKEN_URL + "?grant_type=" + Constants.BAIDU_GRANT_TYPE + "&client_id=" + apiKey + "&client_secret=" + secretKey;
        logger.info("getAccessTokenURL: {}", getAccessTokenURL);
        JSONObject token = null;
        try {
            String accessResult = HttpClient.doGetHttp(getAccessTokenURL,10000,10000);
            token = JSONObject.parseObject(accessResult);
        } catch (Exception e){
            e.printStackTrace();
            logger.error("getAccessToken Error, e:{}",e.toString());
        } 
        return token;
    }
}
