package com.education.confucius.Entity.My.Gem;

import com.alibaba.fastjson.annotation.JSONField;
import com.education.confucius.Entity.My.BaseExpend;
import lombok.Data;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/2/22 下午3:43
 * @desc 请求计算宝石类
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@Data
public class GemRequest extends BaseExpend  {
    @JSONField(name = "name")
    public String name;

    @JSONField(name = "level")
    public String level;

    @JSONField(name = "sellingPrice")
    public Long sellingPrice;
}
