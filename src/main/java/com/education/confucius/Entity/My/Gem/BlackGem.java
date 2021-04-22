package com.education.confucius.Entity.My.Gem;


import com.alibaba.fastjson.JSONObject;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/4/20 下午4:42
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class BlackGem implements Gem{

    @Override
    public String name() {
        return GemEnum.BlackGem.name;
    }

    @Override
    public JSONObject calProfits() {
        return null;
    }
}
