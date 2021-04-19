package com.education.confucius.Controller;

import com.education.confucius.Entity.Event.EventEnum;
import com.education.confucius.Entity.JudgeResult;
import com.education.confucius.Entity.RejectEvent.RejectEventParam;
import com.education.confucius.Service.EventService.EventService;
import com.pangu.Http.response.RestResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/1/14 下午8:02
 * @desc 外卖、快递拒单类
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@RestController
@RequestMapping(value = "/reject")
public class RejectController {

    @Resource
    EventService eventService;

    @RequestMapping("/dutyJudge")
    public RestResult<JudgeResult> getDutyJudge(@RequestBody RejectEventParam rejectEventParam){
        JudgeResult judgeResult = eventService.getEventJudge(EventEnum.RejectEvent, rejectEventParam);
        return RestResult.successResult(judgeResult);
    }
}
