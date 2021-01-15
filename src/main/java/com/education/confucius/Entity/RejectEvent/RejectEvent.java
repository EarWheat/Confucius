package com.education.confucius.Entity.RejectEvent;

import com.education.confucius.Constants.Constants;
import com.education.confucius.Entity.Event.Event;
import com.education.confucius.Entity.JudgeResult;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/1/15 下午12:16
 * @desc 拒单事件
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class RejectEvent implements Event<RejectEventParam> {

    public JudgeResult judgeDuty(RejectEventParam eventParam) {
        JudgeResult judgeResult = new JudgeResult();
        if(eventParam.getType() == 1024){
            judgeResult.setDuty(Constants.HAVE_DUTY);
            judgeResult.setName(RejectEventEnum.long_distance_order.name());
        } else if(eventParam.getType() == 2048){
            judgeResult.setDuty(Constants.HAVE_DUTY);
            judgeResult.setName(RejectEventEnum.danger_order.name());
        } else {
            judgeResult.setDuty(Constants.NO_DUTY);
            judgeResult.setName(RejectEventEnum.reveal_order.name());
        }
        return judgeResult;
    }

}
