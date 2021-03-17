package com.education.confucius.Entity.My.Flag;

import com.alibaba.fastjson.annotation.JSONField;
import com.education.confucius.Entity.My.BaseExpend;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/3/15 下午5:20
 * @desc 梦幻西游旗帜赚钱
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@Data
public class Flag extends BaseExpend {
    @JSONField
    private FlagEnum color;
    @JSONField
    @Value("${my.flag.selling-price:}")
    public Long sellingPrice;
    @JSONField
    @Value("${my.flag.purchase-price:}")
    public Long purchasePrice;
    @JSONField
    public Long profits;
    @JSONField
    public Long nums;
}
