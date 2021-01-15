package com.education.confucius.Factory;

import com.education.confucius.Entity.CancelEvent.CancelEvent;
import com.education.confucius.Entity.Event.Event;
import com.education.confucius.Entity.Event.EventEnum;
import com.education.confucius.Entity.RejectEvent.RejectEvent;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/1/14 下午8:05
 * @desc 事件工厂类
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class EventFactory {
    public static Event getEvent(EventEnum eventName){
        if(eventName.equals(EventEnum.RejectEvent)){
            return new RejectEvent();
        } else if(eventName.equals(EventEnum.CancelEvent)){
            return new CancelEvent();
        }
        return null;
    }
}
