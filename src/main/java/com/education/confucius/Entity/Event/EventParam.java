package com.education.confucius.Entity.Event;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/1/15 下午2:27
 * @desc 事件参数
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@Data
public abstract class EventParam {
    @JSONField(name = "caller")
    public String caller;
    @JSONField(name = "role")
    public Integer role;
}
