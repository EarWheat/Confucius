package com.education.confucius.Entity.My.Flag;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

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
public class Flag {
    @JSONField
    private FlagEnum color;
    @JSONField
    public Long sellingPrice;
    @JSONField
    public Long purchasePrice;
    @JSONField
    public Long profits;
}
