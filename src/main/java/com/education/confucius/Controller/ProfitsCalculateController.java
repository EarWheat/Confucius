package com.education.confucius.Controller;

import com.alibaba.fastjson.JSONObject;
import com.education.confucius.Entity.My.Flag.Flag;
import com.education.confucius.Entity.My.Flag.FlagSellingRecord;
import com.education.confucius.Entity.My.Gem.GemRequest;
import com.education.confucius.Service.MyService.MyService;
import com.pangu.Http.response.RestResult;
import com.pangu.Http.response.ResultEnum;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/2/22 下午3:38
 * @desc 宝石价格计算类
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
@RestController
@RequestMapping(value = "/my")
public class ProfitsCalculateController {


    @Resource
    public MyService myService;
    /**
     * 宝石收益
     * @param gemRequest
     * @return
     */
    @RequestMapping(value = "gem")
    public RestResult calculateGemProfits(@RequestBody GemRequest gemRequest){
        JSONObject summary = myService.calculateGemProfits(gemRequest);
        return RestResult.successResult(summary);
    }

    @RequestMapping(value = "flag")
    public RestResult<JSONObject> calculateFlagProfits(@RequestBody Flag flag){
        JSONObject summary = myService.calculateFlagProfits(flag);
        return RestResult.successResult(summary);
    }

    @RequestMapping(value = "/sellingFlag")
    public RestResult recordSelling(@RequestBody FlagSellingRecord flagSellingRecord){
        Boolean result = myService.recordSelling(flagSellingRecord);
        return result ? RestResult.successResult() : RestResult.failResult(ResultEnum.EXCEPTION);
    }

    @RequestMapping(value = "/flagSummary")
    public RestResult<JSONObject> flagSummary(@RequestParam(value = "hour") Long hour){
        JSONObject result = myService.getSummary(hour);
        return RestResult.successResult(result);
    }
}
