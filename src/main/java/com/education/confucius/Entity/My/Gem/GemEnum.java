package com.education.confucius.Entity.My.Gem;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/2/22 下午4:48
 * @desc 宝石枚举类
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public enum GemEnum {
    RedGem("Red", 7500L),
    BlackGem("Black",105000L);

    public String name;
    public Long purchasePrice;

    GemEnum(String name, Long purchasePrice){
        this.name = name;
        this.purchasePrice = purchasePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Long purchase) {
        this.purchasePrice = purchase;
    }
}
