package com.education.confucius.Entity.My.Gem;

import com.alibaba.fastjson.JSONObject;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/5/15 上午11:33
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class BaseGem {
    // 宝石合成费用
    public Long mergeFee(Integer level){
        if(level == 1){
            return 0L;
        }
        if(level == 2){
            return 500L;
        } else {
            return mergeFee(level - 1) * 2 + 500 * (level - 1);
        }
    }

    public Long totalCost(Integer level, Long perLevelCost){
        if(level == 1){
            return perLevelCost;
        }
        return totalCost(level - 1, perLevelCost) * 2;
    }

    public Long calProfits(Integer level, Long sellingPrice, Long perLevelCost) {
        Long mergeFee = mergeFee(level);
        Long totalCost = totalCost(level, perLevelCost);
        return sellingPrice - mergeFee - totalCost;
    }

    public static void main(String[] args) {
        BaseGem baseGem = new BaseGem();
        System.out.println(baseGem.calProfits(5,2200000L,127000L));
        System.out.println(baseGem.calProfits(6,4400000L,127000L));
        System.out.println(baseGem.calProfits(7,8800000L,127000L));
        System.out.println(baseGem.calProfits(8,17600000L,127000L));
    }
}
