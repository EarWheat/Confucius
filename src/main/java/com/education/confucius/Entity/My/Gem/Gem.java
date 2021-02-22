package com.education.confucius.Entity.My.Gem;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/2/22 下午3:32
 * @desc 基础宝石类
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@Data
public class Gem {
    @JSONField(name = "name")
    public String name;

    @JSONField(name =  "sellingPrice")
    public Long sellingPrice;

    @JSONField(name = "purchasePrice")
    public Long purchasePrice;

    @JSONField(name = "level")
    public Long level;

    @JSONField(name = "profits")
    public Long profits;

    public Gem(String name, Long purchasePrice){
        this.name = name;
        this.purchasePrice = purchasePrice;
    }

    public void calculateProfits(){
        this.profits = this.sellingPrice - this.purchasePrice;
    }
}
