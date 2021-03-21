package com.education.confucius.Entity.My.Flag;

import com.alibaba.fastjson.annotation.JSONField;
import com.education.confucius.Entity.My.BaseExpend;
import lombok.Data;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/3/15 下午6:14
 * @desc 导标棋请求类
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@Data
public class FlagRequest extends BaseExpend {
    @JSONField
    public String RED;
    @JSONField
    public String YELLOW;
    @JSONField
    public String GREEN;
    @JSONField
    public String WHITE;
}
