package com.education.confucius.Controller;

import com.education.confucius.Entity.My.Gem.GemRequest;
import com.pangu.Http.response.RestResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class GemCalculateController {

    @RequestMapping(value = "calculateProfits")
    public RestResult calculateProfits(@RequestBody GemRequest gemRequest){

        return RestResult.successResult();
    }
}
