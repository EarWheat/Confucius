package com.education.confucius.Entity.My.Gem;

import com.alibaba.fastjson.JSONObject;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/4/20 下午4:53
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class MoonGem extends BaseGem implements Gem {

    public Integer level = 8;
    public Long sellingPrice = 11000000L;
    public Long perLevelCost = 75000L;


    @Override
    public String name() {
        return GemEnum.MoonGem.name;
    }

    @Override
    public Long calProfits() {
        return super.calProfits(this.level,this.sellingPrice,this.perLevelCost);
    }

}
