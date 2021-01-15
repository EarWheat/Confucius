package com.education.confucius.Service.EventService;

import com.education.confucius.Entity.Event.EventEnum;
import com.education.confucius.Entity.Event.EventParam;
import com.education.confucius.Entity.JudgeResult;
import org.springframework.stereotype.Service;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/1/15 上午11:56
 * @desc 事件接口
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public interface EventService {
    JudgeResult getEventJudge(EventEnum eventEnum, EventParam eventParam);
}
