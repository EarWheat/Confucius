package com.education.confucius.Entity.My.Flag;


import com.education.confucius.Constants.Constants;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/3/22 下午8:10
 * @desc 导标棋收入
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class FlagInCome {
    public static Long calculateFlagProfits(Integer num){
        Long singleProfits = Constants.FLAG_SELLING_PRICE - Constants.FLAG_PURCHASE_PRICE;
        return singleProfits * num;
    }
}
