package com.education.confucius.Controller;

import com.alibaba.fastjson.JSONObject;
import com.education.confucius.Dao.My.MedicineMapper;
import com.education.confucius.Entity.My.Flag.Flag;
import com.education.confucius.Entity.My.Flag.FlagSellingRecord;
import com.education.confucius.Entity.My.Gem.GemRequest;
import com.education.confucius.Entity.My.Items.Item;
import com.education.confucius.Entity.My.Items.ItemEnum;
import com.education.confucius.Factory.ItemSellingFactory;
import com.education.confucius.Service.MyService.MyService;
import com.pangu.Http.response.RestResult;
import com.pangu.Http.response.ResultEnum;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Optional;

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
    public RestResult<JSONObject> calculateGemProfits(@RequestBody GemRequest gemRequest){
        JSONObject summary = myService.calculateGemProfits(gemRequest);
        return RestResult.successResult(summary);
    }

    @RequestMapping(value = "flag")
    public RestResult<JSONObject> calculateFlagProfits(@RequestBody Flag flag){
        JSONObject summary = myService.calculateFlagProfits(flag);
        return RestResult.successResult(summary);
    }

    /**
     * 记录贩卖的旗子
     * @param flagSellingRecord
     * @return
     */
    @RequestMapping(value = "/sellingFlag")
    public RestResult recordSellingFlag(@RequestBody FlagSellingRecord flagSellingRecord){
        Boolean result = myService.recordSelling(flagSellingRecord);
        return result ? RestResult.successResult() : RestResult.failResult(ResultEnum.EXCEPTION);
    }

    /**
     * 总结
     * @param hour
     * @return
     */
    @RequestMapping(value = "/flagSummary")
    public RestResult<JSONObject> flagSummary(@RequestParam(value = "hour") Double hour){
        JSONObject result = myService.getSummary(hour);
        return RestResult.successResult(result);
    }

    @RequestMapping(value = "/sell")
    public RestResult recordSellingMedicine(@RequestParam(value = "type") String type, @RequestParam(value = "num") int num){
        Item item = ItemSellingFactory.getItem(type);
        if(item == null){
            return RestResult.failResult(ResultEnum.PARAM_EMPTY);
        }
        item.setNum(Optional.of(num).orElse(0));
        Boolean result = myService.recordSellingV2(item);
        return result ? RestResult.successResult() : RestResult.failResult(ResultEnum.EXCEPTION);
    }
}
