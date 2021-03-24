package com.education.confucius.Entity.My.Items;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/3/24 下午4:05
 * @desc 物品类
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@Data
public class Item implements Serializable {
    private static final long serialVersionUID = -4417476527007496068L;
    @JSONField
    public String recordId;
    @JSONField
    public String name;
    @JSONField
    public Integer num;
    @JSONField
    private String createTime;
    @JSONField
    private String updateTime;
}
