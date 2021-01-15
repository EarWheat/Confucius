package com.education.confucius.Entity.Event;


import com.education.confucius.Entity.JudgeResult;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/1/14 下午8:06
 * @desc 事件
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public interface Event<T extends EventParam> {
    JudgeResult judgeDuty(T eventParam);
}
