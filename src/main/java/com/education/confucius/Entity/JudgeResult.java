package com.education.confucius.Entity;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/1/15 下午12:03
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@Data
@Component
public class JudgeResult {
    // 结果
    public Integer duty;
    // 事件名字
    public String name;
}
