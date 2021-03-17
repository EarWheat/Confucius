package com.education.confucius.Entity.My;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/3/17 下午8:11
 * @desc 获得利润
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@Data
public class Profits {
    @JSONField
    public Long MYH;    //   梦幻币
    @JSONField
    public Long physical; // 体力
    @JSONField
    public Long vitality; // 活力
}
