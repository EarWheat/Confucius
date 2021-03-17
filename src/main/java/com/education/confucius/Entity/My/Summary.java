package com.education.confucius.Entity.My;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/3/17 下午8:19
 * @desc 总结
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class Summary {
    @JSONField
    public Profits profits;
    @JSONField
    public BaseExpend expend;
}
