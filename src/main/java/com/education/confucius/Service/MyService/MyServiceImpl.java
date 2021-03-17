package com.education.confucius.Service.MyService;

import com.education.confucius.Entity.My.Flag.Flag;
import com.education.confucius.Entity.My.Gem.GemRequest;
import com.education.confucius.Entity.My.Profits;
import com.education.confucius.Entity.My.Summary;
import com.education.confucius.Service.MyBaseService.BaseService;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/3/17 下午7:54
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class MyServiceImpl extends BaseService implements MyService {
    @Override
    public Summary calculateGemProfits(GemRequest gemRequest) {
        return null;
    }

    @Override
    public Summary calculateFlagProfits(Flag flag) {
        Profits profits = new Profits();
        Long singleProfits = flag.sellingPrice - flag.purchasePrice;
        Long totalMYH = flag.nums * singleProfits;
        profits.setMYH(totalMYH);
        return new Summary();
    }
}
