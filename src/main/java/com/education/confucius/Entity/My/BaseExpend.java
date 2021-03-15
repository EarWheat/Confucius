package com.education.confucius.Entity.My;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/3/15 下午7:58
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@Data
public class BaseExpend {
    @JSONField
    public Long hour;
    @JSONField
    public Long physical;   // 体力
    @JSONField
    public Long vitality; // 活力
    @JSONField
    public Long level;
}
