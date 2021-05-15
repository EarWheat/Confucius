package com.education.confucius.Service.GemService;

import com.alibaba.fastjson.JSONObject;
import com.education.confucius.Entity.My.Gem.Gem;
import com.education.confucius.Factory.GemFactory;
import jdk.nashorn.api.scripting.JSObject;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/2/22 下午4:44
 * @desc 宝石服务类实现
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@Service
public class GemServiceImpl implements GemService {

    private static final Long mergePrice = 500L;

    @Override
    public String name() {
        return null;
    }

    @Override
    public Map<String, JSONObject> profitsSummary() {
        Map<String, Gem> gemMap = GemFactory.getAllGem();
        Map<String, JSONObject> gemSummary = new HashMap<>(8);
//        Optional.ofNullable(gemMap).(g -> gemSummary.put(g.name(),g.calProfits()));
        return gemSummary;
    }

    /**
     * 计算宝石成本
     * @param gem
     * @return
     */
    public void calculatePurchasePrice(Gem gem){
//        int numOfGem = needGemMerged(gem.level);    // 需要多少个一级宝石
//        Long purchasePrice = gem.purchasePrice * numOfGem;
//        // TODO: 算上点卡钱
//        // TODO: 算上体力
//        gem.setPurchasePrice(purchasePrice);
    }

    public Long calProfits(Long level, Long purchasePrice, Long sellingPrice){
        Long cost = calCost(level,purchasePrice);
        return sellingPrice - cost;
    }

    public Long calCost(Long level, Long purchasePrice){
        if(level == 1L){
            return purchasePrice;
        }
        return calCost(level - 1, purchasePrice) * 2 + mergePrice * (level - 1);
    }

    /**
     * 计算合成宝石需要的宝石数量
     * @param level
     * @return
     */
    public int needGemMerged(Long level){
        if(level == 1L){
            return 1;
        }
        return needGemMerged(level - 1) * 2;
    }

    public static void main(String[] args) {
        GemServiceImpl gemService = new GemServiceImpl();
        System.out.println(gemService.calProfits(8L,127000L,17600000L));
    }
}
