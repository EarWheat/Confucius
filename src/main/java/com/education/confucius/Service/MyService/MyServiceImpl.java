package com.education.confucius.Service.MyService;

import com.alibaba.fastjson.JSONObject;
import com.education.confucius.Dao.My.FlagMapper;
import com.education.confucius.Entity.My.Flag.Flag;
import com.education.confucius.Entity.My.Flag.FlagRequest;
import com.education.confucius.Entity.My.Gem.GemRequest;
import com.education.confucius.Entity.My.Profits;
import com.education.confucius.Service.MyBaseService.BaseService;

import javax.annotation.Resource;

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

    @Resource
    public FlagMapper flagMapper;

    @Override
    public JSONObject calculateGemProfits(GemRequest gemRequest) {
        return null;
    }

    /**
     * 按日期统计收入
     * 1、查询当天已经录入的记录
     * @param flag
     * @return
     */
    @Override
    public JSONObject calculateFlagProfits(Flag flag) {
        JSONObject result = new JSONObject();
        Profits profits = new Profits();
        Long singleProfits = flag.sellingPrice - flag.purchasePrice;
        Long totalMYH = flag.nums * singleProfits;
        profits.setMYH(totalMYH);
        result.put("profits",profits);
        return result;
    }

    @Override
    public Boolean recordSelling(FlagRequest flagRequest) {
        return flagMapper.addRecord();
    }
}
