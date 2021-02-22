package com.education.confucius.Service.GemService;

import com.education.confucius.Entity.My.Gem.Gem;
import com.education.confucius.Factory.GemFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    @Override
    public Long getProfits(String name, Long sellingPrice, Long level) {
        Gem gem = GemFactory.getGem(name);
        Optional.ofNullable(gem).ifPresent(this::calculatePurchasePrice);
        Optional.ofNullable(gem).ifPresent(Gem::calculateProfits);
        if(gem == null){
            return 0L;
        }
        return gem.getProfits();
    }

    /**
     * 计算宝石成本
     * @param gem
     * @return
     */
    public void calculatePurchasePrice(Gem gem){
        int numOfGem = needGemMerged(gem.level);    // 需要多少个一级宝石
        Long purchasePrice = gem.purchasePrice * numOfGem;
        // TODO: 算上点卡钱
        // TODO: 算上体力
        gem.setPurchasePrice(purchasePrice);
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
        for (int i = 1; i < 9; i++) {
            System.out.println("level " + i + " 需要" + gemService.needGemMerged(Long.parseLong(String.valueOf(i))) + "颗宝石");
        }
    }
}
