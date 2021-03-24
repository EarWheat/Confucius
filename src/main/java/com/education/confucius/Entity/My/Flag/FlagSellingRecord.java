package com.education.confucius.Entity.My.Flag;

import com.alibaba.fastjson.annotation.JSONField;
import com.education.confucius.Entity.My.Items.Item;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/3/21 下午4:14
 * @desc 导标棋售卖记录表
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@Data
public class FlagSellingRecord extends Item implements Serializable {

    private static final long serialVersionUID = 1473645666621141L;

    @JSONField
    public Integer redFlagNum = 0;
    @JSONField
    public Integer greenFlagNum = 0;
    @JSONField
    public Integer yellowFlagNum = 0;
    @JSONField
    public Integer whiteFlagNum = 0;
    @JSONField
    public Integer blueFlagNum = 0;
}
