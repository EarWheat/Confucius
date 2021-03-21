package com.education.confucius.Entity.My.Flag;

import com.alibaba.fastjson.annotation.JSONField;
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
public class FlagSellingRecord implements Serializable {

    private static final long serialVersionUID = 1473645666621141L;

    @JSONField
    public String recordId;
    @JSONField
    public Integer redFlagNum;
    @JSONField
    public Integer greenFlagNum;
    @JSONField
    public Integer yellowFlagNum;
    @JSONField
    public Integer whiteFlagNum;
    @JSONField
    public Integer blueFlagNum;
    @JSONField
    private String createTime;
    @JSONField
    private String updateTime;
}
