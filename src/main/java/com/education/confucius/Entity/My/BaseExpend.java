package com.education.confucius.Entity.My;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/3/15 下午7:58
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class BaseExpend {
    @JSONField
    public Long hour;
    @JSONField
    public Long carValue;

    public static Long expend(Long hour){
        return hour * 6 * MyConstant.CAR_VALUE;
    }
}
