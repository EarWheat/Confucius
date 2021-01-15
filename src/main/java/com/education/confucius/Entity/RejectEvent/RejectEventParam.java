package com.education.confucius.Entity.RejectEvent;

import com.alibaba.fastjson.annotation.JSONField;
import com.education.confucius.Entity.Event.EventParam;
import lombok.Data;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/1/15 下午2:32
 * @desc 拒单参数
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@Data
public class RejectEventParam extends EventParam {
    @JSONField(name = "orderId")
    public Long orderId;
    @JSONField(name = "type")
    public Long type;

}
