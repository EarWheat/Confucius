package com.education.confucius.Entity.Auth;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/2/3 下午7:56
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public enum AuthEnum {
    Tencent("1","2"),
    Baidu("1","2"),
    Alibaba("1","2");

    private String AppKey;
    private String SecretKey;

    private AuthEnum(String appKey, String secretKey) {
        AppKey = appKey;
        SecretKey = secretKey;
    }
}
