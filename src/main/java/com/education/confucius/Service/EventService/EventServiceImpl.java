package com.education.confucius.Service.EventService;

import com.education.confucius.Entity.Event.Event;
import com.education.confucius.Entity.Event.EventEnum;
import com.education.confucius.Entity.Event.EventParam;
import com.education.confucius.Entity.JudgeResult;
import com.education.confucius.Factory.EventFactory;
import org.springframework.stereotype.Service;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/1/15 上午11:56
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@Service
public class EventServiceImpl implements EventService {

    @Override
    public JudgeResult getEventJudge(EventEnum eventEnum, EventParam eventParam) {
        Event event = EventFactory.getEvent(eventEnum);
        JudgeResult judgeResult = new JudgeResult();
        if(event != null){
            judgeResult = event.judgeDuty(eventParam);
        }
        return judgeResult;
    }
}
